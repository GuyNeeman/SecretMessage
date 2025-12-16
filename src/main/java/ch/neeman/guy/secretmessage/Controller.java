package ch.neeman.guy.secretmessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/secretmessage")
@CrossOrigin("*")
public class Controller {

    private final BCryptPasswordEncoder encoder;
    private final SecretMessageRepository repo;
    private final SecretMessageApplication secretMessageApplication;

    @Autowired
    public Controller(BCryptPasswordEncoder encoder, SecretMessageRepository repo, SecretMessageApplication secretMessageApplication) {
        this.encoder = encoder;
        this.repo = repo;
        this.secretMessageApplication = secretMessageApplication;
    }

    @PostMapping("/createmessage")
    public ResponseEntity<String> createMessage(@RequestBody Map<String, String> request) {

        String message = request.get("message");
        String password = encoder.encode(request.get("password"));

        LocalDateTime expireAt = LocalDateTime.now().plusMinutes(Integer.parseInt(request.get("expirationminute")));
        Boolean selfdelete = Boolean.valueOf(request.get("selfdelete"));
        String uuid = UUID.randomUUID().toString();

        SecretMessage msg = new SecretMessage(
                message,
                password,
                expireAt,
                selfdelete,
                uuid
        );

        SecretMessage saved = repo.save(msg);

        return ResponseEntity.ok(uuid);
    }

    @PostMapping("/showmessage/{uuid}")
    public String showMessage(@PathVariable String uuid, @RequestBody Map<String, String> request) {
        String password = repo.findByUuid(uuid)
                .map(SecretMessage::getPassword)
                .orElse(null);
        String message = repo.findByUuid(uuid)
                .map(SecretMessage::getMessage)
                .orElse(null);
        Boolean selfdelete = repo.findByUuid(uuid)
                .map(SecretMessage::getSelfdelete)
                .orElse(null);
        if (encoder.matches(request.get("password"), password)) {
            if (selfdelete) {
                repo.findByUuid(uuid).ifPresent(repo::delete);
            }
            return message;
        }
        return "Password is wrong!";
    }

    @PostMapping("/checkExistance/{uuid}")
    public ResponseEntity<String> checkExistance(@PathVariable String uuid) {
        return repo.findByUuid(uuid)
                .map(secretMessage -> {
                    if (LocalDateTime.now().isAfter(secretMessage.getExpireAt())) {
                        repo.delete(secretMessage);
                        return ResponseEntity.status(410).body("Message has expired!");
                    }
                    return ResponseEntity.ok("Message exists!");
                })
                .orElseGet(() -> ResponseEntity.status(404).body("Message not found"));
    }

}

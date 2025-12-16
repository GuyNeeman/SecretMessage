package ch.neeman.guy.secretmessage;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
public class SecretMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    private String password;
    private LocalDateTime expireAt;
    private Boolean selfdelete;

    public SecretMessage(String message, String password, LocalDateTime expireAt, Boolean selfdelete, String uuid) {
        this.uuid = uuid;
        this.message = message;
        this.password = password;
        this.expireAt = expireAt;
        this.selfdelete = selfdelete;
    }

}
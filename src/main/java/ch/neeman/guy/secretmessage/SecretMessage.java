package ch.neeman.guy.secretmessage;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int expirationDay;
    private int expirationHour;
    private int expirationMinute;
    private Boolean selfdelete;

    public SecretMessage(String message, String password, int expirationDay, int expirationHour, int expirationMinute, Boolean selfdelete, String uuid) {
        this.uuid = uuid;
        this.message = message;
        this.password = password;
        this.expirationDay = expirationDay;
        this.expirationHour = expirationHour;
        this.expirationMinute = expirationMinute;
        this.selfdelete = selfdelete;
    }

}
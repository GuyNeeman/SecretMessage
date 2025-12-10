package ch.neeman.guy.secretmessage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecretMessageRepository extends JpaRepository<SecretMessage, Long> {
    Optional<SecretMessage> findByUuid(String uuid);
}

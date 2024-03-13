package ru.zderev.SEO.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zderev.SEO.core.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}

package ru.zderev.seo.core.messages.web;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zderev.seo.core.messages.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}

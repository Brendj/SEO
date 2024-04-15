package ru.zderev.seo.core.user.web;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zderev.seo.core.user.User;

public interface UserRepo extends JpaRepository<User, Long> {
}

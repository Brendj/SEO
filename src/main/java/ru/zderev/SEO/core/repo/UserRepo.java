package ru.zderev.SEO.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zderev.SEO.core.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
}

package com.mysite.sbb.domain.user.Repository;

import com.mysite.sbb.domain.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
}

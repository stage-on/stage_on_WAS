package com.stageon.stageonwas.domain.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stageon.stageonwas.domain.auth.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailAndProvider(String email, String provider); // 서비스명으로 사용자에 관한 정보 검색
}
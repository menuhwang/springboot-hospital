package com.likelion.hospital.repository;

import com.likelion.hospital.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

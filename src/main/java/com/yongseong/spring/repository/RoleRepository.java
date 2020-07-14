package com.yongseong.spring.repository;

import java.util.Optional;

import com.yongseong.spring.entity.Role;
import com.yongseong.spring.util.ERole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
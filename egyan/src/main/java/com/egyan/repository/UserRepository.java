package com.egyan.repository;

import com.egyan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
public interface <RepositoryName> extends JpaRepository<EntityClass, IdType>
 */


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
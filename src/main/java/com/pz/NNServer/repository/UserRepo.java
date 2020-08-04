package com.pz.NNServer.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pz.NNServer.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

	Optional<User> findByName(String name);
}

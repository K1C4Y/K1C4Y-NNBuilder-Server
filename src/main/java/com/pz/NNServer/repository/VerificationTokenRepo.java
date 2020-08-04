package com.pz.NNServer.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pz.NNServer.model.VerificationToken;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken, UUID> {

	Optional<VerificationToken> findByToken(String token);
}

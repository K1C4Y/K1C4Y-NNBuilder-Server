package com.pz.NNServer.repository;

import com.pz.NNServer.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken, UUID> {
}

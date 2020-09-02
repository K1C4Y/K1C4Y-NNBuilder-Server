package com.pz.NNServer.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pz.NNServer.model.NNModel;

@Repository
public interface NNModelRepo extends JpaRepository<NNModel, UUID> {
	Optional<NNModel> findByModelId(int modelId);
}

package com.pz.NNServer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pz.NNServer.model.Layer;
import com.pz.NNServer.model.User;
@Repository
public interface LayerRepo extends JpaRepository<Layer, UUID> {
	Optional<Layer> findByLayerId(int layerId);
	List<Layer> findAllByUser(User user);
}

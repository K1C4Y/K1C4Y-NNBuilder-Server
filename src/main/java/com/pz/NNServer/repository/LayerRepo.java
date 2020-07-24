package com.pz.NNServer.repository;

import com.pz.NNServer.model.Layer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LayerRepo extends JpaRepository<Layer, UUID> {
}

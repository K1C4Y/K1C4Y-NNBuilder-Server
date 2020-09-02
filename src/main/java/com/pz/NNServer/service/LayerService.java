package com.pz.NNServer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.pz.NNServer.dto.LayerDto;
import com.pz.NNServer.exceptions.NNBuilderException;
import com.pz.NNServer.model.Layer;
import com.pz.NNServer.model.User;
import com.pz.NNServer.repository.LayerRepo;
import com.pz.NNServer.repository.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LayerService {
	public final UserRepo userRepo;
	public final LayerRepo layerRepo;
	private final AuthService authService;
	
	@Transactional
	public void addLayer(LayerDto layerDto) {
		Layer layer = new Layer(); 
		layer.setActivation(layerDto.getActivation());
		layer.setPrimNum(layerDto.getPrimNum());
		layer.setSecNum(layerDto.getSecNum());
		layer.setType(layerDto.getType());
		User currentUser = authService.getCurrentUser();
		layer.setUser(currentUser);
		layerRepo.save(layer);
	}

	public Layer getLayer(int layerId) {
		User user = authService.getCurrentUser();
		Layer layer = user.getLayers().get(layerId);
		if(layer != null) {
			return layer;
		}else {
			throw new NNBuilderException("Layer with id " + layerId + " not found.");
		}
		
		
//		return layerRepo.findByLayerId(layerId).orElseThrow(
//				() -> new NNBuilderException("Layer with id " + layerId + " not found.")
//				);
	}

	public List<Layer> getUserLayers() {
		User user = authService.getCurrentUser();
		List<Layer> layers = user.getLayers();
		return layers;
	}

}

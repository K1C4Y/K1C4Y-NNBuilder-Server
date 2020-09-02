package com.pz.NNServer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pz.NNServer.dto.LayerDto;
import com.pz.NNServer.model.Layer;
import com.pz.NNServer.service.LayerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/layer")
public class LayerController {
	public final LayerService layerService;
	
	@PostMapping("/addLayer")
	public ResponseEntity<String> addLayer(@RequestBody LayerDto layerDto){
		layerService.addLayer(layerDto);
		return new ResponseEntity<String>("Layer added", HttpStatus.OK);
	}
	
	@GetMapping("/getLayer/{layerId}")
	public Layer getLayer(@PathVariable int layerId){
		layerService.getLayer(layerId);
		return layerService.getLayer(layerId);
	}
	
	@GetMapping("/getUserLayers")
	public List<Layer> getUserLayers(){
		return layerService.getUserLayers();
		
	}
}

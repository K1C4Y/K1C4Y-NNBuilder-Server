package com.pz.NNServer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pz.NNServer.dto.NNLayersDto;
import com.pz.NNServer.dto.NNModelDto;
import com.pz.NNServer.service.ModelService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/model")
public class ModelController {
	private final ModelService modelService;
	
//	@PostMapping("/printModel")
//	public ResponseEntity<String> printModel(@RequestBody String modelName){
//		modelService.printModel(modelName);
//		return null;
//		
//	}
	
	@PostMapping("/addModel")
	public ResponseEntity<String> addModel(@RequestBody NNModelDto nnModelDto){
		modelService.addNNModel(nnModelDto);
		return new ResponseEntity<String>("Added model", HttpStatus.OK);
	}
	
	@PostMapping("/addLayers")
	public ResponseEntity<String> addLayers(@RequestBody NNLayersDto nnLayersDto){
		modelService.addLayers(nnLayersDto);
		return new ResponseEntity<String>("Added layer", HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteModel/{modelId}")
	public ResponseEntity<String> deleteModel(@PathVariable int modelId){
		modelService.deleteModel(modelId);
		return new ResponseEntity<String>("Deleting succesfull",HttpStatus.OK);
	}
	
}

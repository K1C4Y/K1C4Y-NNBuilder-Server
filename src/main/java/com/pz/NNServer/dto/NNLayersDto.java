package com.pz.NNServer.dto;

import java.util.ArrayList;

public class NNLayersDto {
	private int modelId;
	private ArrayList<Integer> layers;
	public NNLayersDto(int modelId, ArrayList<Integer> layers) {
		super();
		this.modelId = modelId;
		this.layers = layers;
	}
	
	public NNLayersDto() {
	}
	
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public ArrayList<Integer> getLayers() {
		return layers;
	}
	public void setLayers(ArrayList<Integer> layers) {
		this.layers = layers;
	}

}

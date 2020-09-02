package com.pz.NNServer.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.pz.NNServer.dto.NNLayersDto;
import com.pz.NNServer.dto.NNModelDto;
import com.pz.NNServer.exceptions.NNBuilderException;
import com.pz.NNServer.model.Layer;
import com.pz.NNServer.model.NNModel;
import com.pz.NNServer.model.User;
import com.pz.NNServer.repository.LayerRepo;
import com.pz.NNServer.repository.NNModelRepo;
import com.pz.NNServer.repository.UserRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ModelService {
	private final NNModelRepo nnModelRepo;
    private final UserRepo userRepo;
    private final LayerRepo layerRepo;
    private final AuthService authService;
//	public void printModel(String model) {
//			try {
//	            FileWriter writer = new FileWriter(model.getName() + "py");
//	            BufferedWriter bufferedWriter = new BufferedWriter(writer);
//	 
//	            bufferedWriter.write("import tensorflow as tf");
//	            bufferedWriter.newLine();
//	            bufferedWriter.write("import numpy as np");
//	            bufferedWriter.newLine();
//	            bufferedWriter.write(
//	                "print(\"Path to X training\")"
//	                + "path_to_x_training = input()"
//	                + "print(\"Path to Y training\")"
//	                + "path_to_y_training = input()"
//	                + "print(\"Path to X testing\")"
//	                + "path_to_x_testing = input()"
//	                + "print(\"Path to Y testing\")"
//	                + "path_to_y_testing = input()"
//	                );
//	            
//	            bufferedWriter.newLine();
//	            bufferedWriter.write(
//	                "def gatherData(path_to_x_training, path_to_y_training, path_to_x_testing, path_to_y_testing)"
//	                + "  x_train = np.genfromtxt(path_to_x_training)"
//	                + "  y_train = np.genfromtxt(path_to_y_training)"
//	                + "  x_test = np.genfromtxt(path_to_x_testing)"
//	                + "  y_test = np.genfromtxt(path_to_y_testing)"
//	                + "  return x_train,y_train,x_test,y_test"
//	                );
//	            
//	            bufferedWriter.newLine();
//	            bufferedWriter.write(
//	                "def create_model():"
//	                + "\n model = tf.keras.models.Sequential()");
//	            bufferedWriter.newLine();
//	                
//	            for(Layer layer : model.getLayers()){
//	            	bufferedWriter.write(
//	            		"jakiś tekst"	
//	            			);
//	            }
//	            
//	            bufferedWriter.close();
//	            bufferedWriter.close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	}

	public void addNNModel(NNModelDto nnModelDto) {
		NNModel model = new NNModel();
		model.setName(nnModelDto.getName());
		model.setBatch(nnModelDto.getBatch());
		model.setEpochs(nnModelDto.getEpochs());
		model.setSchuffle(nnModelDto.isSchuffle());
		model.setLoss(nnModelDto.getLoss());
		model.setOpt(nnModelDto.getOpt());
		User user = authService.getCurrentUser();
		model.setUser(user);
		nnModelRepo.save(model);
	}

	public void addLayers(NNLayersDto nnLayersDto) {
//		NNModel model = nnModelRepo.findByModelId(nnLayersDto.getModelId())
//				.orElseThrow(() -> new NNBuilderException("Model with id: " + nnLayersDto.getModelId() + " not found."));
		NNModel model = authService.getCurrentUser().getModels().get(nnLayersDto.getModelId());
		List<Layer> layers = new ArrayList<Layer>();
		int counter = 0;
		for(Integer id : nnLayersDto.getLayers()) {
			int[] remember = new int[nnLayersDto.getLayers().size()];
			if(IntStream.of(remember).anyMatch(x -> x == id)) {
				layers.add(layers.get(Arrays.asList(remember).indexOf(id)));
				counter++;	
			}else {
				layers.add(layerRepo.findByLayerId(id).orElseThrow(
						() -> new NNBuilderException("Exception during finding layer " + id)
						));
				remember[counter] = id;
				counter++;
			}
		}
		model.setLayers(layers);	
		nnModelRepo.save(model);
	}

	public void deleteModel(int modelId) {
		User user = authService.getCurrentUser();
		NNModel model = user.getModels().get(modelId);
		if(model != null) {
			nnModelRepo.delete(model);
		}else {
			throw new NNBuilderException("Model with id " + modelId + " not found!");
		}
	}
}

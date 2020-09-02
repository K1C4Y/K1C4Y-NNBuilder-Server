package com.pz.NNServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LayerDto {
	private String type;
	private float primNum;
	private float secNum;
	private String activation;
}

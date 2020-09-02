package com.pz.NNServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NNModelDto {
    private String name;
    private String opt;
    private String metrics;
    private String loss;
    private Integer epochs;
    private String batch;
    private boolean schuffle;
}

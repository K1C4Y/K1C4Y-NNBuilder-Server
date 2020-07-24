package com.pz.NNServer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Layer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int layerId;
    @NonNull
    private String type;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name= "modelId")
    private NNModel model;
    private float primNum;
    private float secNum;
    private String activation;

}

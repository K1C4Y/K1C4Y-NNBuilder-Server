package com.pz.NNServer.model;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @ManyToMany(fetch = LAZY)
    @JoinColumn(name= "modelId")
    private List<NNModel> model;
    private float primNum;
    private float secNum;
    private String activation;    
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name= "userId")
    private User user;

    
   

}

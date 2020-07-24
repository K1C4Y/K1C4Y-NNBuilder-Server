package com.pz.NNServer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NNModel {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int modelId;
    @NonNull
    private  String name;
    @OneToMany(fetch = LAZY)
    private List<Layer> layers;
    @Nullable
    private String opt;
    @Nullable
    private String metrics;
    @Nullable
    private String loss;
    @Nullable
    private Integer epochs;
    @Nullable
    private String batch;
    @Nullable
    private boolean schuffle;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="USERID")
    private User user;
}

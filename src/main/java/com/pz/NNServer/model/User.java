package com.pz.NNServer.model;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int userId;
    @NonNull
    private String name;
    @NonNull
    private String password;
    @NonNull
    private String email;
    @OneToMany(fetch = LAZY)
    private List<NNModel> models;
    private boolean enabled;
    @OneToMany(fetch = LAZY)
    private List<Layer> layers;
}

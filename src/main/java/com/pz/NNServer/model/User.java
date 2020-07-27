package com.pz.NNServer.model;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

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
}

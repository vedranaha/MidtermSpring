package com.ironhack.MidtermSpring.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    @OneToMany(mappedBy = "role")

    private Set<User> userSet;

    //CONSTRUCTORS
    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    //GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

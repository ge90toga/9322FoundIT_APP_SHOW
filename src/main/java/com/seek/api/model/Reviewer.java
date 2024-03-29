package com.seek.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by langley on 8/5/17.
 */
public class Reviewer {

    @JsonIgnore
    @Setter @Getter private Long id;

    @Setter @Getter private String username;

    @Setter @Getter private String name;

    @JsonIgnore
    @Setter @Getter private Job job;

    public Reviewer() {

    }

    public Reviewer(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public Reviewer(String username, String name, Job job) {
        this.username = username;
        this.name = name;
        this.job = job;
    }

    @Override
    public String toString() {
        return "Reviewer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

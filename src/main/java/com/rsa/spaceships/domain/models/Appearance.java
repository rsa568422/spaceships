package com.rsa.spaceships.domain.models;

import lombok.Data;

@Data
public class Appearance {

    private Integer id;

    private Spaceship spaceship;

    private Recording recording;
}

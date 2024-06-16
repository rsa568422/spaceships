package com.rsa.spaceships.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appearance {

    private Integer id;

    private Spaceship spaceship;

    private Recording recording;
}

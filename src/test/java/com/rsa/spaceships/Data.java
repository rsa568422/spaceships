package com.rsa.spaceships;

import com.rsa.spaceships.domain.models.Recording;
import com.rsa.spaceships.domain.models.Spaceship;
import com.rsa.spaceships.infrastructure.entities.RecordingEntity;
import com.rsa.spaceships.infrastructure.entities.SpaceshipEntity;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Data {

    public static final Map<Integer, String> EXPECTED_RECORDINGS = Stream.of(
            Pair.of(1, "Star Wars: Episodio I - La amenaza fantasma"),
            Pair.of(2, "Star Wars: Episodio II - El ataque de los clones"),
            Pair.of(3, "Star Wars: Episodio III - La venganza de los Sith"),
            Pair.of(4, "Star Wars: Episodio IV - Una nueva esperanza"),
            Pair.of(5, "Star Wars: Episodio V - El Imperio contraataca"),
            Pair.of(6, "Star Wars: Episodio VI - El retorno del Jedi"),
            Pair.of(7, "Star Wars: Episodio VII - El despertar de la Fuerza"),
            Pair.of(8, "Star Wars: Episodio VIII - Los últimos Jedi"),
            Pair.of(9, "Star Wars: Episodio IX - El ascenso de Skywalker")
    ).collect(Collectors.toMap(Pair::getLeft, Pair::getRight));

    public static Recording getExpectedRecording(Integer id) {
        return new Recording(id, EXPECTED_RECORDINGS.get(id));
    }

    public static RecordingEntity getExpectedRecordingEntity(Integer id) {
        var entity = new RecordingEntity();
        entity.setId(id);
        entity.setName(EXPECTED_RECORDINGS.get(id));
        return entity;
    }

    public static final Map<Integer, String> EXPECTED_SPACESHIPS = Stream.of(
            Pair.of(1, "X-Wing"),
            Pair.of(2, "Halcón Milenario"),
            Pair.of(3, "TIE Fighter"),
            Pair.of(4, "Destructor imperial"),
            Pair.of(5, "Estrella de la muerte")
    ).collect(Collectors.toMap(Pair::getLeft, Pair::getRight));

    public static Spaceship getExpectedSpaceship(Integer id) {
        return new Spaceship(id, EXPECTED_SPACESHIPS.get(id));
    }

    public static SpaceshipEntity getExpectedSpaceshipEntity(Integer id) {
        var entity = new SpaceshipEntity();
        entity.setId(id);
        entity.setName(EXPECTED_SPACESHIPS.get(id));
        return entity;
    }
}

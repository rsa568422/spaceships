package com.rsa.spaceships.infrastructure.adapters;

import com.rsa.spaceships.Data;
import com.rsa.spaceships.infrastructure.mappers.SpaceshipMapper;
import com.rsa.spaceships.infrastructure.repositories.SpaceshipJpaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
class SpaceshipRepositoryAdapterTest {

    @InjectMocks
    private SpaceshipRepositoryAdapter adapter;

    @Mock
    private SpaceshipJpaRepository repository;

    @Mock
    private SpaceshipMapper mapper;

    @Test
    void findAll() {
        var page = PageRequest.of(0, 10);
        var entities = Data.EXPECTED_SPACESHIPS.keySet().stream().map(Data::getExpectedSpaceshipEntity).toList();
        var expectedPage = new PageImpl<>(entities, page, 5);
        var expected = Data.EXPECTED_SPACESHIPS.keySet().stream().map(Data::getExpectedSpaceship).toList();

        when(repository.findAll(page)).thenReturn(expectedPage);
        when(mapper.toSpaceships(expectedPage)).thenReturn(expected);

        var actual = adapter.findAll(page);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );

        verify(repository, times(1)).findAll(any(Pageable.class));
        verify(repository, times(1)).findAll(page);
        verify(mapper, times(1)).toSpaceships(any());
        verify(mapper, times(1)).toSpaceships(expectedPage);
        noMoreInteractions();
    }

    @Test
    void findById() {
        var entity = Data.getExpectedSpaceshipEntity(1);
        var expected = Data.getExpectedSpaceship(1);

        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.toSpaceship(entity)).thenReturn(expected);

        var actual = adapter.findById(1);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertTrue(actual.isPresent()),
                () -> assertEquals(expected, actual.orElse(null))
        );

        verify(repository, times(1)).findById(any());
        verify(repository, times(1)).findById(1);
        verify(mapper, times(1)).toSpaceship(any());
        verify(mapper, times(1)).toSpaceship(entity);
        noMoreInteractions();
    }

    @Test
    void findByNameLike() {
        var name = "X-Wing";
        var entities = List.of(Data.getExpectedSpaceshipEntity(1));
        var expected = List.of(Data.getExpectedSpaceship(1));

        when(repository.findByNameLike(name)).thenReturn(entities);
        when(mapper.toSpaceships(entities)).thenReturn(expected);

        var actual = adapter.findByNameLike(name);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );

        verify(repository, times(1)).findByNameLike(any());
        verify(repository, times(1)).findByNameLike(name);
        verify(mapper, times(1)).toSpaceships(anyIterable());
        verify(mapper, times(1)).toSpaceships(entities);
    }

    @Test
    void findByRecordingNameLike() {
        var name = "Star Wars: Episodio IV - Una nueva esperanza";
        var entities = Data.EXPECTED_SPACESHIPS.keySet().stream().map(Data::getExpectedSpaceshipEntity).toList();
        var expected = Data.EXPECTED_SPACESHIPS.keySet().stream().map(Data::getExpectedSpaceship).toList();

        when(repository.findByRecordingNameLike(name)).thenReturn(entities);
        when(mapper.toSpaceships(entities)).thenReturn(expected);

        var actual = adapter.findByRecordingNameLike(name);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertIterableEquals(expected, actual)
        );

        verify(repository, times(1)).findByRecordingNameLike(any());
        verify(repository, times(1)).findByRecordingNameLike(name);
        verify(mapper, times(1)).toSpaceships(anyIterable());
        verify(mapper, times(1)).toSpaceships(entities);
    }

    @Test
    void save() {
        var spaceship = Data.getExpectedSpaceship(1);
        spaceship.setId(null);
        var spaceshipEntity = Data.getExpectedSpaceshipEntity(1);
        spaceship.setId(null);
        var expectedEntity = Data.getExpectedSpaceshipEntity(1);
        var expected = Data.getExpectedSpaceship(1);

        when(repository.save(spaceshipEntity)).thenReturn(expectedEntity);
        when(mapper.toSpaceshipEntity(spaceship)).thenReturn(spaceshipEntity);
        when(mapper.toSpaceship(expectedEntity)).thenReturn(expected);

        var actual = adapter.save(spaceship);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

        verify(repository, times(1)).save(any());
        verify(repository, times(1)).save(spaceshipEntity);
        verify(mapper, times(1)).toSpaceshipEntity(any());
        verify(mapper, times(1)).toSpaceshipEntity(spaceship);
        verify(mapper, times(1)).toSpaceship(any());
        verify(mapper, times(1)).toSpaceship(expectedEntity);
    }

    @Test
    void deleteById() {
        assertDoesNotThrow(() -> adapter.deleteById(1));

        verify(repository, times(1)).deleteById(any());
        verify(repository, times(1)).deleteById(1);
        noMoreInteractions();
    }

    private void noMoreInteractions() {
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }
}
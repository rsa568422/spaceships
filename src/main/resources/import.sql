INSERT INTO spaceships (name) VALUES ('X-Wing');
INSERT INTO spaceships (name) VALUES ('Halcón Milenario');
INSERT INTO spaceships (name) VALUES ('TIE Fighter');
INSERT INTO spaceships (name) VALUES ('Destructor imperial');
INSERT INTO spaceships (name) VALUES ('Estrella de la muerte');

INSERT INTO recordings (name) VALUES ('Star Wars: Episodio I - La amenaza fantasma');
INSERT INTO recordings (name) VALUES ('Star Wars: Episodio II - El ataque de los clones');
INSERT INTO recordings (name) VALUES ('Star Wars: Episodio III - La venganza de los Sith');
INSERT INTO recordings (name) VALUES ('Star Wars: Episodio IV - Una nueva esperanza');
INSERT INTO recordings (name) VALUES ('Star Wars: Episodio V - El Imperio contraataca');
INSERT INTO recordings (name) VALUES ('Star Wars: Episodio VI - El retorno del Jedi');
INSERT INTO recordings (name) VALUES ('Star Wars: Episodio VII - El despertar de la Fuerza');
INSERT INTO recordings (name) VALUES ('Star Wars: Episodio VIII - Los últimos Jedi');
INSERT INTO recordings (name) VALUES ('Star Wars: Episodio IX - El ascenso de Skywalker');

INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio I - La amenaza fantasma',         SELECT id FROM spaceships WHERE name = 'X-Wing');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio II - El ataque de los clones',    SELECT id FROM spaceships WHERE name = 'X-Wing');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio III - La venganza de los Sith',   SELECT id FROM spaceships WHERE name = 'X-Wing');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio IV - Una nueva esperanza',        SELECT id FROM spaceships WHERE name = 'X-Wing');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio V - El Imperio contraataca',      SELECT id FROM spaceships WHERE name = 'X-Wing');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio VI - El retorno del Jedi',        SELECT id FROM spaceships WHERE name = 'X-Wing');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio VII - El despertar de la Fuerza', SELECT id FROM spaceships WHERE name = 'X-Wing');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio VIII - Los últimos Jedi',         SELECT id FROM spaceships WHERE name = 'X-Wing');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio IX - El ascenso de Skywalker',    SELECT id FROM spaceships WHERE name = 'X-Wing');

INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio III - La venganza de los Sith',   SELECT id FROM spaceships WHERE name = 'Halcón Milenario');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio IV - Una nueva esperanza',        SELECT id FROM spaceships WHERE name = 'Halcón Milenario');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio V - El Imperio contraataca',      SELECT id FROM spaceships WHERE name = 'Halcón Milenario');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio VI - El retorno del Jedi',        SELECT id FROM spaceships WHERE name = 'Halcón Milenario');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio VII - El despertar de la Fuerza', SELECT id FROM spaceships WHERE name = 'Halcón Milenario');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio VIII - Los últimos Jedi',         SELECT id FROM spaceships WHERE name = 'Halcón Milenario');

INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio I - La amenaza fantasma',         SELECT id FROM spaceships WHERE name = 'TIE Fighter');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio II - El ataque de los clones',    SELECT id FROM spaceships WHERE name = 'TIE Fighter');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio III - La venganza de los Sith',   SELECT id FROM spaceships WHERE name = 'TIE Fighter');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio IV - Una nueva esperanza',        SELECT id FROM spaceships WHERE name = 'TIE Fighter');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio V - El Imperio contraataca',      SELECT id FROM spaceships WHERE name = 'TIE Fighter');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio VI - El retorno del Jedi',        SELECT id FROM spaceships WHERE name = 'TIE Fighter');

INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio I - La amenaza fantasma',         SELECT id FROM spaceships WHERE name = 'Destructor imperial');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio II - El ataque de los clones',    SELECT id FROM spaceships WHERE name = 'Destructor imperial');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio III - La venganza de los Sith',   SELECT id FROM spaceships WHERE name = 'Destructor imperial');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio IV - Una nueva esperanza',        SELECT id FROM spaceships WHERE name = 'Destructor imperial');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio V - El Imperio contraataca',      SELECT id FROM spaceships WHERE name = 'Destructor imperial');

INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio IV - Una nueva esperanza',        SELECT id FROM spaceships WHERE name = 'Estrella de la muerte');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio V - El Imperio contraataca',      SELECT id FROM spaceships WHERE name = 'Estrella de la muerte');
INSERT INTO appearances (recording_id, spaceship_id) VALUES (SELECT id FROM recordings WHERE name = 'Star Wars: Episodio VI - El retorno del Jedi',        SELECT id FROM spaceships WHERE name = 'Estrella de la muerte');

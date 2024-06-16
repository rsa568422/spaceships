package com.rsa.spaceships;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class SpaceshipsApplicationTests {

	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> SpringApplication.run(SpaceshipsApplication.class, ""));
	}

}

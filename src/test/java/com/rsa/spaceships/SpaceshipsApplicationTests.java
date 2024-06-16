package com.rsa.spaceships;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class SpaceshipsApplicationTests {

	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> SpaceshipsApplication.main(new String[0]));
	}

}

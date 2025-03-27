package com.md29.microservices.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Runs test on random port
class ProductServiceApplicationTests {
	@ServiceConnection // automatically sets mongodb connection uri from application.properties.
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");
	@LocalServerPort // injects random port into this variable.
	private Integer port;
	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}
	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
				    "name": "Samsung S24 Ultra",
				    "description": "Biggest camera",
				    "price": "90000"
				}
				""";
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("Samsung S24 Ultra"))
				.body("description", Matchers.equalTo("Biggest camera"))
				.body("price", Matchers.equalTo(90000));
	}

}

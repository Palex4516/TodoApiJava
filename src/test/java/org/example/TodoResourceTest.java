package org.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class TodoResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/Todo")
          .then()
             .statusCode(200)
             .body(is("Din mamma"));
    }

}
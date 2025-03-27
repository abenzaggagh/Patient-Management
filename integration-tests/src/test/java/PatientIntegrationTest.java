import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class PatientIntegrationTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:4004";
    }

    @Test
    void shouldReturnPatientsWithValidToken() {
        String loginPayload = """
                {
                    "email": "testuser@test.com",
                    "password": "password123"
                }
                """;

        Response response = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .response();

        String token = response.jsonPath().getString("token");

        Response patients = given()
                .contentType("application/json")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .when()
                .get("/api/patients")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Patients: " + patients.asPrettyString());
    }

    @Test
    void shouldCreatePatientWithValidToken() {
        String loginPayload = """
                {
                    "email": "testuser@test.com",
                    "password": "password123"
                }
                """;

        Response response = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .response();

        String token = response.jsonPath().getString("token");

        String patientPayload = """
                {
                    "name": "Amine Benzaggagh",
                    "email": "amine.benzaggagh@example.com",
                    "address": "001 Main Street",
                    "dateOfBirth": "1998-02-14"
                }
                """;

        Response creationResponse = given()
                .contentType("application/json")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(patientPayload)
                .when()
                .post("/api/patients")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Creation Response: " + creationResponse.asPrettyString());
    }

    @Test
    void shouldUpdatePatientWithValidToken() {
        String loginPayload = """
                {
                    "email": "testuser@test.com",
                    "password": "password123"
                }
                """;

        Response response = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .response();

        String token = response.jsonPath().getString("token");

        String patientPayload = """
                {
                    "name": "Amine Benzaggagh",
                    "email": "amine.benzaggagh@example.com",
                    "address": "987 Second Street",
                    "dateOfBirth": "1999-04-12"
                }
                """;

        Response creationResponse = given()
                .contentType("application/json")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(patientPayload)
                .when()
                .post("/api/patients")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        String patientId = creationResponse.jsonPath().getString("id");

        String updatedPatientPayload = """
                {
                    "name": "Amine BEN ZAGGAGH",
                    "email": "amine.benzaggagh@example.com",
                    "address": "001 Main Street",
                    "dateOfBirth": "1998-02-14"
                }
                """;

        Response updateResponse = given()
                .contentType("application/json")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .pathParam("patientId", patientId)
                .body(updatedPatientPayload)
                .when()
                .put("/api/patients/{patientId}")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        System.out.println("Update Response: " + updateResponse.asPrettyString());
    }

    @Test
    void shouldDeletePatientWithValidToken() {
        String loginPayload = """
                {
                    "email": "testuser@test.com",
                    "password": "password123"
                }
                """;

        Response response = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .response();

        String token = response.jsonPath().getString("token");

        String patientPayload = """
                {
                    "name": "Amine Benzaggagh",
                    "email": "amine.benzaggagh@example.com",
                    "address": "001 Main Street",
                    "dateOfBirth": "1998-02-14"
                }
                """;

        Response creationResponse = given()
                .contentType("application/json")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(patientPayload)
                .when()
                .post("/api/patients")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();

        String patientId = creationResponse.jsonPath().getString("id");

        given()
                .contentType("application/json")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .pathParam("patientId", patientId)
                .when()
                .delete("/api/patients/{patientId}")
                .then()
                .statusCode(204)
                .body(notNullValue())
                .extract()
                .response();
    }


}

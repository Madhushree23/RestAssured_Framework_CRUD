package utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import specs.Requestspec;

public class APIUtilis {

    // 🔹 Generic POST (for create + auth)
    public static Response post(String endpoint, Object body) {

        return given()
                .spec(Requestspec.getRequestSpec())
                .body(body)
        .when()
                .post(endpoint);
    }

    // 🔹 Generic GET
    public static Response get(String endpoint) {

        return given()
                .spec(Requestspec.getRequestSpec())
        .when()
                .get(endpoint);
    }

    // 🔹 PUT with Token
    public static Response put(String endpoint, Object body, String token) {

        return given()
                .spec(Requestspec.getRequestSpec())
                .header("Cookie", "token=" + token)
                .body(body)
        .when()
                .put(endpoint);
    }

    // 🔹 DELETE with Token
    public static Response delete(String endpoint, String token) {

        return given()
                .spec(Requestspec.getRequestSpec())
                .header("Cookie", "token=" + token)
        .when()
                .delete(endpoint);
    }

    // ==========================
    // 🚫 NEGATIVE SUPPORT METHODS
    // ==========================

    // 🔹 PUT without Token
    public static Response putWithoutToken(String endpoint, Object body) {

        return given()
                .spec(Requestspec.getRequestSpec())
                .body(body)
        .when()
                .put(endpoint);
    }

    // 🔹 DELETE without Token
    public static Response deleteWithoutToken(String endpoint) {

        return given()
                .spec(Requestspec.getRequestSpec())
        .when()
                .delete(endpoint);
    }

    // 🔹 PUT with Invalid Token
    public static Response putWithInvalidToken(String endpoint, Object body) {

        return given()
                .spec(Requestspec.getRequestSpec())
                .header("Cookie", "token=invalid123")
                .body(body)
        .when()
                .put(endpoint);
    }

    // 🔹 DELETE with Invalid Token
    public static Response deleteWithInvalidToken(String endpoint) {

        return given()
                .spec(Requestspec.getRequestSpec())
                .header("Cookie", "token=invalid123")
        .when()
                .delete(endpoint);
    }
}
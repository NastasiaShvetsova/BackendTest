import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ClassificationCuisine extends AbstractTest {

    @Test
    void ClassificationOfScandinavianCuisineByRecipeNameTest() {

        given()
                .queryParam("apiKey", getApiKey())
                .formParam("title", "swedish agges")
                .contentType("application/x-www-form-urlencoded")
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200)
                .body("cuisine", equalTo("Scandinavian"));
    }
        @Test
        void ClassificationOfIndianCuisineByRecipeNameTest() {

            given()
                    .queryParam("apiKey", getApiKey())
                    .formParam("title", "curry rice")
                    .contentType("application/x-www-form-urlencoded")
                    .when()
                    .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .body("cuisine", equalTo("Indian"));
        }
    @Test
    void ClassificationOfKoreanCuisineByRecipeNameTest() {

        given()
                .queryParam("apiKey", getApiKey())
                .formParam("title", "kimchi")
                .contentType("application/x-www-form-urlencoded")
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200)
                .body("cuisine", equalTo("Korean"));
    }
    @Test
    void ClassificationOfMexicanCuisineByRecipeNameTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .formParam("title", "burrito")
                .contentType("application/x-www-form-urlencoded")
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200)
                .body("cuisine", equalTo("Mexican"));
    }

    @Test
    void KitchenSearchInEnglishTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .formParam("title", "sushi")
                .contentType("application/x-www-form-urlencoded")
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200)
                .body("cuisine", equalTo("Japanese"));
    }
}
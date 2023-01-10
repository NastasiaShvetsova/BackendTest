import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ComplexSearch extends AbstractTest{

    @Test
    void SearchRecipesHasNotQuery(){
         given()
                 .queryParam("apiKey", getApiKey())
                 .when()
                 .get(getBaseUrl() + "recipes/complexSearch")
                 .then()
                 .assertThat()
                 .statusCode(200)
                 .body("offset", equalTo(0))
                 .body("number", equalTo(10));
    }
    @Test
    void GettingRecipesWithoutDetailedInformationTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("offset", "0")
                .queryParam("number", "1")
                .queryParam("addRecipeInformation", "false")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .body("offset", equalTo(0))
                .body("number", equalTo(1));
    };
    @Test
    void LicenseTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "Strawberry Banana")
                .queryParam("offset", "0")
                .queryParam("number", "1")
                .queryParam("addRecipeInformation", "true")
                .queryParam("limitLicense", "true")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .body("offset", equalTo(0))
                .body("number", equalTo(1));
    }
    @Test
    void GettingRecipesWithDetailedInformationTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("offset", "0")
                .queryParam("number", "1")
                .queryParam("addRecipeInformation", "true")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .body("offset", equalTo(0))
                .body("number", equalTo(1));
    }
    @Test
    void SearchForRecipeWithNutritionalValueTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("offset", "0")
                .queryParam("number", "1")
                .queryParam("addRecipeInformation", "true")
                .queryParam("addRecipeNutrition", "true")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .body("offset", equalTo(0))
                .body("number", equalTo(1));
    }
}

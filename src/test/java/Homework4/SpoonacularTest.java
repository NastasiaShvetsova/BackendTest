package Homework4;


import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;



public class SpoonacularTest extends AbstractTest {

    @Test
    void getClassificationOfScandinavianCuisineByRecipeNameTest() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title", "swedish agges")
                .post("https://api.spoonacular.com/recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Scandinavian"));
    }

    @Test
    void getClassificationOfIndianCuisineByRecipeNameTest() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title", "curry rice")
                .post("https://api.spoonacular.com/recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Indian"));
    }

    @Test
    void getClassificationOfKoreanCuisineByRecipeNameTest() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title", "kimchi")
                .post("https://api.spoonacular.com/recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Korean"));
    }

    @Test
    void getClassificationOfMexicanCuisineByRecipeNameTest() {
        Response response = given().spec(requestSpecification)
                .when()
                .formParam("title", "burrito")
                .post("https://api.spoonacular.com/recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Mexican"));
    }

    @Test
    void getKitchenSearchInEnglishTest() {
        Response response = given().spec(requestSpecification)
                .when()
                .queryParam("language", "en")
                .formParam("title", "sushi")
                .post("https://api.spoonacular.com/recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Japanese"));
    }

    @Test
    void GetRecipesWithoutDetailedInformationTest() {
        given().spec(getRequestSpecification())
                .when()
                .queryParam("offset", "0")
                .queryParam("number", "1")
                .queryParam("addRecipeInformation", "false")
                .get("https://api.spoonacular.com/recipes/complexSearch?addRecipeInformation=false")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void GetRecipesWithDetailedInformationTest() {
        given().spec(getRequestSpecification())
                .when()
                .queryParam("offset", "0")
                .queryParam("number", "1")
                .queryParam("addRecipeInformation", "true")
                .get("https://api.spoonacular.com/recipes/complexSearch?offset=0&nunber=1&addRecipeInformation=true")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void GetSearchForRecipeWithNutritionalValueTest() {
        given().spec(getRequestSpecification())
                .when()
                .queryParam("offset", "0")
                .queryParam("number", "1")
                .queryParam("addRecipeInformation", "true")
                .queryParam("addRecipeNutrition", "true")
                .get("https://api.spoonacular.com/recipes/complexSearch?addRecipeInformation=true&offset=0&number=1&addRecipeNutrition=true")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void GetLicenseTest() {
        given().spec(getRequestSpecification())
                .when()
                .queryParam("query", "Strawberry Banana")
                .queryParam("offset", "0")
                .queryParam("number", "1")
                .queryParam("addRecipeInformation", "true")
                .queryParam("limitLicense", "true")
                .get("https://api.spoonacular.com/recipes/complexSearch?query=Strawberry Banana&offset=0&number=1&limitLicense=true&addRecipeInformation=true")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getForVitaminCContentInTheRecipeTest() {
        given().spec(getRequestSpecification())
                .when()
                .queryParam("addRecipeInformation", "true")
                .queryParam("minVitaminC", "10")
                .queryParam("maxVitaminC", "20")
                .get("https://api.spoonacular.com/recipes/complexSearch?addRecipeInformation=true&maxVitaminC=10&maxVitamaxC=20&")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void testGetShoppingList() {
        given().spec(getRequestSpecification())
                .when()
                .pathParam("username", "dsky")
                .queryParam("hash", "4b5v4398573406")
                .get("https://api.spoonacular.com/mealplanner/dsky/shopping-list")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void testAddToShoppingList() {
        given().spec(requestSpecification)
                .when()
                .pathParam("username", "dsky")
                .queryParam("hash", "4b5v4398573406")
                .queryParam("item", "1 package baking powder")
                .queryParam("aisle", "Baking")
                .post("https://api.spoonacular.com/mealplanner/dsky/shopping-list/items")
                .then()
                .statusCode(200);
    }
    @Test
    void testComputeShoppingList(){
        given().spec(requestSpecification)
                .when()
                .post("https://api.spoonacular.com/mealplanner/shopping-list/compute")
                .then()
                .statusCode(200);
    }

    @Test
    void testDeleteFromShoppingList() {
        given().spec(requestSpecification)
                .when()
                .pathParam("username", "dsky")
                .pathParam("id", "15678")
                .queryParam("hash", "4b5v4398573406")
                .post("https://api.spoonacular.com/mealplanner/dsky/shopping-list/items/15678?hash=4b5v4398573406")
                .then()
                .statusCode(200);
    }
}
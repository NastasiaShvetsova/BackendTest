import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ShoppingList extends AbstractTest {

    @Test
    void addMealTest() {
        String id = given()
                .queryParam("hash", "2cd3ffb9eb7612e172045275313d7986f2de3a9e")
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 avocado.\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/cssfsfs/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", "2cd3ffb9eb7612e172045275313d7986f2de3a9e")
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/cssfsfs/items/" + id)
                .then()
                .statusCode(200);
    }
}
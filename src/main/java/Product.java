import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Product {
    public static Integer productId;
    String name;
    BigDecimal price;
    Integer id;

    Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    Product() {

    }

    public static Product product() {
        return new Product();
    }


    Product createProduct(String name, BigDecimal price, Integer expectedStatusCode) {
        productId = getLastItem() + 1;
        String requestPayLoad = new Gson().toJson(new Product(name, price));
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestPayLoad)
                .post("/product")
                .then()
                .statusCode(expectedStatusCode);
        return this;
    }

    Product updateExistingProduct(Integer recordId, String name, BigDecimal price, Integer expectedStatusCode) {
        String payLoad = new Gson().toJson(new Product(name, price));
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payLoad)
                .put("/product/" + recordId)
                .then()
                .statusCode(expectedStatusCode);
        return this;
    }


    Product validateRecord(Integer id, String name, BigDecimal price) {

        JsonPath response = RestAssured.given()
                .when().get("/product/" + id)
                .getBody().jsonPath();
        Product product = ConvertJsonPathToProduct(response);
        assertThat(product.price, equalTo(price));
        assertThat(product.name, equalTo(name));
        return this;
    }

    public Product verifyProductDelete() {
        Integer id = getLastItem();
        RestAssured.given()
                .when().delete("/product/" + id)
                .then().statusCode(204);
        RestAssured.given()
                .when().get("/product/" + id)
                .then().statusCode(404).body("id", Matchers.nullValue());
        return this;
    }

    public Integer getLastItem() {
        List<Integer> productIds = RestAssured.given()
                .when().get("/products").getBody()
                .jsonPath().getList("id");
        return productIds.get(productIds.size() - 1);
    }

    public Product GetLastItemDetails() {
        List<Integer> productIds = RestAssured.given()
                .when().get("/products").getBody()
                .jsonPath().getList("id");
        Integer lastItemId = productIds.get(productIds.size() - 1);
        JsonPath itemDetails = RestAssured.given()
                .get("/product/" + lastItemId).getBody().jsonPath();
        return ConvertJsonPathToProduct(itemDetails);
    }

     Product ConvertJsonPathToProduct(JsonPath itemDetails) {
         Product productDetails = new Product();
         productDetails.name = itemDetails.get("name");
         productDetails.id = Integer.valueOf((Integer) itemDetails.get("id"));
         productDetails.price = new BigDecimal(itemDetails.get("price").toString());
        return productDetails;
    }
}
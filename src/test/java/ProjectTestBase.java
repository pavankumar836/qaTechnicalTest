import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.BeforeClass;

public class ProjectTestBase {
    @BeforeClass
    public static void setup(){
        RestAssured.baseURI="http://localhost:5000/v1";
        RestAssured.given().contentType(ContentType.JSON);
        RestAssured.defaultParser = Parser.JSON;

    }
}

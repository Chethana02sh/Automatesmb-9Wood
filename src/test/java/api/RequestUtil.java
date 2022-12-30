package api;

import base.FilePaths;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.FileManager;

public class RequestUtil {


    public static Response getRequest(String endpoint){
       // RestAssured.baseURI= FileManager.getInstance(FilePaths.PROPERTY_FILE).getPropertyValue("url");
       return RestAssured.given().auth().basic("username","password")
               // .contentType(ContentType.JSON)
                .when()
                .get("https://mifp-dev.ballistixcrm.com/testsuite/api/1.php")

                .then().extract().response();
    }

    public static Response getFieldMetaData(String endpoint, String module){
        RestAssured.baseURI=FileManager.getInstance(FilePaths.PROPERTY_FILE).getPropertyValue("url");
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("module", module)
                .when()
                .get(endpoint)
                .then().extract().response();
    }
}

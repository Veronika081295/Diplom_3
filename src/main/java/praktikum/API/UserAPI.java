package praktikum.API;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserAPI {
    @Step("Create API user")
    public static Response createApiUser(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(RestApiConstants.REGISTER_PATH);
        return response;
    }

    @Step("loginUser response")
    public static Response loginUser(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(RestApiConstants.LOGIN_PATH);
        return response;
    }

    @Step("getAccessToken response")
    public static String getAccessToken(User user) {
        return loginUser(user).then().extract().path("accessToken");
    }

    @Step("Delete user")
    public static void deleteUser(String accessToken) {
        if (accessToken != null)
            given()
                    .header("Authorization", accessToken)
                    .when()
                    .delete(RestApiConstants.DELETE_PATH);
    }
}

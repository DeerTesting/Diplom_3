import io.qameta.allure.Step;
import configs.EndPoints;
import dto.User;

import static io.restassured.RestAssured.given;

public class ApiMethods {

    @Step("Delete user")
    public void deleteUser(String token){
         given()
                .header("Authorization",
                        token)
                .delete(EndPoints.USER_DELETE_PATCH);
    }

    @Step("User creation")
    public void postUserCreation(User user){
        given()
                .body(user)
                .when()
                .post(EndPoints.USER_CREATION);
    }

    @Step("get user token")
    public String getToken(User user){
        return  given()
                .body(user)
                .when()
                .post(EndPoints.USER_LOGIN)
                .then().extract().path("accessToken");
    }

}

package praktikum.pages;

import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static io.qameta.allure.model.Parameter.Mode.HIDDEN;
import static io.restassured.RestAssured.given;

public class UserApi {
    @Step("создание пользователя через апи")
    public String createStellarUser(User user) {
        String accessToken = given().log().all()
                .contentType(ContentType.JSON)
                .baseUri("https://stellarburgers.nomoreparties.site")
                .body(user)
                .when()
                .post("/api/auth/register")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");
        return accessToken;
    }

    @Step("удаление пользователя через апи")
    public void deleteStellarUser(@Param(mode=HIDDEN) String accessToken) {
        given().log().all()
                .baseUri("https://stellarburgers.nomoreparties.site")
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then().log().all()
                .assertThat()
                .statusCode(202);
    }
}

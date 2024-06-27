import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.Before;

import static configs.Url.HOST;

public abstract class  AbstractClass {

    @Before
    public void setUpRestAssured(){
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-type", "application/json")
                .setBaseUri(HOST)
                .build();
    }

}

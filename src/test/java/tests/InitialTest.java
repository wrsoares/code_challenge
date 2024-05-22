package tests;

import api.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static utils.Utils.writeLog;

public class InitialTest extends Request {

    @BeforeEach
    public void load() {
        super.uri = Utils.getProperty("uri");
    }

    @Test
    @DisplayName("Get posts")
    public void getPosts() throws IOException {
        GET();
        writeLog(response);
        Assertions.assertEquals(200, response.getStatusCode(), "Error: invalid status");
    }


}

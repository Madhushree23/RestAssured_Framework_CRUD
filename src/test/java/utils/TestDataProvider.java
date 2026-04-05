package utils;

import org.testng.annotations.DataProvider;
import pojo.AuthRequestBooker;

public class TestDataProvider {

    @DataProvider(name = "authData")
    public Object[][] getAuthData() {
        return new Object[][] {
            { "admin", "password123" },  // valid
            { "wrong", "wrong" },        // invalid
            { "", "" }                  // empty
        };
    }
}
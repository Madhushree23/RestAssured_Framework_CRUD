package base;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import utils.Configreader;

public class BaseTest {

	@BeforeClass
	public void setup() {
		RestAssured.baseURI=Configreader.get("baseurl");
	}
}

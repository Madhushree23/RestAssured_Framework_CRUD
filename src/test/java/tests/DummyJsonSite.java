package tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import base.BaseTest;
import pojo.LoginRequest;
import pojo.Product;
import specs.Requestspec;
import specs.Responsespec;

public class DummyJsonSite extends BaseTest {
	
	String token;
	int productid;
	
	@Test(priority = 1)
	public void login() {
		
	LoginRequest l=new LoginRequest();
	  l.setUsername("emilys");
      l.setPassword("emilyspass");
      
      token=given().spec(Requestspec.getRequestSpec()).body(l)
      .when().post("/auth/login")
      .then().spec(Responsespec.successspec()).extract().path("accessToken");
      System.out.println("token:" + token);
	}
	//CREATE PRODUCT
	@Test(priority=2)
	public void createProduct() {
		Product p=new Product();
		p.setTitle("Madhushree");
		
		int productid =
				given()
				.spec(Requestspec.getRequestSpec())
				   
				    .header("Authorization", "Bearer " + token)
				   
				    .body(p)
				.when()
				    .post("/products/add")   // ✅ IMPORTANT (not /products)
				.then()
				   .spec(Responsespec.createspec())
				    .log().all()
				    .extract().path("id");

				//System.out.println("Product ID: " + productid);
	}
	
	  // 🔹 3. GET PRODUCT
    @Test(priority = 3)
    public void getProduct() {

        given()
                .spec(Requestspec.getRequestSpec())
                .header("Authorization", "Bearer " + token)
        .when()
                .get("/products/")
        .then()
                .spec(Responsespec.successspec());
    }
    // 🔹 4. UPDATE PRODUCT
    @Test(priority = 4)
    public void updateProduct() {

        Product p = new Product();
        p.setTitle("Updated Product");

        given()
                .spec(Requestspec.getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .body(p)
        .when()
                .put("/products/1")
        .then()
                .spec(Responsespec.successspec())
                .body("title", equalTo("Updated Product"));
    }

    // 🔹 5. DELETE PRODUCT
    @Test(priority = 5)
    public void deleteProduct() {

        given()
                .spec(Requestspec.getRequestSpec())
                .header("Authorization", "Bearer " + token)
        .when()
                .delete("/products/1")
        .then()
                .spec(Responsespec.deletespec());
    }
}

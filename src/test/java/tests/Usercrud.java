package tests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import pojo.User;
import specs.Requestspec;
import specs.Responsespec;
import static org.hamcrest.Matchers.equalTo;

public class Usercrud extends BaseTest {
	public int userid;
	@Test(priority = 1)
		public void createUser() {
			User user=new User();
			 user.setName("Madhu");
		        user.setUsername("madhu123");
		        user.setEmail("madhu@test.com");
		        
			userid=given().spec(Requestspec.getRequestSpec()).body(user)
			.when().post("/users")
			.then().spec(Responsespec.createspec())
					.body("name",equalTo("Madhu"))
					.extract().path("id");
			 System.out.println("User ID: " + userid);
		}
	
		@Test(priority=2)
		public void getUser() {
			given().spec(Requestspec.getRequestSpec()).relaxedHTTPSValidation()
			.when().get("/users/1") 
			.then().spec(Responsespec.successspec());
			
		}
	
	@Test(priority=3)
	public void updateUser() {
		User user=new User();
		user.setName("Madhu updated");
		 user.setUsername("madhu12345");
	        user.setEmail("madhusssssssss@test.com");
		given().spec(Requestspec.getRequestSpec()).body(user).relaxedHTTPSValidation()
		//.when().put("/users" + userid)
		.when().put("/users/1")
		.then().spec(Responsespec.successspec()).body("name", equalTo("Madhu updated"));
		
	}
	
	@Test(priority=4)
	public void updatePartialUser() {
		User user=new User();
		user.setName("Madhu patch");
		 
		given().spec(Requestspec.getRequestSpec()).body(user).relaxedHTTPSValidation()
		//.when().patch("/users" + userid)
		.when().patch("/users/1")
		.then().spec(Responsespec.successspec()).body("name", equalTo("Madhu patch"));
		
	}
	
	  // 🔹 DELETE USER
    @Test(priority = 5)
    public void deleteUser(){

        given().log().all().relaxedHTTPSValidation()
                .spec(Requestspec.getRequestSpec())
        .when()
                //.delete("/users/" + userid)
                 .delete("/users/1")
                
        .then().log().all()
                .spec(Responsespec.deletespec());
        System.out.println("deleted");
    }
	

	
}

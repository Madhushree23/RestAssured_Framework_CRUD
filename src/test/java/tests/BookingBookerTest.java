//This IS 
//this is booking booker	
package tests;
	
	import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.response.Response;
import pojo.AuthRequestBooker;
import pojo.BookingBooker;
import pojo.BookingResponseDeseralization;
import specs.Requestspec;
import specs.Responsespec;
import utils.APIUtilis;
import utils.TestData;
	
	@Listeners(utils.TestListener.class)
	public class BookingBookerTest extends BaseTest {
	/*
		String token;
		int bookingId;
	
		// 🔐 AUTH
		@Test
		public void getToken() {
	
			AuthRequestBooker auth = new AuthRequestBooker();
			auth.setUsername("admin");
			auth.setPassword("password123");
	
			token = given().spec(Requestspec.getRequestSpec()).body(auth).when().post("/auth").then()
					.spec(Responsespec.successspec()).log().all().extract().path("token");
	
			System.out.println("Token: " + token);
		}
	
		// ➕ CREATE
		@Test(dependsOnMethods = "getToken")
		public void createBooking() {
	
			BookingBooker booking = TestData.createBooking();
	
			/*
			 * bookingId = given() .spec(Requestspec.getRequestSpec()) .body(booking)
			 * .when() .post("/booking") .then() .log().all() .statusCode(anyOf(is(200),
			 * is(201))) // flexible fix .body("booking.firstname",
			 * equalTo(booking.getFirstname())) .extract().path("bookingid");//THIS IS
			 * NORMAL
			 * 
			 * System.out.println("Booking ID: " + bookingId);
			 */
	/*
			// DESERALIZATION
			BookingResponseDeseralization response = given().spec(Requestspec.getRequestSpec()).body(booking).when()
					.post("/booking").then().statusCode(anyOf(is(200), is(201))).log().all().extract()
					.as(BookingResponseDeseralization.class); // ✅ HERE
	
			// ✅ Now use response
			bookingId = response.getBookingid();
	
			System.out.println("Booking ID: " + bookingId);
	
			// ✅ Validation using POJO
			assert response.getBooking().getFirstname().equals(booking.getFirstname());
		}
	
		// 🔍 GET
		@Test(dependsOnMethods = "createBooking")
		public void getBooking() {
	
			given().spec(Requestspec.getRequestSpec()).when().get("/booking/" + bookingId).then()
					.spec(Responsespec.successspec()).log().all().body("firstname", notNullValue());
		}
	
		// ✏️ UPDATE
		@Test(dependsOnMethods = "getBooking")
		public void updateBooking() {
	
			BookingBooker updated = TestData.updateBooking();
	
			given().spec(Requestspec.getRequestSpec()).header("Cookie", "token=" + token).body(updated).when()
					.put("/booking/" + bookingId).then().spec(Responsespec.successspec())
	
					// 🔥 Business validation
					.body("firstname", equalTo(updated.getFirstname()))
	
					// 🔥 Structure validation
					.body(matchesJsonSchemaInClasspath("bookingSchema.json"))
	
					.log().all();
		}
	
		// ❌ DELETE
		@Test(dependsOnMethods = "updateBooking")
		public void deleteBooking() {
	
			given().spec(Requestspec.getRequestSpec()).header("Cookie", "token=" + token).when()
					.delete("/booking/" + bookingId).then().statusCode(anyOf(is(200), is(201))) // flexible fix
					.log().all();
		}
	
		// ==========================
		// 🚫 NEGATIVE TESTS
		// ==========================
	
		// 🚫 INVALID AUTH
		@Test(dependsOnMethods = "deleteBooking")
		public void invalidAuth() {
	
			AuthRequestBooker auth = new AuthRequestBooker();
			auth.setUsername("wrong");
			auth.setPassword("wrong");
	
			given().spec(Requestspec.getRequestSpec()).body(auth).when().post("/auth").then().statusCode(200).log().all();
		}
	
		// 🚫 GET INVALID BOOKING ID
		@Test(dependsOnMethods = "invalidAuth")
		public void getInvalidBooking() {
	
			given().spec(Requestspec.getRequestSpec()).when().get("/booking/999999").then().statusCode(404).log().all();
		}
	
		// 🚫 UPDATE WITHOUT TOKEN
		@Test(dependsOnMethods = "getInvalidBooking")
		public void updateWithoutToken() {
	
			BookingBooker updated = TestData.updateBooking();
	
			given().spec(Requestspec.getRequestSpec()).body(updated).when().put("/booking/" + bookingId).then()
					.statusCode(403).log().all();
		}
	
		// 🚫 DELETE WITHOUT TOKEN
		@Test(dependsOnMethods = "updateWithoutToken")
		public void deleteWithoutToken() {
	
			given().spec(Requestspec.getRequestSpec()).when().delete("/booking/" + bookingId).then().statusCode(403).log()
					.all();
		}
	
		// 🚫 UPDATE WITH INVALID TOKEN
		@Test(dependsOnMethods = "deleteWithoutToken")
		public void updateWithInvalidToken() {
	
			BookingBooker updated = TestData.updateBooking();
	
			given().spec(Requestspec.getRequestSpec()).header("Cookie", "token=invalid123").body(updated).when()
					.put("/booking/" + bookingId).then().statusCode(403).log().all();
		}
	
		// 🚫 DELETE WITH INVALID TOKEN
		@Test(dependsOnMethods = "updateWithInvalidToken")
		public void deleteWithInvalidToken() {
	
			given().spec(Requestspec.getRequestSpec()).header("Cookie", "token=invalid123").when()
					.delete("/booking/" + bookingId).then().statusCode(403).log().all();
		}
	 
		// 🚫 CREATE INVALID PAYLOAD
		@Test(dependsOnMethods = "deleteWithInvalidToken")
		public void createInvalidBooking() {
	
			String invalidPayload = "{ \"firstname\": 123 }";
	
			given().spec(Requestspec.getRequestSpec()).body(invalidPayload).when().post("/booking").then().statusCode(500)
					.log().all();
		}
	}
	
	*/
	

    String token;
    int bookingId;

    // 🔐 AUTH
    @Test
    public void getToken() {

        AuthRequestBooker auth = new AuthRequestBooker();
        auth.setUsername("admin");
        auth.setPassword("password123");

        Response response = APIUtilis.post("/auth", auth);

        response.then()
                .log().all()
                .statusCode(200);

        token = response.path("token");

        System.out.println("Token: " + token);
    }

    // ➕ CREATE BOOKING
    @Test(dependsOnMethods = "getToken")
    public void createBooking() {

        BookingBooker booking = TestData.createBooking();

        BookingResponseDeseralization response =
                APIUtilis.post("/booking", booking)
                        .then()
                        .log().all()
                        .statusCode(anyOf(is(200), is(201)))
                        .extract()
                        .as(BookingResponseDeseralization.class);

        bookingId = response.getBookingid();

        System.out.println("Booking ID: " + bookingId);

        // ✅ Validation
        assert response.getBooking().getFirstname()
                .equals(booking.getFirstname());
    }

    // 🔍 GET BOOKING
    @Test(dependsOnMethods = "createBooking")
    public void getBooking() {

        APIUtilis.get("/booking/" + bookingId)
                .then()
                .log().all()
                .statusCode(200)
                .body("firstname", notNullValue());
    }

    // ✏️ UPDATE BOOKING
    @Test(dependsOnMethods = "getBooking")
    public void updateBooking() {

        BookingBooker updated = TestData.updateBooking();

        APIUtilis.put("/booking/" + bookingId, updated, token)
                .then()
                .log().all()
                .statusCode(200)

                // ✅ Business validation
                .body("firstname", equalTo(updated.getFirstname()))

                // ✅ Schema validation (make sure file is in resources)
                .body(matchesJsonSchemaInClasspath("bookingSchema.json"));
    }

    // ❌ DELETE BOOKING
    @Test(dependsOnMethods = "updateBooking")
    public void deleteBooking() {

        APIUtilis.delete("/booking/" + bookingId, token)
                .then()
                .log().all()
                .statusCode(anyOf(is(200), is(201)));
    }

    // ==========================
    // 🚫 NEGATIVE TESTS
    // ==========================

    // 🚫 INVALID AUTH
    @Test(dependsOnMethods = "deleteBooking")
    public void invalidAuth() {

        AuthRequestBooker auth = new AuthRequestBooker();
        auth.setUsername("wrong");
        auth.setPassword("wrong");

        APIUtilis.post("/auth", auth)
                .then()
                .log().all()
                .statusCode(200); // API behavior
    }

    // 🚫 GET INVALID BOOKING
    @Test(dependsOnMethods = "invalidAuth")
    public void getInvalidBooking() {

        APIUtilis.get("/booking/999999")
                .then()
                .log().all()
                .statusCode(404);
    }

    // 🚫 UPDATE WITHOUT TOKEN
    @Test(dependsOnMethods = "getInvalidBooking")
    public void updateWithoutToken() {

        BookingBooker updated = TestData.updateBooking();

        APIUtilis.putWithoutToken("/booking/" + bookingId, updated)
                .then()
                .log().all()
                .statusCode(403);
    }

    // 🚫 DELETE WITHOUT TOKEN
    @Test(dependsOnMethods = "updateWithoutToken")
    public void deleteWithoutToken() {

        APIUtilis.deleteWithoutToken("/booking/" + bookingId)
                .then()
                .log().all()
                .statusCode(403);
    }

    // 🚫 UPDATE WITH INVALID TOKEN
    @Test(dependsOnMethods = "deleteWithoutToken")
    public void updateWithInvalidToken() {

        BookingBooker updated = TestData.updateBooking();

        APIUtilis.putWithInvalidToken("/booking/" + bookingId, updated)
                .then()
                .log().all()
                .statusCode(403);
    }

    // 🚫 DELETE WITH INVALID TOKEN
    @Test(dependsOnMethods = "updateWithInvalidToken")
    public void deleteWithInvalidToken() {

        APIUtilis.deleteWithInvalidToken("/booking/" + bookingId)
                .then()
                .log().all()
                .statusCode(403);
    }

    // 🚫 INVALID PAYLOAD
    @Test(dependsOnMethods = "deleteWithInvalidToken")
    public void createInvalidBooking() {

        String invalidPayload = "{ \"firstname\": 123 }";

        APIUtilis.post("/booking", invalidPayload)
                .then()
                .log().all()
                .statusCode(500);
    }
}

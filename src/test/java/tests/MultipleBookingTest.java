
	package tests;

	import static io.restassured.RestAssured.given;
	import static org.hamcrest.Matchers.anyOf;
	import static org.hamcrest.Matchers.is;

	import java.util.ArrayList;
	import java.util.List;

	import org.testng.annotations.Test;

	import base.BaseTest;
	import pojo.AuthRequestBooker;
	import pojo.BookingBooker;
	import pojo.BookingResponseDeseralization;
	import specs.Requestspec;
	import utils.TestData;
	import utils.TestDataProvider;

	public class MultipleBookingTest extends BaseTest {

	    String token;
	    List<Integer> bookingIds = new ArrayList<>();

	    // 🔐 AUTH
	    @Test
	    public void getToken() {

	        AuthRequestBooker auth = new AuthRequestBooker();
	        auth.setUsername("admin");
	        auth.setPassword("password123");

	        token =
	        given()
	            .spec(Requestspec.getRequestSpec())
	            .body(auth)
	        .when()
	            .post("/auth")
	        .then()
	            .extract().path("token");

	        System.out.println("Token: " + token);
	    }

	    // ➕ CREATE MULTIPLE BOOKINGS
	    @Test(dataProvider = "authData", 
	          dataProviderClass = TestDataProvider.class,
	          dependsOnMethods = "getToken")
	    public void createMultipleBookings(String firstname, String lastname) {

	        BookingBooker booking = new BookingBooker();
	        booking.setFirstname(firstname);
	        booking.setLastname(lastname);
	        booking.setTotalprice(1000);
	        booking.setDepositpaid(true);

	        pojo.BookingDatesBooker dates = new pojo.BookingDatesBooker();
	        dates.setCheckin("2024-01-01");
	        dates.setCheckout("2024-01-10");

	        booking.setBookingdates(dates);
	        booking.setAdditionalneeds("Breakfast");

	        BookingResponseDeseralization response =
	            given()
	                .spec(Requestspec.getRequestSpec())
	                .body(booking)
	            .when()
	                .post("/booking")
	            .then()
	                .statusCode(anyOf(is(200), is(201)))
	                .log().all()
	                .extract().as(BookingResponseDeseralization.class);

	        int id = response.getBookingid();
	        bookingIds.add(id);

	        System.out.println("Created ID: " + id);
	    }

	    // ✏️ UPDATE ALL
	    @Test(dependsOnMethods = "createMultipleBookings")
	    public void updateAllBookings() {

	        BookingBooker updated = TestData.updateBooking();

	        for (int id : bookingIds) {

	            given()
	                .spec(Requestspec.getRequestSpec())
	                .header("Cookie", "token=" + token)
	                .body(updated)
	            .when()
	                .put("/booking/" + id)
	            .then()
	                .statusCode(anyOf(is(200), is(201)))
	                .log().all();

	            System.out.println("Updated ID: " + id);
	        }
	    }

	    // ❌ DELETE ALL
	    @Test(dependsOnMethods = "updateAllBookings")
	    public void deleteAllBookings() {

	        for (int id : bookingIds) {

	            given()
	                .spec(Requestspec.getRequestSpec())
	                .header("Cookie", "token=" + token)
	            .when()
	                .delete("/booking/" + id)
	            .then()
	                .statusCode(anyOf(is(200), is(201)))
	                .log().all();

	            System.out.println("Deleted ID: " + id);
	        }
	    }
	}

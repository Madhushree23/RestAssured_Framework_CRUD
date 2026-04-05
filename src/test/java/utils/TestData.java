package utils;
import pojo.BookingBooker;
import pojo.BookingDatesBooker;
public class TestData {

	

	

	    public static BookingBooker createBooking() {

	        BookingDatesBooker dates = new BookingDatesBooker();
	        dates.setCheckin("2024-01-01");
	        dates.setCheckout("2024-01-10");

	        BookingBooker booking = new BookingBooker();
	        booking.setFirstname("Madhu");
	        booking.setLastname("Tester");
	        booking.setTotalprice(1000);
	        booking.setDepositpaid(true);
	        booking.setBookingdates(dates);
	        booking.setAdditionalneeds("Breakfast");

	        return booking;
	    }

	    public static BookingBooker updateBooking() {

	        BookingDatesBooker dates = new BookingDatesBooker();
	        dates.setCheckin("2024-02-01");
	        dates.setCheckout("2024-02-10");

	        BookingBooker booking = new BookingBooker();
	        booking.setFirstname("Updated");
	        booking.setLastname("User");
	        booking.setTotalprice(2000);
	        booking.setDepositpaid(false);
	        booking.setBookingdates(dates);
	        booking.setAdditionalneeds("Lunch");

	        return booking;
	    }
	
}

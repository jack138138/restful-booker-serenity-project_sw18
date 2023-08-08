package com.restful.booker.userinfo;

import com.restful.booker.constants.Endpoints;
import com.restful.booker.model.BookingDates;
import com.restful.booker.model.BookingPojoFinal;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class BookingSteps {
    static GetToken getToken = new GetToken();
    static String token = getToken.getToken();


    @Step("Get all bookings information")
    public ValidatableResponse getAllBooking() {
        return SerenityRest.given()
                .when()
                .get(Endpoints.GET_ALL_BOOKING)
                .then();


    }

    @Step("Create Booking")
    public ValidatableResponse createBooking(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin,
                                             String chekout, String additionalneed) {


        BookingPojoFinal bookingPojoFinal = new BookingPojoFinal();
        BookingDates bookingdates = new BookingDates();

        bookingdates.setCheckin(checkin);
        bookingdates.setCheckout(chekout);

        bookingPojoFinal.setFirstname(firstname);
        bookingPojoFinal.setLastname(lastname);
        bookingPojoFinal.setTotalprice(totalprice);
        bookingPojoFinal.setDepositpaid(depositpaid);
        bookingPojoFinal.setBookingdates(bookingdates);
        bookingPojoFinal.setAdditionalneeds(additionalneed);

        return  SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .when()
                .body(bookingPojoFinal)
                .post(Endpoints.CREATE_BOOKING)
                .then();


    }

    @Step("Verify Newly Create Booking")
    public ValidatableResponse VerifyBooking(int bookingid){
        System.out.println(bookingid);
        return  SerenityRest.given().log().all()
                //.header("Authorization","Bearer f4a966cfb8b1f8762386c84c483edfe3e4d73f9b92bd3e88426ca701e310525b")
                .pathParam("bookingID",bookingid)
                .when()
                .get(Endpoints.GET_BOOKING_BY_ID)
                .then();

    }

    @Step("Update Booking")
    public ValidatableResponse updateBooking(int bookingid, String firstname, String lastname, int totalprice, boolean depositpaid, String checkin,
                                             String chekout, String additionalneed){



        BookingPojoFinal bookingPojoFinal = new BookingPojoFinal();
        BookingDates bookingdates = new BookingDates();

        bookingdates.setCheckin(checkin);
        bookingdates.setCheckout(chekout);

        bookingPojoFinal.setFirstname(firstname);
        bookingPojoFinal.setLastname(lastname);
        bookingPojoFinal.setTotalprice(totalprice);
        bookingPojoFinal.setDepositpaid(depositpaid);
        bookingPojoFinal.setBookingdates(bookingdates);
        bookingPojoFinal.setAdditionalneeds(additionalneed);

        return  SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .pathParam("bookingID",bookingid)
                .when()
                .body(bookingPojoFinal)
                .put(Endpoints.UPDATE_BOOKING_BY_ID)
                .then();
    }
    @Step("Delete Booking")
    public ValidatableResponse deleteBooking(int bookingid){

        return  SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .pathParam("bookingID",bookingid)
                .when()
                .delete(Endpoints.DELETE_BOOKING_BY_ID)
                .then();
    }

}

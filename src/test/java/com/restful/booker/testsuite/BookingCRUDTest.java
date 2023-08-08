package com.restful.booker.testsuite;

import com.restful.booker.testbase.TestBase;
import com.restful.booker.userinfo.BookingSteps;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookingCRUDTest extends TestBase {
    String firstname = "jack"+ TestUtils.getRandomValue();
    String lastname = "sparrow" + TestUtils.getRandomValue();
    int totalprice = 500;
    boolean depositpaid = true;

    String checkin = "2018-01-01";
    String checkout = "2019-01-01";
    String additionalneed = "BreafFast";

    static int newbookingid;

    @Steps
    BookingSteps bookingSteps;

    @Title("Get all Booking Details")
    @Test
    public void test01_allBooking(){
        ValidatableResponse response=bookingSteps.getAllBooking();
        response.log().all().statusCode(200);
    }

    @Title("Create New Booking")
    @Test
    public void test02_createBooking(){
        ValidatableResponse response=bookingSteps.createBooking(firstname,lastname,totalprice,depositpaid,checkin,checkout,additionalneed);
        response.log().all().statusCode(200);
        newbookingid = response.extract().path("bookingid");
        System.out.println("NEWLY CREATED STORE ID IS:" +newbookingid);

    }
    @Title("Verify Newly Created Booking")
    @Test
    public void test03_verifyBooking(){
        ValidatableResponse response=bookingSteps.VerifyBooking(newbookingid);
        response.log().all().statusCode(200);
    }

    @Title("Update Booking")
    @Test
    public void test04_updateBooking(){
        firstname = "jack"+ TestUtils.getRandomValue();
        lastname = "sparrow" + TestUtils.getRandomValue();
        ValidatableResponse response=bookingSteps.updateBooking(newbookingid,firstname,lastname,totalprice,depositpaid,checkin,checkout,additionalneed);
        response.log().all().statusCode(200);

    }

    @Title("Delete Booking")
    @Test
    public void test05_deleteBooking(){
        ValidatableResponse response=bookingSteps.deleteBooking(newbookingid);
        response.log().all().statusCode(201);
    }

}

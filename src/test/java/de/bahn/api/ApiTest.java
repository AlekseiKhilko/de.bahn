package de.bahn.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {
    private final String CLIENT_ID = "2b65c585020fe77b0c62b07823289240";
    private final String CLIENT_SECRET = "e153f24de1a2b8668ef6788732013f69";
    private final Integer TIMETABLES_EVANO = 8000105;
    private final Integer STATION_ID = 7075;

    @BeforeTest
    public void configureRestAssured() {
        RestAssured.baseURI = "https://apis.deutschebahn.com/db-api-marketplace/apis";
        RestAssured.requestSpecification = given()
                .header("DB-Client-Id", CLIENT_ID)
                .header("DB-Api-Key", CLIENT_SECRET);
    }

    @Test
    public void timetablesStationTest() {
        String actual = "Frankfurt(Main)Hbf";
        given()
                .accept(ContentType.XML)
                .get("/timetables/v1/fchg/" + TIMETABLES_EVANO.toString())
                .then()
                .assertThat()
                .statusCode(200)
                .body("timetable[0].@station", equalTo(actual));
    }

    @Test
    public void timetablesEvaTest() {
        given()
                .accept(ContentType.XML)
                .get("/timetables/v1/fchg/" + TIMETABLES_EVANO.toString())
                .then()
                .assertThat()
                .statusCode(200)
                .body("timetable[0].@eva", equalTo(TIMETABLES_EVANO.toString()));
    }

    @Test
    public void stationIdDataTest() {
        given()
                .accept(ContentType.JSON)
                .get("/station-data/v2/stations/" + STATION_ID.toString())
                .then()
                .assertThat()
                .statusCode(200)
                .body("result[0].number", equalTo(STATION_ID));

    }

    @Test
    public void stationNameDataTest() {
        String actual = "Zwingenberg (Bergstr)";
        given()
                .accept(ContentType.JSON)
                .get("/station-data/v2/stations/" + STATION_ID.toString())
                .then()
                .assertThat()
                .statusCode(200)
                .body("result[0].name", equalTo(actual));
    }

    @Test
    public void railwayStationsTest() {
        String actual = "Lietuva (Litauen)";
        given()
                .accept(ContentType.JSON)
                .get("/api.railway-stations.org/countries")
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].name", equalTo(actual));

    }

    @Test
    public void connectingTimesTest() {
        given()
                .accept("application/vnd.de.db.ris+json")
                .get("/ris-stations/v1/connecting-times/" + STATION_ID.toString())
                .then()
                .assertThat()
                .statusCode(200)
                .body("connectingTimesList[0].fromEvaNumber", equalTo(STATION_ID.toString()));

    }
}

package com.hapifyme.api.tests;

import com.hapifyme.api.models.*;
import com.hapifyme.api.utils.ApiPoller;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class FullUserLifecycleTest {
    @Test
    public void testFullUserLifecycle() {
        String baseUri = "https://apps.qualiadept.eu/hapifyme/api";

        // --- ETAPA 1: REGISTER (Obținem API Key și User ID) ---
        String uniqueEmail = "chain_" + System.currentTimeMillis() + "@hapifyme.com";
        String password = "Pass1234@";
        String firstName = "Chain";
        String lastName = "User";

        RegisterRequest registerBody = new RegisterRequest(firstName, lastName, uniqueEmail, password);

        RegisterResponse registerResponse = given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .body(registerBody)
                .log().body()
                .when()
                .post("/user/register.php")
                .then()
                .statusCode(201) // sau 200 depinde de API
                .extract().as(RegisterResponse.class);

        String extractedApiKey = registerResponse.getApiKey();
        int extractedUserId = Integer.parseInt(registerResponse.getUserId());
        System.out.println("User creat cu ID: " + registerResponse.getUserId());
        String extractedUsername = registerResponse.getUsername();

        // Pregătire date (emailul utilizatorului înregistrat)
        String userEmail = uniqueEmail;
        String apiKey = extractedApiKey;

        //Așteptăm statusul "success" folosind ApiPoller pe endpoint-ul valid
        String statusUrl = "https://apps.qualiadept.eu/hapifyme/api/user/retrieve_token.php?username_or_email=" + userEmail;
        ApiPoller.pollForStatus(statusUrl, "success", apiKey);

        System.out.println("1. Register Done. API Key: " + extractedApiKey + " . Username is: " + extractedUsername);

        // --- ETAPA 2: Confirm email (folosim token-ul utilizatorului) ---
        String extractedToken = registerResponse.getToken();

        given()
                .baseUri(baseUri)
                .queryParam("token", extractedToken)
                .contentType(ContentType.JSON)
                .when()
                .get("/user/confirm_email.php")
                .then()
                .log().body()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", containsString("Email confirmed successfully. You can now log in"));

        System.out.println("2. Email confirmed successfully.");

        // Etapa 3. Login ( extrage Bearer Token)
        LoginRequest loginBody = new LoginRequest(extractedUsername, password); // SAU folosim username din registerResponse

        LoginResponse loginResponse = given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .body(loginBody)
                .when()
                .post("/user/login.php")
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponse.class);

        String bearerToken = loginResponse.getToken();
        System.out.println("3. Login Done. Bearer Token: " + bearerToken);

        //4.Get profile (Verificare profil: datele de la autentificare
        // corespund cu cele din profil)

        ProfileResponse profileResponse = given()
                .baseUri(baseUri)
                .header("Authorization", extractedApiKey) // Folosim API Key
                .queryParam("user_id", extractedUserId) // Folosim Bearer Token
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("/user/get_profile.php")
                .then()
                .statusCode(200)
                .extract().as(ProfileResponse.class);
        System.out.println("Profile response: " + profileResponse);
        System.out.println("4. Done.");

    }
}



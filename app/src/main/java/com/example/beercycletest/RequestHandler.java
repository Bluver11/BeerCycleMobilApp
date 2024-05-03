package com.example.beercycletest;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Egy RequestHandler osztály, amely a backend csatlakozásért felelős.
 */
public class RequestHandler {
    /**
     * Privát konstruktor, hogy ne lehessen példányosítani.
     */
    private RequestHandler() {
    }
    /**
     * Beállítja a kapcsolatot a megadott URL-hez.
     *
     * @param url A csatlakozandó URL.
     * @param token A hitelesítő token (opcionális).
     * @return A beállított HttpURLConnection objektum.
     * @throws IOException Ha hiba történik a csatlakozás során.
     */
    //backend csatlakozásért felelős methódus
    private static HttpURLConnection setupConnection(String url, String token) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setRequestProperty("Accept", "application/json");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        Log.d("Token", "setupConnection: " + token);
        if (token != null) {
            conn.setRequestProperty("Authorization", "Bearer " + token);
        }
        return conn;
    }

    //response elérése
    private static com.example.beercycletest.Response getResponse(HttpURLConnection connection)
            throws IOException {
        int responseCode = connection.getResponseCode();
        InputStream inputStream;
        if (responseCode < 400) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String sor = bufferedReader.readLine();
        while (sor != null) {
            builder.append(sor);
            sor = bufferedReader.readLine();
        }
        bufferedReader.close();
        inputStream.close();
        return new Response(responseCode, builder.toString());
    }

    //request body hozzáadása
    private static void addRequestBody(HttpURLConnection connection, String data) throws IOException {
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        writer.write(data);
        writer.flush();
        writer.close();
        outputStream.close();
    }

    /**
     * Get response.
     *
     * @param url   the url
     * @param token the token
     * @return the response
     * @throws IOException the io exception
     */
//GET METHOD
    public static Response get(String url,String token) throws IOException {
        HttpURLConnection connection = setupConnection(url,token);
        return getResponse(connection);
    }

    /**
     * Post response.
     *
     * @param url   the url
     * @param data  the data
     * @param token the token
     * @return the response
     * @throws IOException the io exception
     */
//POST METHOD
    public static Response post(String url, String data,String token) throws IOException {
        HttpURLConnection connection = setupConnection(url,token);
        connection.setRequestMethod("POST");
        addRequestBody(connection, data);
        return getResponse(connection);
    }

    /**
     * Patch response.
     *
     * @param url   the url
     * @param data  the data
     * @param token the token
     * @return the response
     * @throws IOException the io exception
     */
//PUT METHOD
    public static Response patch(String url, String data,String token) throws IOException {
        HttpURLConnection connection = setupConnection(url,token);
        connection.setRequestMethod("PATCH");
        addRequestBody(connection, data);
        return getResponse(connection);
    }

    /**
     * Delete response.
     *
     * @param url   the url
     * @param token the token
     * @return the response
     * @throws IOException the io exception
     */
//DELETE METHOD
    public static Response delete(String url,String token) throws IOException {
        HttpURLConnection connection = setupConnection(url,token);
        connection.setRequestMethod("DELETE");
        return getResponse(connection);
    }
}

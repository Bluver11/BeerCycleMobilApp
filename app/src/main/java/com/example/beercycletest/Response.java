package com.example.beercycletest;

/**
 * Egy választ reprezentáló osztály, amely tartalmaz egy válaszkódot és egy üzenetet.
 */
public class Response {

    /**
     * A válasz kódja
     */
    private int responseCode;
    /**
     * A válasz tartalma
     */
    private String responseMessage;

    /**
     * Konstruktor egy új Response objektum létrehozásához.
     *
     * @param responseCode    A válaszkód.
     * @param responseMessage A válaszüzenet.
     */
    public Response(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    /**
     * Visszaadja a válaszkódot.
     *
     * @return A válaszkód.
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * Beállítja a válaszkódot.
     *
     * @param responseCode A beállítandó válaszkód.
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * Visszaadja a válaszüzenetet.
     *
     * @return A válaszüzenet.
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Beállítja a válaszüzenetet.
     *
     * @param responseMessage A beállítandó válaszüzenet.
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}

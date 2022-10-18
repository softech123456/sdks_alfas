package com.softech.alfasdk.Models.AccountModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountResponse {

    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("code")
    @Expose
    private String code;

    /**
     * No args constructor for use in serialization
     */
    public AccountResponse() {
    }

    /**
     * @param response
     * @param error
     * @param code
     */
    public AccountResponse(Response response, String error, String code) {
        this.response = response;
        this.error = error;
        this.code = code;
    }

    /**
     * @return The response
     */
    public Response getResponse() {
        return response;
    }

    /**
     * @param response The response
     */
    public void setResponse(Response response) {
        this.response = response;
    }

    /**
     * @return The error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error The error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return The code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(String code) {
        this.code = code;
    }

}

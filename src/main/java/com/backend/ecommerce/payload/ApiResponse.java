package com.backend.ecommerce.payload;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Standard API response wrapper")
public class ApiResponse<T> {
    @Schema(description = "HTTP status code of the response", example = "200")
    private final int status;

    @Schema(description = "Human-readable response message", example = "Products fetched successfully.")
    private final String message;

    @Schema(description = "Response payload")
    private final T data;

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}

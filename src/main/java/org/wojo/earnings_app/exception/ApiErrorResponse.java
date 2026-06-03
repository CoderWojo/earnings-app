package org.wojo.earnings_app.exception;

import java.time.LocalDateTime;

// path is an endpoint where exception occured, error is a name of Exception which happened
public record ApiErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {}

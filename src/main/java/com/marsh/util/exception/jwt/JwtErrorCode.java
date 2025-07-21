package com.marsh.util.exception.jwt;

public enum JwtErrorCode {
    INVALID_SIGNATURE,
    MALFORMED,
    CANNOT_DECODE,
    EXPIRED,
    CANNOT_REFRESH
}

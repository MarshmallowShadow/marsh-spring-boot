package com.marsh.exception.error.enc;

import com.marsh.exception.upper.aes.EncErrorCode;
import com.marsh.exception.upper.aes.EncException;
import org.springframework.http.HttpStatus;

public class AesEncryptionFailedException extends EncException {
    public AesEncryptionFailedException() {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            EncErrorCode.AES_ENCRYPTION_FAILED,
            "개인정보 암호화 실패했습니다."
        );
    }
}

package com.marsh.exception.error.enc;

import com.marsh.exception.upper.aes.EncErrorCode;
import com.marsh.exception.upper.aes.EncException;
import org.springframework.http.HttpStatus;

public class AesDecryptionFailedException extends EncException {
    public AesDecryptionFailedException() {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            EncErrorCode.AES_ENCRYPTION_FAILED,
            "데이터 복호화 실패했습니다."
        );
    }
}

package com.marsh.controller;

import com.marsh.service.AttachmentFileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "첨부파일 관련 API")
public class AttachmentFileController {
    private final AttachmentFileService attachmentFileService;
}

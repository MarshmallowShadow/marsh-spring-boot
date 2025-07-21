package com.marsh.service;

import com.marsh.repository.AttachmentFileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttachmentFileService {
    private final AttachmentFileRepository attachmentFileRepository;
}

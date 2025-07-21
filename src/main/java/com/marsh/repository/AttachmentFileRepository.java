package com.marsh.repository;

import com.marsh.repository.entitiy.AttachmentFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentFileRepository extends JpaRepository<AttachmentFile, Long> {
}

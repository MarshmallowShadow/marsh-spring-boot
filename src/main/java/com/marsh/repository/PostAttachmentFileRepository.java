package com.marsh.repository;

import com.marsh.repository.entitiy.PostAttachmentFile;
import com.marsh.repository.entitiy.id.PostAttachmentFileId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostAttachmentFileRepository extends JpaRepository<PostAttachmentFile, PostAttachmentFileId> {
}

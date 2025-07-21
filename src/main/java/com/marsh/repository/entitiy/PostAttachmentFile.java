package com.marsh.repository.entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Builder
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@ToString(exclude = "")
@EntityListeners(AuditingEntityListener.class)
@Entity
@IdClass(PostAttachmentFile.class)
@Table(name = "tr_post_attachment_file")
public class PostAttachmentFile {
    @Id
    @Column(name="post_id")
    private Long postId;

    @Id
    @Column(name="attachment_file_id")
    private Long attachmentFileId;
}

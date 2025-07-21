package com.marsh.repository.entitiy.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@Setter
public class PostAttachmentFileId {
    @Column(name="post_seq")
    private Long postSeq;

    @Column(name="attachment_file_seq")
    private Long attachmentFileSeq;
}

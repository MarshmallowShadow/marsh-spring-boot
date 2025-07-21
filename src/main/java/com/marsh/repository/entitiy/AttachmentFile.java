package com.marsh.repository.entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@ToString(exclude = "")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "t_attachment_file")
public class AttachmentFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    @Comment("식별자 번호")
    private Long seq;

    @Column(name = "uuid", nullable=false)
    @Comment("UUID")
    private String uuid;

    @Column(name = "stored_path", nullable=false)
    @Comment("저장 경로")
    private String storedPath;

    @Column(name = "stored_name", nullable=false)
    @Comment("저장된 첨부파일명")
    private String storedName;

    @Column(name = "origin_name", nullable=false)
    @Comment("원본 첨부파일명")
    private String originName;

    @Column(name = "type", nullable=false)
    @Comment("첨부파일 타입")
    private String type;

    @Column(name = "size", nullable=false)
    @Comment("첨부파일 크기(바이트)")
    private String size;

    @Column(name = "deleted", nullable=true)
    @Comment("삭제 여부")
    private Boolean deleted;

    @Column(name = "register_dtm", nullable=true)
    @Comment("등록 일시")
    private LocalDateTime registerDtm;

    @Column(name = "register_user", nullable=true)
    @Comment("등록자")
    private String registerUser;

    @Column(name = "update_dtm", nullable=true)
    @Comment("수정 일시")
    private LocalDateTime updateDtm;

    @Column(name = "update_user", nullable=true)
    @Comment("수정자")
    private String updateUser;
}

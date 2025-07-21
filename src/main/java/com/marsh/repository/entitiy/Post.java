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
@Table(name = "t_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    @Comment("식별자 번호")
    private Long seq;

    @Column(name = "uuid", nullable=false)
    @Comment("UUID")
    private String uuid;

    @Column(name = "user_seq", nullable = true)
    @Comment("사용자 식별번호")
    private Long userSeq;

    @Column(name = "title", nullable = true)
    @Comment("제목")
    private String title;

    @Column(name = "content", nullable = true)
    @Comment("내용")
    private String content;

    @Column(name = "deleted", nullable = true)
    @Comment("삭제 여부")
    private boolean deleted;

    @Column(name = "register_dtm", nullable = true)
    @Comment("등록 일시")
    private LocalDateTime registerDtm;

    @Column(name = "register_user", nullable = true)
    @Comment("등록자")
    private String registerUser;

    @Column(name = "update_dtm", nullable = true)
    @Comment("수정 일시")
    private LocalDateTime updateDtm;

    @Column(name = "update_user", nullable = true)
    @Comment("수정자")
    private String updateUser;
}

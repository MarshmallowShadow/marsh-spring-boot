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
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable=true)
    @Comment("식별자 번호")
    private Long seq;

    @Column(name = "uuid", nullable=false)
    @Comment("UUID")
    private String uuid;

    @Column(name="profile_picture_id", nullable=true)
    @Comment("프로필 사진 첨부파일 ID")
    private Long profilePictureSeq;

    @Column(name="username", nullable=true)
    @Comment("사용자 아이디")
    private String username;

    @Column(name="email", nullable=true)
    @Comment("이메일")
    private String email;

    @Column(name="password", nullable=true)
    @Comment("비밀번호")
    private String password;

    @Column(name="deleted", nullable=true)
    @Comment("삭제여부")
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

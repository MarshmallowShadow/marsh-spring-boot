package com.marsh.repository.entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@ToString(exclude = "")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "t_refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    @Comment("식별자 번호")
    private Long seq;

    @Column(name = "user_seq", nullable = false)
    @Comment("사용자 식별자 번호")
    private Long userSeq;

    @Column(name = "token", nullable = false)
    @Comment("토큰")
    private String token;

    @Column(name = "expires_at", nullable = false)
    @Comment("토큰 만료 기간")
    private LocalDateTime expiresAt;

    @Column(name = "revoked", nullable = false)
    @Comment("토큰 사용 중지 여부")
    private Boolean revoked;

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

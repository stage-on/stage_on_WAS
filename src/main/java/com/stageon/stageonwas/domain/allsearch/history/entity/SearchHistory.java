package com.stageon.stageonwas.domain.allsearch.history.entity;

import com.stageon.stageonwas.domain.auth.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class) // createdAt 자동 생성을 위해
@Table(name = "search_history")
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 검색한 유저

    @Column(nullable = false)
    private String keyword; // 검색한 키워드

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; // 검색한 시간

    public SearchHistory(User user, String keyword) {
        this.user = user;
        this.keyword = keyword;
    }
}
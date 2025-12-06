package com.stageon.stageonwas.domain.alonecon.entity;

import com.stageon.stageonwas.domain.auth.entity.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 공연 좋아요 엔티티
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_performance_like")
public class UserPerformanceLike {
    @EmbeddedId
    private UserPerformanceLikeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("performanceId")
    @JoinColumn(name = "performance_id")
    private PerformanceDetail performance;

    public UserPerformanceLike(User user, PerformanceDetail performanceDetail) {
        this.user = user;
        this.performance = performanceDetail;
        this.id = new UserPerformanceLikeId(user.getUserId(), performanceDetail.getId());
    }
}
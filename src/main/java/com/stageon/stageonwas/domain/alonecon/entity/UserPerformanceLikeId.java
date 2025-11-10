package com.stageon.stageonwas.domain.alonecon.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
// 공연 좋아요 ID 클래스 (복합 PK용)
public class UserPerformanceLikeId implements Serializable {
    private Long userId;
    private Long performanceId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPerformanceLikeId that = (UserPerformanceLikeId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(performanceId, that.performanceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, performanceId);
    }
}
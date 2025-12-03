package com.stageon.stageonwas.domain.alonecon.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSlotCustomId implements Serializable {

    private Long userId;
    private Long performanceId;
    private LocalDate slotDate;
    private Integer stageOrder;
    private String artist;
}

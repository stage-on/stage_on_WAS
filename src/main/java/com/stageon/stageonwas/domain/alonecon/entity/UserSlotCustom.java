package com.stageon.stageonwas.domain.alonecon.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_fes_slot_custom")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSlotCustom {

    @EmbeddedId
    private UserSlotCustomId id;

    @Column(nullable = false)
    private boolean inverted; // true면 색상 반전
}

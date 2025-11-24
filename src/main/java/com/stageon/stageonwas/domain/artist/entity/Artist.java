package com.stageon.stageonwas.domain.artist.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "artist_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "band_name")
    private String bandName;

    @Column(name = "relate_url")
    private String relateUrl; // 밴드 사진 URL

    @Column(name = "session_mem")
    private String sessionMem; // 밴드 구성원

    @Column(name = "intro_band")
    private String introBand; // 밴드 소개

    @Column(name = "type_of_artist")
    private Integer typeofartist;
}

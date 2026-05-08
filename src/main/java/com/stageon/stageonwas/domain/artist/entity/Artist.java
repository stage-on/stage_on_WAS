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
    private String relateUrl;

    @Column(name = "session_mem")
    private String sessionMem;

    @Column(name = "intro_band")
    private String introBand;

    @Column(name = "type_of_artist")
    private Integer typeofartist;
}

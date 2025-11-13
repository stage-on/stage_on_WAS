package com.stageon.stageonwas.domain.artist.repository;

import com.stageon.stageonwas.domain.artist.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<Artist> findByArtistNameContaining(String keyword);
}

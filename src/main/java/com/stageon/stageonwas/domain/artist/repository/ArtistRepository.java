package com.stageon.stageonwas.domain.artist.repository;

import com.stageon.stageonwas.domain.artist.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}

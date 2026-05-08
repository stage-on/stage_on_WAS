package com.stageon.stageonwas.domain.artist.service;

import com.stageon.stageonwas.domain.artist.entity.Artist;
import com.stageon.stageonwas.domain.artist.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;


    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }


    public Artist getArtistById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 아티스트가 존재하지 않습니다. id=" + id));
    }


    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }


    public List<Artist> searchArtistsByBandName(String keyword) {
        return artistRepository.findByBandNameContaining(keyword);
    }


    public Artist updateArtist(Long id, Artist updatedArtist) {
        Artist artist = getArtistById(id);

        artist.setBandName(updatedArtist.getBandName());
        artist.setRelateUrl(updatedArtist.getRelateUrl());
        artist.setSessionMem(updatedArtist.getSessionMem());
        artist.setIntroBand(updatedArtist.getIntroBand());
        artist.setTypeofartist(updatedArtist.getTypeofartist());

        return artistRepository.save(artist);
    }


    public void deleteArtist(Long id) {
        Artist artist = getArtistById(id);
        artistRepository.delete(artist);
    }
}

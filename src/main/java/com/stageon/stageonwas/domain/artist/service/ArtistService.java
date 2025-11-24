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

    /**
     * 아티스트 생성
     */
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    /**
     * ID로 아티스트 조회 (없으면 예외)
     */
    public Artist getArtistById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 아티스트가 존재하지 않습니다. id=" + id));
    }

    /**
     * 전체 아티스트 목록 조회
     */
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    /**
     * 밴드 이름 검색 (부분 일치)
     */
    public List<Artist> searchArtistsByBandName(String keyword) {
        return artistRepository.findByBandNameContaining(keyword);
    }

    /**
     * 아티스트 수정
     */
    public Artist updateArtist(Long id, Artist updatedArtist) {
        Artist artist = getArtistById(id);

        artist.setBandName(updatedArtist.getBandName());
        artist.setRelateUrl(updatedArtist.getRelateUrl());
        artist.setSessionMem(updatedArtist.getSessionMem());
        artist.setIntroBand(updatedArtist.getIntroBand());
        artist.setTypeofartist(updatedArtist.getTypeofartist());

        return artistRepository.save(artist);
    }

    /**
     * 아티스트 삭제
     */
    public void deleteArtist(Long id) {
        Artist artist = getArtistById(id);
        artistRepository.delete(artist);
    }
}

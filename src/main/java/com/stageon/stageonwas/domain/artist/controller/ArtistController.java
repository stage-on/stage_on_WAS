package com.stageon.stageonwas.domain.artist.controller;

import com.stageon.stageonwas.domain.artist.entity.Artist;
import com.stageon.stageonwas.domain.artist.service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @Operation(summary = "아티스트 생성", description = "새로운 아티스트(밴드)를 생성합니다.")
    @PostMapping
    public ResponseEntity<Artist> createArtist(
            @RequestBody Artist artist
    ) {
        Artist savedArtist = artistService.createArtist(artist);
        return ResponseEntity
                .created(URI.create("/api/artists/" + savedArtist.getId()))
                .body(savedArtist);
    }

    @Operation(summary = "아티스트 단건 조회", description = "ID로 특정 아티스트 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtist(
            @Parameter(description = "조회할 아티스트 ID", example = "1")
            @PathVariable Long id
    ) {
        Artist artist = artistService.getArtistById(id);
        return ResponseEntity.ok(artist);
    }

    @Operation(summary = "전체 아티스트 조회", description = "등록된 전체 아티스트 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        List<Artist> artists = artistService.getAllArtists();
        return ResponseEntity.ok(artists);
    }

    @Operation(summary = "밴드명 검색", description = "밴드 이름으로 아티스트를 부분 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<List<Artist>> searchArtists(
            @Parameter(description = "검색 키워드(밴드 이름 일부)", example = "nir")
            @RequestParam String keyword
    ) {
        List<Artist> artists = artistService.searchArtistsByBandName(keyword);
        return ResponseEntity.ok(artists);
    }

    @Operation(summary = "아티스트 수정", description = "기존 아티스트 정보를 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(
            @Parameter(description = "수정할 아티스트 ID", example = "1")
            @PathVariable Long id,
            @RequestBody Artist artist
    ) {
        Artist updated = artistService.updateArtist(id, artist);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "아티스트 삭제", description = "특정 아티스트 정보를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(
            @Parameter(description = "삭제할 아티스트 ID", example = "1")
            @PathVariable Long id
    ) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}

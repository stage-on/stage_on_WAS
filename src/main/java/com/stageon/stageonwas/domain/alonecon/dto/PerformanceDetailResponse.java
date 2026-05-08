package com.stageon.stageonwas.domain.alonecon.dto;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class PerformanceDetailResponse {

    private String mt20id;
    private String prfnm;
    private LocalDate prfpdfrom;
    private LocalDate prfpdto;
    private String fcltynm;
    private String locationUrl;

    private boolean hasCustom;

    private List<FesDayResponse> days;
    private List<FesSlotResponse> slots;
    private List<ArtistPicResponse> artistPics;

    public static PerformanceDetailResponse from(
            PerformanceDetail fes,
            Map<String, Boolean> invertedMap
    ) {
        boolean hasCustom = invertedMap.values().stream()
                .anyMatch(Boolean::booleanValue);

        return PerformanceDetailResponse.builder()
                .mt20id(fes.getMt20id())
                .prfnm(fes.getPrfnm())
                .prfpdfrom(fes.getPrfpdfrom())
                .prfpdto(fes.getPrfpdto())
                .fcltynm(fes.getFcltynm())
                .locationUrl(fes.getLocationUrl())
                .hasCustom(hasCustom)
                .days(fes.getDays().stream()
                        .map(FesDayResponse::from)
                        .toList())
                .slots(fes.getSlots().stream()
                        .map(slot -> {
                            String key = slotKey(slot.getDate(), slot.getStageOrder(), slot.getArtist());
                            boolean inverted = invertedMap.getOrDefault(key, false);
                            return FesSlotResponse.from(slot, inverted);
                        })
                        .toList())
                .artistPics(fes.getArtistPics().stream()
                        .map(ArtistPicResponse::from)
                        .toList())
                .build();
    }

    public static String slotKey(java.time.LocalDate date, Integer stageOrder, String artist) {
        return date + "|" + stageOrder + "|" + artist;
    }
}

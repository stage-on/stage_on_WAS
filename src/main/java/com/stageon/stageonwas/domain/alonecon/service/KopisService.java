package com.stageon.stageonwas.domain.alonecon.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stageon.stageonwas.domain.alonecon.client.KopisClient;
import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.alonecon.repository.PerformanceDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KopisService {

    private final KopisClient kopisClient;
    private final PerformanceDetailRepository performanceRepo;
    private final ObjectMapper objectMapper;

    /** 단건(압축) – 예전처럼 Mono 버전 */
    @Transactional
    public Mono<ObjectNode> getCompactOne(String mt20id) {
        String trimmed = mt20id.trim();

        return Mono.fromCallable(() ->
                performanceRepo.findByMt20id(trimmed)
                        .orElseGet(() -> {
                            // 없으면 KOPIS에서 가져와서 저장
                            String xml = kopisClient.fetchDetailXml(trimmed)
                                    .block(Duration.ofSeconds(8));

                            PerformanceDetail entity = convertXmlToEntity(xml);
                            if (entity.getMt20id() == null) entity.setMt20id(trimmed);

                            return performanceRepo.save(entity);
                        })
        ).map(this::toCompactObjectNode);
    }

    /** 다건(압축) – GET, POST 둘 다 여기 사용 */
    @Transactional
    public Mono<ObjectNode> getCompactMany(List<String> ids) {
        return Flux.fromIterable(ids)
                .flatMap(this::getCompactOne)
                .collectList()
                .map(list -> {
                    ArrayNode arr = objectMapper.createArrayNode();
                    list.forEach(arr::add);
                    ObjectNode root = objectMapper.createObjectNode();
                    root.set("items", arr);
                    return root;
                });
    }

    // ================= XML → 엔티티 (임시 더미 구현) =================
    private PerformanceDetail convertXmlToEntity(String xml) {
        // TODO: 실제 XML 파싱 로직으로 교체
        return PerformanceDetail.builder()
                .mt20id("DUMMY")
                .prfnm("DUMMY 제목")
                .prfpdfrom(LocalDate.now())
                .prfpdto(LocalDate.now())
                .fcltynm("DUMMY 공연장")
                .prfruntime("120분")
                .prfage("15세 이상 관람가")
                .pcseguidance("전석 99,000원")
                .poster("https://example.com/poster.png")
                .prfstate("공연예정")
                .dtguidance("안내문 텍스트")
                .typeofcon(1)
                .build();
    }

    // ================= 엔티티 → compact JSON =================
    private ObjectNode toCompactObjectNode(PerformanceDetail p) {
        ObjectNode node = objectMapper.createObjectNode();

        node.put("id", p.getId());
        node.put("mt20id", p.getMt20id());
        node.put("prfnm", p.getPrfnm());
        node.put("fcltynm", p.getFcltynm());
        node.put("poster", p.getPoster());
        node.put("prfstate", p.getPrfstate());
        node.put("prfruntime", p.getPrfruntime());
        node.put("prfage", p.getPrfage());
        node.put("pcseguidance", p.getPcseguidance());
        node.put("dtguidance", p.getDtguidance());
        node.put("typeofcon", p.getTypeofcon());

        if (p.getPrfpdfrom() != null) node.put("prfpdfrom", p.getPrfpdfrom().toString());
        if (p.getPrfpdto() != null)   node.put("prfpdto", p.getPrfpdto().toString());
        if (p.getTkstdate() != null)  node.put("tkstdate", p.getTkstdate().toString());
        if (p.getTksttime() != null)  node.put("tksttime", p.getTksttime().toString());

        return node;
    }
}

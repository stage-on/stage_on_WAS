package com.stageon.stageonwas.domain.alonecon.repository;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerformanceDetailRepository extends JpaRepository<PerformanceDetail, Long> {
    List<PerformanceDetail> findByPrfnmContaining(String keyword);
    Optional<PerformanceDetail> findByMt20id(String mt20id);
    List<PerformanceDetail> findByTypeofcon(Integer typeofcon);

    List<PerformanceDetail> findTop50ByPrfnmContainingIgnoreCaseOrderByPrfpdfromDesc(String prfnm);


    @Query("""
        select distinct p
        from PerformanceDetail p
        left join p.styurls sty
        left join p.slots s
        left join p.artistPics ap
        where lower(sty.relatenm) like lower(concat('%', :keyword, '%'))
           or lower(s.artist)      like lower(concat('%', :keyword, '%'))
           or lower(ap.relatenm)   like lower(concat('%', :keyword, '%'))
        order by p.prfpdfrom desc nulls last
        """)
    List<PerformanceDetail> findByArtistNameAnywhere(@Param("keyword") String keyword);
}

package com.stageon.stageonwas.domain.email.service;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.alonecon.entity.UserPerformanceLike;
import com.stageon.stageonwas.domain.alonecon.repository.UserPerformanceLikeRepository;
import com.stageon.stageonwas.domain.auth.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PerformanceAlertService {

    private final UserPerformanceLikeRepository userPerformanceLikeRepository;
    private final EmailService emailService;

    private static final int ALERT_DAYS_BEFORE = 3;

    @Scheduled(cron = "0 0 9 * * *") //
    @Transactional(readOnly = true)
    public void sendTicketingAlerts() {
        log.info("--- [배치 시작] 예매일 알림 이메일 발송 ---");

        // (D-3) 날짜 계산
        LocalDate targetDate = LocalDate.now().plusDays(ALERT_DAYS_BEFORE);

        List<UserPerformanceLike> likesToSend = userPerformanceLikeRepository
                .findAllWithUserAndPerformanceByPerformanceTkstdate(targetDate);

        if (likesToSend.isEmpty()) {
            log.info("--- [배치 종료] D-{} 알림 대상 없음 ---", ALERT_DAYS_BEFORE);
            return;
        }

        // 유저별로 공연 목록을 그룹핑 (User1: [공연A, 공연B], User2: [공연A])
        Map<User, List<PerformanceDetail>> alertsByUser = likesToSend.stream()
                .collect(Collectors.groupingBy(
                        UserPerformanceLike::getUser, // (Key: 유저)
                        Collectors.mapping(UserPerformanceLike::getPerformance, Collectors.toList())
                ));

        // 유저별로 이메일 발송
        alertsByUser.forEach((user, performanceList) -> {
            log.info("{}님에게 {}건의 알림 발송 시도...", user.getEmail(), performanceList.size());
            emailService.sendTicketingAlertEmail(user, performanceList);
        });

        log.info("--- [배치 종료] 총 {}명에게 알림 발송 완료 ---", alertsByUser.size());
    }
}
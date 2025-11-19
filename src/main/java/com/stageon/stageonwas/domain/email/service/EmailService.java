package com.stageon.stageonwas.domain.email.service;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.auth.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    
    @Async
    public void sendTicketingAlertEmail(User user, List<PerformanceDetail> performances) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());

        // 제목
        message.setSubject("[StageOn] " + user.getUsername() + "님, '좋아요' 누른 공연 예매일 알림!");

        // 본문
        String performanceListText = performances.stream()
                .map(p -> String.format("- %s (예매일: %s)", p.getPrfnm(), p.getTkstdate().toString()))
                .collect(Collectors.joining("\n"));

        String text = String.format(
                "안녕하세요, %s님!\n" +
                        "회원님께서 '좋아요' 누른 공연의 예매일이 3일 앞으로 다가왔습니다.\n\n" +
                        "놓치지 말고 티켓팅에 성공하세요! 🔥\n\n" +
                        "%s\n\n" +
                        "감사합니다.\n" +
                        "StageOn 드림.",
                user.getUsername(),
                performanceListText
        );
        message.setText(text);

        // 발송
        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            String failedPerformanceNames = performances.stream()
                    .map(PerformanceDetail::getPrfnm)
                    .collect(Collectors.joining(", "));
            log.error(
                    "이메일 발송 실패: User='{}', FailedPerformances='{}', Error='{}'",
                    user.getEmail(),
                    failedPerformanceNames,
                    e.getMessage(),
                    e
            );
        }
    }
}

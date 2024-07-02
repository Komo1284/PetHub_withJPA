package pethub.with_JPA.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pethub.with_JPA.repository.MemberCouponRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ExpiredCouponScheduler {

    private final MemberCouponRepository memberCouponRepository;

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    @Transactional
    public void deleteExpiredCoupons() {
        LocalDateTime now = LocalDateTime.now();
        memberCouponRepository.deleteByEndDateBefore(now);
    }
}
package pethub.with_JPA.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private BoardType type;

    private String title = "제목 없음";

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content = "내용 없음";

    private Secret secret = Secret.PUBLIC;

    private LocalDateTime w_date = LocalDateTime.now();
    private int v_count;
}

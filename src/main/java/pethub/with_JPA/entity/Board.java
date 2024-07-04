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
    @Enumerated(EnumType.STRING)
    private BoardType type;

    private String title = "제목 없음";

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content = "내용 없음";

    private LocalDateTime w_date = LocalDateTime.now();
    private int v_count = 0;

    public Board(Member member, BoardType type, String title, String content) {
        this.member = member;
        this.type = type;
        this.title = title;
        this.content = content;
    }

    // 게시글 조회시 조회수 증가
    public void incrementViewCount() {
        v_count += 1;
    }
}

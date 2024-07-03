package pethub.with_JPA.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import pethub.with_JPA.entity.BoardType;
import pethub.with_JPA.entity.Member;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardListDto {

    private Long id;
    private String nick;
    private String title;
    private LocalDateTime w_date;
    private int v_count;

    @QueryProjection
    public BoardListDto(Long id, String nick, String title, LocalDateTime w_date, int v_count) {
        this.id = id;
        this.nick = nick;
        this.title = title;
        this.w_date = w_date;
        this.v_count = v_count;
    }
}

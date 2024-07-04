package pethub.with_JPA.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyListDto {

    private Long board_id;
    private String boardTitle;
    private String content;
    private LocalDateTime w_date;

    @QueryProjection
    public ReplyListDto(Long board_id, String boardTitle, String content, LocalDateTime w_date) {
        this.board_id = board_id;
        this.boardTitle = boardTitle;
        this.content = content;
        this.w_date = w_date;
    }
}

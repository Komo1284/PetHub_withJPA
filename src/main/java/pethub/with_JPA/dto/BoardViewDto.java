package pethub.with_JPA.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardViewDto {

    private Long id;
    private Long member_id;
    private String title;
    private String profile;
    private String nick;
    private int v_count;
    private LocalDateTime w_date;
    private String boardType;
    private String content;

    public BoardViewDto(Long id, String title, String profile, String nick, int v_count, LocalDateTime w_date, String boardType, String content, Long member_id) {
        this.id = id;
        this.title = title;
        this.profile = profile;
        this.nick = nick;
        this.v_count = v_count;
        this.w_date = w_date;
        this.boardType = boardType;
        this.content = content;
        this.member_id = member_id;
    }
}

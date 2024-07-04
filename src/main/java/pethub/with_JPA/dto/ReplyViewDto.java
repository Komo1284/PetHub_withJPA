package pethub.with_JPA.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyViewDto {

    private Long id;
    private Long member_id;
    private String profile;
    private String nick;
    private LocalDateTime w_date;
    private String content;

    public ReplyViewDto(Long id, String profile, String nick, LocalDateTime w_date, String content, Long member_id) {
        this.id = id;
        this.profile = profile;
        this.nick = nick;
        this.w_date = w_date;
        this.content = content;
        this.member_id = member_id;
    }
}

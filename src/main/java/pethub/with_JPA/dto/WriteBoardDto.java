package pethub.with_JPA.dto;

import lombok.Data;
import pethub.with_JPA.entity.Board;
import pethub.with_JPA.entity.BoardType;
import pethub.with_JPA.entity.Member;

@Data
public class WriteBoardDto {

    private String title;
    private BoardType boardType;
    private String content;

    public Board setBoard(Member user) {
        return new Board(
                user,
                boardType,
                title,
                content
        );
    }

    public WriteBoardDto(String title, BoardType boardType, String content) {
        this.title = title;
        this.boardType = boardType;
        this.content = content;
    }
}

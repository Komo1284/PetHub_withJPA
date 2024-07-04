package pethub.with_JPA.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pethub.with_JPA.dto.BoardListDto;
import pethub.with_JPA.dto.BoardSearchCondition;

public interface BoardRepositoryCustom {
    Page<BoardListDto> searchBoards(BoardSearchCondition condition, Pageable pageable);
    Page<BoardListDto> whatIWroteBoard(BoardSearchCondition condition, Pageable pageable, Long id);
}

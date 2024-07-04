package pethub.with_JPA.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pethub.with_JPA.dto.BoardListDto;
import pethub.with_JPA.dto.BoardSearchCondition;
import pethub.with_JPA.dto.ReplyListDto;

public interface ReplyRepositoryCustom {
    Page<ReplyListDto> whatIWroteBoard(BoardSearchCondition condition, Pageable pageable, Long id);
}

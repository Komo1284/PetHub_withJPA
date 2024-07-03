package pethub.with_JPA.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pethub.with_JPA.dto.BoardListDto;
import pethub.with_JPA.dto.BoardSearchCondition;
import pethub.with_JPA.repository.BoardRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<BoardListDto> searchBoards(BoardSearchCondition condition, Pageable pageable) {
        return boardRepository.searchBoards(condition, pageable);
    }
}

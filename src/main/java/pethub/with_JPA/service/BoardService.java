package pethub.with_JPA.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pethub.with_JPA.dto.BoardListDto;
import pethub.with_JPA.dto.BoardSearchCondition;
import pethub.with_JPA.dto.BoardViewDto;
import pethub.with_JPA.dto.WriteBoardDto;
import pethub.with_JPA.entity.Board;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.repository.BoardRepository;
import pethub.with_JPA.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Page<BoardListDto> searchBoards(BoardSearchCondition condition, Pageable pageable) {
        return boardRepository.searchBoards(condition, pageable);
    }

    public void write (WriteBoardDto dto, HttpSession session) {
        Member user = (Member) session.getAttribute("user");
        Member findMember = memberRepository.findById(user.getId()).get();
        Board board = dto.setBoard(findMember);
        boardRepository.save(board);
    }

    public BoardViewDto view(Long id) {
        Board board = boardRepository.findById(id).get();
        board.incrementViewCount();
        return new BoardViewDto(
                board.getId(),
                board.getTitle(),
                board.getMember().getProfile(),
                board.getMember().getNick(),
                board.getV_count(),
                board.getW_date(),
                board.getType().name(),
                board.getContent(),
                board.getMember().getId()
        );
    }

    public void update(Long id, WriteBoardDto dto) {
        Board board = boardRepository.findById(id).get();
        board.update(dto);
    }
}

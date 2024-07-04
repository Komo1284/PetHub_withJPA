package pethub.with_JPA.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pethub.with_JPA.dto.BoardSearchCondition;
import pethub.with_JPA.dto.ReplyListDto;
import pethub.with_JPA.dto.ReplyViewDto;
import pethub.with_JPA.entity.Board;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.entity.Reply;
import pethub.with_JPA.repository.BoardRepository;
import pethub.with_JPA.repository.MemberRepository;
import pethub.with_JPA.repository.ReplyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;

    public List<ReplyViewDto> view(Long id) {

        Board board = boardRepository.findById(id).get();
        List<Reply> replies = replyRepository.findByBoard(board);
        return replies.stream()
                .map(reply -> new ReplyViewDto(
                        reply.getId(),
                        reply.getMember().getProfile(),
                        reply.getMember().getNick(),
                        reply.getW_date(),
                        reply.getContent(),
                        reply.getMember().getId()
                )).collect(Collectors.toList());
    }

    public void write(Long id, String content, HttpSession session) {

        Member user = memberRepository.findById(((Member) session.getAttribute("user")).getId()).get();
        Board board = boardRepository.findById(id).get();
        replyRepository.save(new Reply(user, board, content));
    }

    public void update(Long id, String content) {
        Reply reply = replyRepository.findById(id).get();
        reply.update(content);
    }

    public Page<ReplyListDto> whatIWroteBoard(BoardSearchCondition condition, Pageable pageable, Long id) {
        return replyRepository.whatIWroteReply(condition, pageable, id);
    }
}

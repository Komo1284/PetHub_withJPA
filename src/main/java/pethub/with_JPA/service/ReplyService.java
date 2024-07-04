package pethub.with_JPA.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pethub.with_JPA.dto.ReplyViewDto;
import pethub.with_JPA.entity.Board;
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
}

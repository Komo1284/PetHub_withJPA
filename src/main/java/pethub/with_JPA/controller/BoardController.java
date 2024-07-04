package pethub.with_JPA.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pethub.with_JPA.dto.*;
import pethub.with_JPA.entity.Board;
import pethub.with_JPA.entity.BoardType;
import pethub.with_JPA.entity.Member;
import pethub.with_JPA.entity.Reply;
import pethub.with_JPA.repository.BoardRepository;
import pethub.with_JPA.repository.ReplyRepository;
import pethub.with_JPA.service.BoardService;
import pethub.with_JPA.service.EmailService;
import pethub.with_JPA.service.ReplyService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final EmailService emailService;

    @GetMapping("/list")
    public String list(Model model, BoardSearchCondition condition,
                       @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<BoardListDto> result = boardService.searchBoards(condition, pageable);
        model.addAttribute("posts", result.getContent());
        model.addAttribute("page", result);
        model.addAttribute("condition", condition);

        return "board/list";
    }

    @GetMapping("/write")
    public void write(Model model) {
        model.addAttribute("boardTypes", BoardType.values());
    }

    @PostMapping("/write")
    public String write(WriteBoardDto dto, HttpSession session) {
        boardService.write(dto, session);
        return "redirect:/board/list";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.view(id));
        model.addAttribute("replies", replyService.view(id));
        return "board/view";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Board board = boardRepository.findById(id).get();
        WriteBoardDto dto = new WriteBoardDto(board.getTitle(), board.getType(), board.getContent());
        model.addAttribute("board", dto);
        model.addAttribute("board_id", id);
        model.addAttribute("boardTypes", BoardType.values());
        return "board/write";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, WriteBoardDto dto) {
        boardService.update(id,dto);
        return "redirect:/board/view/" + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return "redirect:/board/list";
    }

    @GetMapping("/help")
    public void help() {}

    @PostMapping("/help")
    public String help(ContactForm contactForm) {
        emailService.sendSimpleMessage(contactForm);
        return "redirect:/board/help";
    }

    @GetMapping("/wroteBoard")
    public String wroteBoard(Model model, BoardSearchCondition condition,
                       @PageableDefault(size = 10, page = 0) Pageable pageable,
                             HttpSession session) {
        Member user = (Member) session.getAttribute("user");
        Page<BoardListDto> result = boardService.whatIWroteBoard(condition, pageable,user.getId());
        model.addAttribute("posts", result.getContent());
        model.addAttribute("page", result);
        model.addAttribute("condition", condition);

        return "board/wroteBoard";
    }

    @GetMapping("/wroteReply")
    public void wroteReply(Model model) {}
}

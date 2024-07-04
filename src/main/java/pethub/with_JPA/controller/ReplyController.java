package pethub.with_JPA.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pethub.with_JPA.repository.ReplyRepository;
import pethub.with_JPA.service.ReplyService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;
    private final ReplyRepository replyRepository;

    @PostMapping("/write/{id}")
    public String writeReply(@PathVariable Long id,
                             @RequestParam("content") String content,
                             HttpSession session) {
        replyService.write(id, content, session);
        return "redirect:/board/view/" + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, String board_id) {
        replyRepository.deleteById(id);
        return "redirect:/board/view/" + board_id;
    }
}

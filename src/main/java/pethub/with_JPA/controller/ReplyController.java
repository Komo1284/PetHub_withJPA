package pethub.with_JPA.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pethub.with_JPA.service.ReplyService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/write/{id}")
    public String writeReply(@PathVariable Long id,
                             @RequestParam("content") String content,
                             HttpSession session) {
        replyService.write(id, content, session);
        return "redirect:/board/view/" + id;
    }
}

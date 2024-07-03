package pethub.with_JPA.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pethub.with_JPA.dto.BoardListDto;
import pethub.with_JPA.dto.BoardSearchCondition;
import pethub.with_JPA.service.BoardService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, BoardSearchCondition condition,
                       @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<BoardListDto> result = boardService.searchBoards(condition, pageable);
        model.addAttribute("posts", result.getContent());
        model.addAttribute("page", result);
        model.addAttribute("condition", condition);

        return "board/list";
    }
}

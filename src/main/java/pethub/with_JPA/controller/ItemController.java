package pethub.with_JPA.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pethub.with_JPA.dto.ItemListDto;
import pethub.with_JPA.dto.ItemSearchCondition;
import pethub.with_JPA.entity.ItemCategory;
import pethub.with_JPA.entity.ItemType;
import pethub.with_JPA.repository.ItemRepository;
import pethub.with_JPA.service.ItemService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @GetMapping("/list")
    public String list(Model model, ItemSearchCondition condition,
                       @PageableDefault(size = 12, page = 0) Pageable pageable) {
        Page<ItemListDto> result = itemService.searchItems(condition, pageable);
        model.addAttribute("items", result.getContent());
        model.addAttribute("page", result);
        model.addAttribute("condition", condition);

        model.addAttribute("itemTypes", ItemType.values());
        model.addAttribute("itemCategories", ItemCategory.values());

        return "item/list";
    }

    @GetMapping("/detailPage/{id}")
    public String detailPage(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemRepository.findById(id).get());
        return "item/detailPage";
    }
}

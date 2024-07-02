package pethub.with_JPA.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pethub.with_JPA.dto.AddItemDto;
import pethub.with_JPA.entity.Item;
import pethub.with_JPA.repository.ItemRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final ImageService imageService;
    private final ItemRepository itemRepository;

    public Map<String, String> AddProduct(AddItemDto dto, MultipartFile file) throws IOException {

        Map<String, String> result = new HashMap<>();

        if (dto.getItemType() == null) {
            result.put("msg", "제품의 타입을 선택해주세요.");
            result.put("path", "redirect:/admin/product_registration");
            return result;
        } else if (dto.getItemCategory() == null) {
            result.put("msg", "제품의 카테고리를 선택해주세요.");
            result.put("path", "redirect:/admin/product_registration");
            return result;
        }

        Item item;
        if (file != null) {
            dto.setItemProfile(imageService.imageUploadFromFile(file));
            item = dto.setItem();
        } else {
            item = dto.setItemNoProfile();
        }
        itemRepository.save(item);

        result.put("msg", "상품등록에 성공하였습니다.");
        result.put("path", "admin/product_registration");
        return result;
    }
}

package pethub.with_JPA.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pethub.with_JPA.dto.ItemListDto;
import pethub.with_JPA.dto.ItemSearchCondition;

public interface ItemRepositoryCustom {
    Page<ItemListDto> searchItems(ItemSearchCondition condition, Pageable pageable);
}

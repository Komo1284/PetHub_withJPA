package pethub.with_JPA.dto;

import lombok.Data;
import pethub.with_JPA.entity.BoardType;
import pethub.with_JPA.entity.ItemCategory;
import pethub.with_JPA.entity.ItemType;

@Data
public class ItemSearchCondition {

    private String searchValue;
    private ItemType itemType;
    private ItemCategory itemCategory;
}

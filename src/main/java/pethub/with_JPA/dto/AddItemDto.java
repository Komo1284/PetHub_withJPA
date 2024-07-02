package pethub.with_JPA.dto;

import lombok.Data;
import pethub.with_JPA.entity.Item;
import pethub.with_JPA.entity.ItemCategory;
import pethub.with_JPA.entity.ItemType;

@Data
public class AddItemDto {

    private String itemName;
    private ItemCategory itemCategory;
    private ItemType itemType;
    private int price;
    private String itemDescription;
    private String itemProfile;

    public Item setItem() {
        return new Item(
                itemName,
                itemCategory,
                itemType,
                price,
                itemDescription,
                itemProfile
        );
    }

    public Item setItemNoProfile() {
        return new Item(
                itemName,
                itemCategory,
                itemType,
                price,
                itemDescription
        );
    }
}

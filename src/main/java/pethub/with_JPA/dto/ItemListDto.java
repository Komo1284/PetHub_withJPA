package pethub.with_JPA.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ItemListDto {

    private Long item_id;
    private String itemProfile;
    private String itemName;
    private int price;

    @QueryProjection
    public ItemListDto(Long item_id, String itemProfile, String itemName, int price) {
        this.item_id = item_id;
        this.itemProfile = itemProfile;
        this.itemName = itemName;
        this.price = price;
    }
}

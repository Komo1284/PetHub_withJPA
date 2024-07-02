package pethub.with_JPA.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;

    private String itemName;

    private String itemProfile = "https://japan.ybmclass.com/common_css/img/noimage.gif";

    @Lob
    @Column(columnDefinition = "TEXT")
    private String itemDescription;
    private int price;

    public Item(String itemName, ItemCategory itemCategory, ItemType itemType, int price, String itemDescription, String itemProfile) {
        this.itemType = itemType;
        this.itemCategory = itemCategory;
        this.itemName = itemName;
        this.itemProfile = itemProfile;
        this.itemDescription = itemDescription;
        this.price = price;
    }

    public Item(String itemName, ItemCategory itemCategory, ItemType itemType, int price, String itemDescription) {
        this.itemType = itemType;
        this.itemCategory = itemCategory;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
    }
}

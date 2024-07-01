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

    @Lob
    @Column(columnDefinition = "TEXT")
    private String itemDescription;
    private int price;

}

package pethub.with_JPA.dto;

import lombok.Data;
import pethub.with_JPA.entity.BoardType;

@Data
public class BoardSearchCondition {

    private String searchType;
    private String searchValue;
    private BoardType boardType;
}

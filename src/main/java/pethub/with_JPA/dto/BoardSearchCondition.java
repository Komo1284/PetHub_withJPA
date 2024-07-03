package pethub.with_JPA.dto;

import lombok.Data;

@Data
public class BoardSearchCondition {

    private String searchType;
    private String searchValue;
}

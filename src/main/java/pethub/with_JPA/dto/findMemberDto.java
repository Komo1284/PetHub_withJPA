package pethub.with_JPA.dto;

import lombok.Data;

@Data
public class findMemberDto {

    private String username;
    private String email;
    private String authNum;
}

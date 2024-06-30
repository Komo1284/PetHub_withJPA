package pethub.with_JPA.dto;

import lombok.Data;

@Data
public class UpdateMemberDto {

    private String prevPw;
    private String newPw;
    private String newPwCheck;
    private String profile;
    private String nick;
    private String email;
    private String phone;
    private String street;
    private String city;
    private String zipcode;
}

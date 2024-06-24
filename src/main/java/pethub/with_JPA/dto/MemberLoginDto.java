package pethub.with_JPA.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberLoginDto {

    private String username;
    private String password;

    public MemberLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

package pethub.with_JPA.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String username;
    private String password;
    private String nick;
    private String email;
    private String phone;

    @Embedded
    private Address address;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String profile;

    private Ad ad;
    private Role role;
}

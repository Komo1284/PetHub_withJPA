package pethub.with_JPA.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
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

    private String profile;

    private Ad ad;
    private Role role;
}

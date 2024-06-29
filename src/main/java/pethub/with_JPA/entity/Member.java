package pethub.with_JPA.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Member(String username, String password, String name, String nick, String email, Address address, String phone, Ad ad) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.nick = nick;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.ad = ad;
    }

    private String profile = "https://search.pstatic.net/sunny/?src=https%3A%2F%2Fpng.pngtree.com%2Fpng-clipart%2F20220112%2Fourlarge%2Fpngtree-cartoon-hand-drawn-default-avatar-png-image_4154232.png&type=sc960_832";

    private Ad ad;
    private Role role = Role.MEMBER;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;
}

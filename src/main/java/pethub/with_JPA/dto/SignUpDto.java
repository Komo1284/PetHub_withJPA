package pethub.with_JPA.dto;

import lombok.Data;
import pethub.with_JPA.entity.Ad;
import pethub.with_JPA.entity.Address;
import pethub.with_JPA.entity.Member;

@Data
public class SignUpDto {

    private String username;
    private String password;
    private String pwCheck;
    private String name;
    private String nick;
    private String email;
    private String street;
    private String city;
    private String zipcode;
    private String phone;
    private int ad;

    public Member setMember(SignUpDto signUpDto) {
        Address address = new Address(street, city, zipcode);
        Ad ad1 = Ad.DENY;
        if (ad == 1) {
            ad1 = Ad.ACCEPT;
        }
        return new Member(
                username,
                password,
                name,
                nick,
                email,
                address,
                phone,
                ad1
        );
    }

    public SignUpDto(String username, String password, String pwCheck, String name, String nick, String email, String street, String city, String zipcode, String phone, int ad) {
        this.username = username;
        this.password = password;
        this.pwCheck = pwCheck;
        this.name = name;
        this.nick = nick;
        this.email = email;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.phone = phone;
        this.ad = ad;
    }
}

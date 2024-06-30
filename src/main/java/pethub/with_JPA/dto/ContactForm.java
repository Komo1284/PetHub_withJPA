package pethub.with_JPA.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ContactForm {
    private String name;
    private String email;
    private String message;
}
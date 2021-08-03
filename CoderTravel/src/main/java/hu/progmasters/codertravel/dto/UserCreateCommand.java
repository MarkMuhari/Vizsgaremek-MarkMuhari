package hu.progmasters.codertravel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserCreateCommand {

    @NotBlank
    @Min(4)
    private String name;

    @NotBlank
    @Email
//    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    @Min(6)
    // TODO @Password annotáció minimum 6 betű kell nagy kicsi szám bele
    private String password;
}

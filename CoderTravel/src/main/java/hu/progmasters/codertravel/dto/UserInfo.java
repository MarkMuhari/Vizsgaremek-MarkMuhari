package hu.progmasters.codertravel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfo {

    private Integer id;

    private String name;

    private String email;

    private String password;
}

package ra.springsecurity.model.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.springsecurity.model.entity.Role;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private String username;
    private String fullName;
    private String email;
    private Boolean sex;
    private Date birthday;
    private Set<Role> roles;
}

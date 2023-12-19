package ra.springsecurity.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import ra.springsecurity.model.entity.Role;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JWTResponse {
    private String username;
    private String fullName;
    private Collection<? extends GrantedAuthority> roles;
    private final String type= "Bearer";
    private String accessToken;
    private String refreshToken;
}

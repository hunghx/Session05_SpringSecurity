package ra.springsecurity.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.springsecurity.model.entity.Role;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private Boolean sex;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date birthday;
    private List<String> roleList;
}

package ra.springsecurity.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // tự sinh, 36 kí tự
    @Column(length = 50)
    private String id;
    @Column(unique = true,length = 100)
    private String username;
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(unique = true)
    private String email;
    private Boolean sex;
    private Date birthday;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}

package ra.springsecurity.security.principle;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.springsecurity.model.entity.User;
import ra.springsecurity.repository.IUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(r->new SimpleGrantedAuthority(r.getRoleName().name()))
                .collect(Collectors.toList());
        return CustomerUserDetail.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .authorities(authorities).build();

    }
}

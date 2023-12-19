package ra.springsecurity.service.impl;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ra.springsecurity.model.dto.request.SignInRequest;
import ra.springsecurity.model.dto.request.SignUpRequest;
import ra.springsecurity.model.dto.response.UserResponse;
import ra.springsecurity.model.entity.Role;
import ra.springsecurity.model.entity.RoleName;
import ra.springsecurity.model.entity.User;
import ra.springsecurity.repository.IRoleRepository;
import ra.springsecurity.repository.IUserRepository;
import ra.springsecurity.service.IUserService;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResponse addRoleToUser(String userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("ID không tìm thấy"));
        Role role = roleRepository.findById(roleId).orElseThrow(() ->
                new RuntimeException("ID không tìm thấy"));
        if (user.getRoles().stream().anyMatch(r-> Objects.equals(r.getId(),role.getId()))){
           throw new RuntimeException("Đã có quyền này rồi");
       }
        user.getRoles().add(role);
        return modelMapper.map(userRepository.save(user), UserResponse.class);
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(u->modelMapper.map(u,UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(String id) {
        return modelMapper.map(userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("ID không tìm thấy"))
                , UserResponse.class);
    }

    @Override
    public UserResponse signUp(SignUpRequest signUpRequest) {
        signUpRequest.setPassword(BCrypt.hashpw(signUpRequest.getPassword(),BCrypt.gensalt(5)));
        Set<Role> roleSet = new HashSet<>();
        if (signUpRequest.getRoleList()==null){
            roleSet.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("Không tìm thấy role")));
        }else {
        signUpRequest.getRoleList().forEach(s ->{
            switch (s){
                case "admin":
                    roleSet.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Không tìm thấy role")));
                case "pm":
                    roleSet.add(roleRepository.findByRoleName(RoleName.ROLE_PM).orElseThrow(() -> new RuntimeException("Không tìm thấy role")));
                case "user":
                default:
                    roleSet.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("Không tìm thấy role")));
            }
        });
        }
        User user = modelMapper.map(signUpRequest, User.class);
        user.setRoles(roleSet);
        return modelMapper.map(userRepository.save(user), UserResponse.class);
    }

    @Override
    public UserResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.findByUsernameOrEmail(signInRequest.getUsername(),signInRequest.getUsername()).orElseThrow(() -> new RuntimeException("Username or Password incorrect"));
        if (!BCrypt.checkpw(signInRequest.getPassword(),user.getPassword())){
            throw  new RuntimeException("Username or Password incorrect");
        }

        return modelMapper.map(user,UserResponse.class);
    }
}

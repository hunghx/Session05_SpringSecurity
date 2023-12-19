package ra.springsecurity.service;

import ra.springsecurity.model.dto.request.SignInRequest;
import ra.springsecurity.model.dto.request.SignUpRequest;
import ra.springsecurity.model.dto.response.UserResponse;

import java.util.List;

public interface IUserService {
    List<UserResponse> findAll();
    UserResponse findById(String id);
    UserResponse addRoleToUser(String userId,Long roleId);

    UserResponse signUp(SignUpRequest signUpRequest);
    UserResponse signIn(SignInRequest signInRequest);
}

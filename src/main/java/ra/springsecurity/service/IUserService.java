package ra.springsecurity.service;

import ra.springsecurity.model.dto.request.SignInRequest;
import ra.springsecurity.model.dto.request.SignUpRequest;
import ra.springsecurity.model.dto.response.JWTResponse;

import java.util.List;

public interface IUserService {
    List<JWTResponse> findAll();
    JWTResponse findById(String id);
    JWTResponse addRoleToUser(String userId, Long roleId);

    void signUp(SignUpRequest signUpRequest);
    JWTResponse signIn(SignInRequest signInRequest);
}

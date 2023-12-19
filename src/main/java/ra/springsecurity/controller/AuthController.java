package ra.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.springsecurity.model.dto.request.SignInRequest;
import ra.springsecurity.model.dto.request.SignUpRequest;
import ra.springsecurity.service.IUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final IUserService userService;
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest){
        userService.signUp(signUpRequest);
        return ResponseEntity.ok("Success");
    }
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(userService.signIn(signInRequest));
    }
}

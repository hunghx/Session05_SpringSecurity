package ra.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.springsecurity.model.dto.request.SignUpRequest;
import ra.springsecurity.model.entity.Role;
import ra.springsecurity.model.entity.User;
import ra.springsecurity.service.IUserService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ApiController {
    private  final IUserService userService;
    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> doSignUp(@RequestBody SignUpRequest signUpRequest){
        return new ResponseEntity<>(userService.signUp(signUpRequest), HttpStatus.OK);
    }

    @PutMapping("/user/{userId}/add-role")
    public ResponseEntity<?> addRole(@PathVariable String userId, @RequestBody Role role){
        return new ResponseEntity<>(userService.addRoleToUser(userId,role.getId()),HttpStatus.OK);
    }

}

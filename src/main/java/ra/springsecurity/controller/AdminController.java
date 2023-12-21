package ra.springsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authen")
public class AdminController {

    @GetMapping("/index")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public String index(){
        return "successsssss";
    }
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public String user(){
        return "successsssss";
    } @GetMapping("/pm")
    @PreAuthorize("hasRole('PM')")
    @ResponseStatus(HttpStatus.OK)
    public String pm(){
        return "successsssss";
    } @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','USER','PM')")
    @ResponseStatus(HttpStatus.OK)
    public String all(){
        return "successsssss";
    }
}

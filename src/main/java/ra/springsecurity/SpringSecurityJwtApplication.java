package ra.springsecurity;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ra.springsecurity.model.entity.Role;
import ra.springsecurity.model.entity.RoleName;
import ra.springsecurity.model.entity.User;
import ra.springsecurity.repository.IUserRepository;
import ra.springsecurity.service.impl.UserServiceImpl;

import java.util.Date;
import java.util.HashSet;

@SpringBootApplication
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

//    @Bean
//    CommandLineRunner commandLineRunner(IUserRepository userRepository){
//        return args -> {
//            Role admin = new Role(null, RoleName.ROLE_ADMIN);
//            Role pm = new Role(null, RoleName.ROLE_PM);
//            Role user = new Role(null, RoleName.ROLE_USER);
//
//            User u1= new User(null,"hunghx", BCrypt.hashpw("123456",BCrypt.gensalt(5)),"Hồ Hùng","hung@gmail.com",true,new Date(),new HashSet<>());
//            u1.getRoles().add(admin);
//            u1.getRoles().add(pm);
//            u1.getRoles().add(user);
//
//            userRepository.save(u1);
//        };
//    }
}

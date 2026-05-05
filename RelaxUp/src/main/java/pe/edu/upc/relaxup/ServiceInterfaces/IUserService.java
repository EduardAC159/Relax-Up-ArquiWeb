package pe.edu.upc.relaxup.ServiceInterfaces;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.edu.upc.relaxup.Entities.Users;

public interface IUserService {

    public Users insert(Users user);
}

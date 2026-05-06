package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Users;
import pe.edu.upc.relaxup.Repositories.IUserRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IUserService;
@Service
public class UserServiceImplement implements IUserService {

    @Autowired
    private IUserRepository uR;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users insert(Users user) {
        // 🔐 Encriptar contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // ✅ Activar usuario por defecto
        user.setEnabled(true);

        // (Opcional pero recomendado) asignar rol por defecto
        // user.setRoles(List.of(roleRepository.findByName("ROLE_USER")));


        return uR.save(user);
    }
}

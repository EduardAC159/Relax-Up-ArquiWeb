package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Role;
import pe.edu.upc.relaxup.Entities.Usuario;
import pe.edu.upc.relaxup.Entities.Users;
import pe.edu.upc.relaxup.Repositories.IUserRepository;
import pe.edu.upc.relaxup.Repositories.IUsuarioRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IUserService;

import java.util.List;

@Service
public class UserServiceImplement implements IUserService {

    @Autowired
    private IUserRepository uR;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users insert(Users user) {
        // Encriptar contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Activar usuario por defecto
        user.setEnabled(true);

        // Asignar rol USER por defecto
        Role rol = new Role();
        rol.setRol("USER");
        rol.setUser(user);
        user.setRoles(List.of(rol));

        Users guardado = uR.save(user);

        // Crear automáticamente el perfil de Usuario vinculado,
        // ya que todos los demás módulos (metas, recordatorios, etc.)
        // trabajan sobre la tabla Usuario, no sobre Users.
        Usuario perfil = new Usuario();
        perfil.setUsername(guardado.getUsername());
        perfil.setNombres(guardado.getUsername());
        perfil.setEmail(guardado.getUsername() + "@relaxup.pe");
        perfil.setDireccion("Por definir");
        perfil.setCelular(0);
        usuarioRepository.save(perfil);

        return guardado;
    }
}
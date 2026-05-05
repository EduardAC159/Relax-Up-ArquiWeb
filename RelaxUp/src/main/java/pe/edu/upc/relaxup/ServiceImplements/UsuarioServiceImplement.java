package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Usuario;
import pe.edu.upc.relaxup.Repositories.IUsuarioRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IUsuarioService;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
    @Autowired
    private IUsuarioRepository uR;
    @Override
    public List<Usuario> list() {
        return uR.findAll();
    }

    @Override
    public Usuario insert(Usuario usuario) {
        return uR.save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        uR.save(usuario);
    }

    @Override
    public void delete(int id) {
        uR.deleteById(id);
    }

    @Override
    public Optional<Usuario> listId(int id) {
        return uR.findById(id);
    }

    @Override
    public Usuario login(String username, String password) {
        return null;
    }

    @Override
    public Usuario findByUsername(String username) {
        return null;
    }
    @Override
    public Usuario findByEmail(String email) {
        return uR.findByEmail(email);
    }

    @Override
    public Usuario findByCelular(int celular) {
        return null;
    }
}

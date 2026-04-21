package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Usuario;
import pe.edu.upc.relaxup.Repositories.IUsuarioRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IUsuarioService;

import java.util.List;
@Service
public class UsuarioServiceImplement implements IUsuarioService {
    @Autowired
    private IUsuarioRepository uR;
    @Override
    public List<Usuario> list() {
        return uR.findAll();
    }
}

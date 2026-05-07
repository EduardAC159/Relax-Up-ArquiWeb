package pe.edu.upc.relaxup.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.relaxup.Entities.Role;
import pe.edu.upc.relaxup.Repositories.iRoleRepository;
import pe.edu.upc.relaxup.ServiceInterfaces.IRoleService;

import java.util.List;

@Service
public class RoleServiceImplement implements IRoleService {


    @Autowired
    private iRoleRepository rR;

    @Override
    public List<Role> list() {
        return rR.findAll();
    }

    @Override
    public Role insert(Role role) {
        return rR.save(role);
    }
}

package pe.edu.upc.relaxup.ServiceInterfaces;

import pe.edu.upc.relaxup.Entities.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> list();
    public Role insert (Role role);
}

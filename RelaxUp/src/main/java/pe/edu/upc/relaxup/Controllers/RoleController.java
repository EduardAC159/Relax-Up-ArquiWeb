package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.relaxup.Dtos.RecursosDTO;
import pe.edu.upc.relaxup.Dtos.RoleDTO;
import pe.edu.upc.relaxup.Entities.Recursos;
import pe.edu.upc.relaxup.Entities.Role;
import pe.edu.upc.relaxup.ServiceInterfaces.IRoleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Role")
public class RoleController {

    @Autowired
    private IRoleService rS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> Listar(){
        ModelMapper m = new ModelMapper();
        List<RoleDTO> listarRol = rS.list().stream()
                .map(x->m.map(x,RoleDTO.class)).collect(Collectors.toList());

        if (listarRol.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay recursos registrados");
        }
        return ResponseEntity.ok(listarRol);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody RoleDTO dto){
        ModelMapper m = new ModelMapper();
        Role r = m.map(dto, Role.class);

        Role rol = rS.insert(r);
        RoleDTO responseDTO = m.map(rol, RoleDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}

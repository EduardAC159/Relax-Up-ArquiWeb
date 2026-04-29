package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.relaxup.Dtos.RolDTO;
import pe.edu.upc.relaxup.Dtos.UsuarioDTO;
import pe.edu.upc.relaxup.Entities.Rol;
import pe.edu.upc.relaxup.Entities.Usuario;
import pe.edu.upc.relaxup.ServiceInterfaces.IRolService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Rol")
public class RolController {
    @Autowired
    private IRolService rS;

    @GetMapping
    public ResponseEntity<?> Listar(){
        ModelMapper m = new ModelMapper();
        List<RolDTO> ListarRol = rS.list().stream()
                .map(x->m.map(x,RolDTO.class)).collect(Collectors.toList());

        if (ListarRol.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay roles registrados");
        }
        return ResponseEntity.ok(ListarRol);
    }
    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody RolDTO dto){
        ModelMapper m = new ModelMapper();
        Rol r = m.map(dto, Rol.class);

        Rol ro = rS.insert(r);
        RolDTO responseDTO = m.map(ro, RolDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/actualiza")
    public ResponseEntity<String> actualizar(@RequestBody RolDTO dto) {

        Optional<Rol> existente = rS.listId(dto.getIdRol());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rol no encontrado");
        }

        Rol ro = existente.get();

        ro.setRol(dto.getRol());
        ro.setDescripcion(dto.getDescripcion());

        rS.update(ro);

        return ResponseEntity.ok("Rol actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Rol> rol = rS.listId(id);

        if (rol.isPresent()) {
            rS.delete(id);
            return ResponseEntity.ok("Rol eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("machine no encontrado");
        }
    }
}

package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.relaxup.Dtos.UserDTO;
import pe.edu.upc.relaxup.Dtos.UsuarioDTO;
import pe.edu.upc.relaxup.Entities.Usuario;
import pe.edu.upc.relaxup.ServiceInterfaces.IUsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService uS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?>Listar(){
        ModelMapper m = new ModelMapper();
        List<UserDTO> ListarUsuarios = uS.list().stream()
                .map(x->m.map(x,UserDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ListarUsuarios);
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registrar(@RequestBody UsuarioDTO dto){
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);

        Usuario usu = uS.insert(u);
        UsuarioDTO responseDTO = m.map(usu, UsuarioDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> actualizar(@RequestBody UsuarioDTO dto) {

        Optional<Usuario> existente = uS.listId(dto.getIdUsuario());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("usuario no encontrado");
        }

        Usuario usu = existente.get();

        usu.setNombres(dto.getNombres());
        usu.setEmail(dto.getEmail());
        usu.setDireccion(dto.getDireccion());
        usu.setUsuario(dto.getUsuario());
        usu.setCelular(dto.getCelular());
        usu.setContraseña(dto.getContraseña());

        uS.update(usu);

        return ResponseEntity.ok("usuario actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Usuario> usuario = uS.listId(id);

        if (usuario.isPresent()) {
            uS.delete(id);
            return ResponseEntity.ok("usuario eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("machine no encontrado");
        }
    }
}

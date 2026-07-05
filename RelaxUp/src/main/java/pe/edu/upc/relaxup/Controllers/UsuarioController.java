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
@RequestMapping("/api/Usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService uS;

    @GetMapping("/listar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?>Listar(){
        ModelMapper m = new ModelMapper();
        List<UsuarioDTO> ListarUsuarios = uS.list().stream()
                .map(x->m.map(x,UsuarioDTO.class)).collect(Collectors.toList());
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
        usu.setCelular(dto.getCelular());
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
                    .body("Usuario no encontrado");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<Usuario> usuario = uS.listId(id);

        if (usuario.isPresent()) {
            UsuarioDTO dto = m.map(usuario.get(), UsuarioDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
    }
}

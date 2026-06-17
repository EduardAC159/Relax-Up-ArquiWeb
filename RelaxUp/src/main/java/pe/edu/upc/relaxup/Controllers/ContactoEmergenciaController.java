package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.relaxup.Dtos.ContactoEmergenciaDTO;
import pe.edu.upc.relaxup.Entities.ContactoEmergencia;
import pe.edu.upc.relaxup.Entities.Usuario;
import pe.edu.upc.relaxup.ServiceInterfaces.IContactoEmergenciaService;
import pe.edu.upc.relaxup.ServiceInterfaces.IUsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ContactoEmergencia")
public class ContactoEmergenciaController {

    @Autowired
    private IContactoEmergenciaService ceS;
    @Autowired
    private IUsuarioService uS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> Listar(){
        ModelMapper m = new ModelMapper();
        List<ContactoEmergenciaDTO> ListarContacto = ceS.list().stream()
                .map(x->m.map(x,ContactoEmergenciaDTO.class)).collect(Collectors.toList());

        if (ListarContacto.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay Contactos registrados");
        }
        return ResponseEntity.ok(ListarContacto);
    }
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registrar(@RequestBody ContactoEmergenciaDTO dto){
        ModelMapper m = new ModelMapper();
        Optional<Usuario> user = uS.listId(dto.getIdUsuario());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El curso no existe");
        }
        ContactoEmergencia ce = m.map(dto, ContactoEmergencia.class);
        ce.setUsuario(user.get());

        ContactoEmergencia contacE = ceS.insert(ce);
        ContactoEmergenciaDTO responseDTO = m.map(contacE, ContactoEmergenciaDTO.class);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> actualizar(@RequestBody ContactoEmergenciaDTO dto) {

        Optional<ContactoEmergencia> existente = ceS.listId(dto.getIdContacto());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Contacto emergencia no encontrado");
        }

        ContactoEmergencia cont = existente.get();

        cont.setNombre(dto.getNombre());
        cont.setCelular(dto.getCelular());
        cont.setRelacion(dto.getRelacion());

        ceS.update(cont);

        return ResponseEntity.ok("Contacto de emergencia actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<ContactoEmergencia> contactoEmergencia = ceS.listId(id);

        if (contactoEmergencia.isPresent()) {
            ceS.delete(id);
            return ResponseEntity.ok("contacto de emergencia eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("machine no encontrado");
        }
    }
}

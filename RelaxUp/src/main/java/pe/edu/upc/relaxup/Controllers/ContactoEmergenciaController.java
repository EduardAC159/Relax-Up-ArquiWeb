package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.relaxup.Dtos.ContactoEmergenciaDTO;
import pe.edu.upc.relaxup.Dtos.EmergenciaDTO;
import pe.edu.upc.relaxup.Entities.ContactoEmergencia;
import pe.edu.upc.relaxup.Entities.Emergencia;
import pe.edu.upc.relaxup.ServiceInterfaces.IContactoEmergenciaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ContactoEmergencia")
public class ContactoEmergenciaController {

    @Autowired
    private IContactoEmergenciaService ceS;

    @GetMapping
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
    public ResponseEntity<?> registrar(@RequestBody ContactoEmergenciaDTO dto){
        ModelMapper m = new ModelMapper();
        ContactoEmergencia c = m.map(dto, ContactoEmergencia.class);

        ContactoEmergencia cont = ceS.insert(c);
        ContactoEmergenciaDTO responseDTO = m.map(cont, ContactoEmergenciaDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/actualiza")
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

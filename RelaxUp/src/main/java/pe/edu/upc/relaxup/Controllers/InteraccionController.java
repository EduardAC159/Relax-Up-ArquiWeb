package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.relaxup.Dtos.InteraccionDTO;
import pe.edu.upc.relaxup.Dtos.RolDTO;
import pe.edu.upc.relaxup.Entities.Interaccion;
import pe.edu.upc.relaxup.Entities.Rol;
import pe.edu.upc.relaxup.ServiceInterfaces.IInteraccionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Interaccion")
public class InteraccionController {

    @Autowired
    private IInteraccionService iS;

    @GetMapping
    public ResponseEntity<?> Listar(){
        ModelMapper m = new ModelMapper();
        List<InteraccionDTO> ListarInteraccion = iS.list().stream()
                .map(x->m.map(x, InteraccionDTO.class)).collect(Collectors.toList());

        if (ListarInteraccion.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay interacciones registrados");
        }
        return ResponseEntity.ok(ListarInteraccion);
    }
    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody InteraccionDTO dto){
        ModelMapper m = new ModelMapper();
        Interaccion i = m.map(dto, Interaccion.class);

        Interaccion inte = iS.insert(i);
        InteraccionDTO responseDTO = m.map(inte, InteraccionDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/actualiza")
    public ResponseEntity<String> actualizar(@RequestBody InteraccionDTO dto) {

        Optional<Interaccion> existente = iS.listId(dto.getIdInteraccion());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Interaccion no encontrado");
        }

        Interaccion inte = existente.get();

        inte.setMensaje(dto.getMensaje());
        inte.setFecha(dto.getFecha());

        iS.update(inte);

        return ResponseEntity.ok("interaccion actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Interaccion> interaccion = iS.listId(id);

        if (interaccion.isPresent()) {
            iS.delete(id);
            return ResponseEntity.ok("interaccion eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("machine no encontrado");
        }
    }
}

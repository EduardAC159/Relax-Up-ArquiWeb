package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.relaxup.Dtos.EmergenciaDTO;
import pe.edu.upc.relaxup.Entities.Emergencia;
import pe.edu.upc.relaxup.ServiceInterfaces.IEmergenciaServicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Emergencias")
public class EmergenciaController {
    @Autowired
    private IEmergenciaServicio eS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<EmergenciaDTO>> Listar(){
        ModelMapper m = new ModelMapper();
        List<EmergenciaDTO> ListarEmergencia = eS.list().stream()
                .map(x->m.map(x,EmergenciaDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ListarEmergencia);
    }
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registrar(@RequestBody EmergenciaDTO dto){
        ModelMapper m = new ModelMapper();
        Emergencia e = m.map(dto, Emergencia.class);

        Emergencia eme = eS.insert(e);
        EmergenciaDTO responseDTO = m.map(eme, EmergenciaDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> actualizar(@RequestBody EmergenciaDTO dto) {

        Optional<Emergencia> existente = eS.listId(dto.getIdEmergencia());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Emergencia no encontrado");
        }

        Emergencia eme = existente.get();

        eme.setTipo(dto.getTipo());
        eme.setDescripcion(dto.getDescripcion());
        eme.setFecha(dto.getFecha());

        eS.update(eme);

        return ResponseEntity.ok("interaccion actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Emergencia> emergencia = eS.listId(id);

        if (emergencia.isPresent()) {
            eS.delete(id);
            return ResponseEntity.ok("Emergencia eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("machine no encontrado");
        }
    }
}

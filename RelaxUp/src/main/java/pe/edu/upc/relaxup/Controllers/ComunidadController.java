package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.relaxup.Dtos.ComunidadDTO;
import pe.edu.upc.relaxup.Dtos.ContactoEmergenciaDTO;
import pe.edu.upc.relaxup.Entities.Comunidad;
import pe.edu.upc.relaxup.Entities.ContactoEmergencia;
import pe.edu.upc.relaxup.ServiceInterfaces.IComunidadService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Comunidad")
public class ComunidadController {
    @Autowired
    private IComunidadService cS;

    @GetMapping
    public ResponseEntity<List<ComunidadDTO>> Listar(){
        ModelMapper m = new ModelMapper();
        List<ComunidadDTO> ListarComunidad = cS.list().stream()
                .map(x->m.map(x,ComunidadDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ListarComunidad);
    }
    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody ComunidadDTO dto){
        ModelMapper m = new ModelMapper();
        Comunidad c = m.map(dto, Comunidad.class);

        Comunidad com = cS.insert(c);
        ComunidadDTO responseDTO = m.map(com, ComunidadDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/actualiza")
    public ResponseEntity<String> actualizar(@RequestBody ComunidadDTO dto) {

        Optional<Comunidad> existente = cS.listId(dto.getIdComunidad());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Comunidad no encontrado");
        }

        Comunidad com = existente.get();

        com.setNombre(dto.getNombre());
        com.setDescripcion(dto.getDescripcion());
        cS.update(com);

        return ResponseEntity.ok("Comunidad actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Comunidad> comunidad = cS.listId(id);

        if (comunidad.isPresent()) {
            cS.delete(id);
            return ResponseEntity.ok("Comunidad eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("machine no encontrado");
        }
    }
}

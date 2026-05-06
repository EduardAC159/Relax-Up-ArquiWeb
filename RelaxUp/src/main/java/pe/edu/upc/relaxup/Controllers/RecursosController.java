package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.relaxup.Dtos.RecursosDTO;
import pe.edu.upc.relaxup.Entities.Recursos;
import pe.edu.upc.relaxup.ServiceInterfaces.IRecursosService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Recursos")
public class RecursosController {

    @Autowired
    private IRecursosService recS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> Listar(){
        ModelMapper m = new ModelMapper();
        List<RecursosDTO> ListarRecursos = recS.list().stream()
                .map(x->m.map(x,RecursosDTO.class)).collect(Collectors.toList());

        if (ListarRecursos.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay recursos registrados");
        }
        return ResponseEntity.ok(ListarRecursos);
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registrar(@RequestBody RecursosDTO dto){
        ModelMapper m = new ModelMapper();
        Recursos r = m.map(dto, Recursos.class);

        Recursos rec = recS.insert(r);
        RecursosDTO responseDTO = m.map(rec, RecursosDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> actualizar(@RequestBody RecursosDTO dto) {

        Optional<Recursos> existente = recS.listId(dto.getIdRecursos());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Recursos  no encontrado");
        }

        Recursos rec = existente.get();

        rec.setTitulo(dto.getTitulo());
        rec.setTipo(dto.getTipo());
        rec.setEnlace(dto.getEnlace());

        recS.update(rec);

        return ResponseEntity.ok("Recursos actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Recursos> rec = recS.listId(id);

        if (rec.isPresent()) {
            recS.delete(id);
            return ResponseEntity.ok("Recursos eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("machine no encontrado");
        }
    }
}

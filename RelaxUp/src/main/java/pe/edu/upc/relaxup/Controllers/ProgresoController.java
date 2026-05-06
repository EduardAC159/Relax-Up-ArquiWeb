package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.relaxup.Dtos.ProgresoDTO;
import pe.edu.upc.relaxup.Entities.Progreso;
import pe.edu.upc.relaxup.ServiceInterfaces.IProgresoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Progreso")
public class ProgresoController {
    @Autowired
    private IProgresoService pS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ProgresoDTO>> Listar(){
        ModelMapper m = new ModelMapper();
        List<ProgresoDTO> ListarProgreso = pS.list().stream()
                .map(x->m.map(x,ProgresoDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ListarProgreso);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody ProgresoDTO dto){
        ModelMapper m = new ModelMapper();
        Progreso p = m.map(dto, Progreso.class);

        Progreso pro = pS.insert(p);
        ProgresoDTO progresoDTO = m.map(pro, ProgresoDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(progresoDTO);
    }

    @PutMapping("/actualiza")
    public ResponseEntity<String> actualizar(@RequestBody ProgresoDTO dto) {

        Optional<Progreso> existente = pS.listId(dto.getIdProgreso());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Progreso  no encontrado");
        }

        Progreso pro = existente.get();

        pro.setNivelControlIra(dto.getNivelControlIra());
        pro.setFecha(dto.getFecha());
        pro.setObservaciones(dto.getObservaciones());

        pS.update(pro);

        return ResponseEntity.ok("Recodatorio actualizado correctamente");
    }
}

package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.relaxup.Dtos.MetaEmocionalDTO;
import pe.edu.upc.relaxup.Dtos.ProgresoDTO;
import pe.edu.upc.relaxup.Entities.MetaEmocional;
import pe.edu.upc.relaxup.Entities.Progreso;
import pe.edu.upc.relaxup.ServiceInterfaces.IMetaEmocionalService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/MetaEmocional")
public class MetaEmocionalController {

    @Autowired
    private IMetaEmocionalService meS;

    @GetMapping
    public ResponseEntity<?> Listar(){
        ModelMapper m = new ModelMapper();
        List<MetaEmocionalDTO> listarMeta = meS.list().stream()
                .map(x->m.map(x,MetaEmocionalDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(listarMeta);
    }
    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody MetaEmocionalDTO dto){
        ModelMapper m = new ModelMapper();
        MetaEmocional e = m.map(dto, MetaEmocional.class);

        MetaEmocional met = meS.insert(e);
        MetaEmocional metaEmocional = m.map(met, MetaEmocional.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(metaEmocional);
    }

    @PutMapping("/actualiza")
    public ResponseEntity<String> actualizar(@RequestBody MetaEmocionalDTO dto) {

        Optional<MetaEmocional> existente = meS.listId(dto.getIdMeta());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Meta emocional no encontrado");
        }

        MetaEmocional met = existente.get();

        met.setDescripcion(dto.getDescripcion());
        met.setFechaInicio(dto.getFechaInicio());
        met.setFechaFin(dto.getFechaFin());
        met.setEstado(dto.getEstado());
        meS.update(met);

        return ResponseEntity.ok("Mrta emocional actualizado correctamente");
    }

}

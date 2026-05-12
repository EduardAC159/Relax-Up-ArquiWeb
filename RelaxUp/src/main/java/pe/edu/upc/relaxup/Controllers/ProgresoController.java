package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.relaxup.Dtos.ProgresoDTO;
import pe.edu.upc.relaxup.Dtos.QuantityInteraccionesDTO;
import pe.edu.upc.relaxup.Dtos.QuantityProgresoDTO;
import pe.edu.upc.relaxup.Dtos.QuantityPromedioDTO;
import pe.edu.upc.relaxup.Entities.Progreso;
import pe.edu.upc.relaxup.ServiceInterfaces.IProgresoService;

import java.util.ArrayList;
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
    public ResponseEntity<List<ProgresoDTO>> Listar() {
        ModelMapper m = new ModelMapper();
        List<ProgresoDTO> ListarProgreso = pS.list().stream()
                .map(x -> m.map(x, ProgresoDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ListarProgreso);
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registrar(@RequestBody ProgresoDTO dto) {
        ModelMapper m = new ModelMapper();
        Progreso p = m.map(dto, Progreso.class);

        Progreso pro = pS.insert(p);
        ProgresoDTO progresoDTO = m.map(pro, ProgresoDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(progresoDTO);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMIN')")
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

    @GetMapping("/CantidadNivelControlIra")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> CantidadNivelControlIra() {
        List<Object[]> listaCantidad = pS.CantidadNivelControlIra();
        if (listaCantidad.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay progreso");
        }
        List<QuantityProgresoDTO> respuesta = new ArrayList<>();
        for (Object[] fila : listaCantidad) {
            QuantityProgresoDTO dto = new QuantityProgresoDTO();
            dto.setNivelControlIra(((Number) fila[0]).intValue());
            dto.setQuantity(((Number) fila[1]).intValue());
            respuesta.add(dto);

        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/PromedioControlIraMetaEmocional")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> PromedioControlIraMetaEmocional() {
        List<Object[]> listaPromedio = pS.PromedioControlIraMetaEmocional();
        if (listaPromedio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay progreso");
        }
        List<QuantityPromedioDTO> respuesta = new ArrayList<>();
        for (Object[] fila : listaPromedio) {
            QuantityPromedioDTO dto = new QuantityPromedioDTO();
            dto.setDescripcion(((String) fila[0]));
            dto.setPromedio(((Number) fila[1]).intValue());
            respuesta.add(dto);

        }
        return ResponseEntity.ok(respuesta);
    }


}

package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.relaxup.Dtos.QuantityPromedioDTO;
import pe.edu.upc.relaxup.Dtos.QueryRecordatorioDTO;
import pe.edu.upc.relaxup.Dtos.RecordatorioDTO;

import pe.edu.upc.relaxup.Entities.Recordatorio;
import pe.edu.upc.relaxup.ServiceInterfaces.IRecordatorioService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Recordatorio")
public class RecordatorioController {

    @Autowired
    private IRecordatorioService reS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> Listar(){
        ModelMapper m = new ModelMapper();
        List<RecordatorioDTO> ListarRecordatorio = reS.list().stream()
                .map(x->m.map(x,RecordatorioDTO.class)).collect(Collectors.toList());

        if (ListarRecordatorio.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay recordatorios registrados");
        }
        return ResponseEntity.ok(ListarRecordatorio);
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registrar(@RequestBody RecordatorioDTO dto){
        ModelMapper m = new ModelMapper();
        Recordatorio r = m.map(dto, Recordatorio.class);

        Recordatorio reco = reS.insert(r);
        RecordatorioDTO recordatorioDTO = m.map(reco, RecordatorioDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(recordatorioDTO);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> actualizar(@RequestBody RecordatorioDTO dto) {

        Optional<Recordatorio> existente = reS.listId(dto.getIdRecordatorio());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Recordatorio  no encontrado");
        }

        Recordatorio reco = existente.get();

        reco.setMensaje(dto.getMensaje());
        reco.setFechaHora(dto.getFechaHora());
        reco.setTipo(dto.getTipo());

        reS.update(reco);

        return ResponseEntity.ok("Recodatorio actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Recordatorio> reco = reS.listId(id);

        if (reco.isPresent()) {
            reS.delete(id);
            return ResponseEntity.ok("Recordatorio eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("machine no encontrado");
        }
    }
    @GetMapping("/contarPorTipo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> contarPorTipo() {
        List<Object[]> listaPromedio = reS.contarPorTipo();
        if (listaPromedio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay progreso");
        }
        List<QueryRecordatorioDTO> respuesta = new ArrayList<>();
        for (Object[] fila : listaPromedio) {
            QueryRecordatorioDTO dto = new QueryRecordatorioDTO();
            dto.setTipo(((String) fila[0]));
            dto.setQuantity(((Number) fila[1]).intValue());
            respuesta.add(dto);

        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/buscarRecordatorios")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> buscarRecordatorios(
            @RequestParam String tipo,
            @RequestParam Integer usuarioId,
            @RequestParam String estado,
            @RequestParam LocalDateTime fechaInicio,
            @RequestParam LocalDateTime fechaFin) {

        List<Recordatorio> recordatorios = reS.buscarRecordatorios(
                tipo, usuarioId, estado, fechaInicio, fechaFin);

        if (recordatorios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron recordatorios con los filtros especificados");
        }
        return ResponseEntity.ok(recordatorios);
    }
}

package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.relaxup.Dtos.RecordatorioDTO;

import pe.edu.upc.relaxup.Dtos.RecursosDTO;
import pe.edu.upc.relaxup.Entities.Recordatorio;
import pe.edu.upc.relaxup.Entities.Recursos;
import pe.edu.upc.relaxup.ServiceInterfaces.IRecordatorioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Recordatorio")
public class RecordatorioController {

    @Autowired
    private IRecordatorioService reS;

    @GetMapping
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
    public ResponseEntity<?> registrar(@RequestBody RecordatorioDTO dto){
        ModelMapper m = new ModelMapper();
        Recordatorio r = m.map(dto, Recordatorio.class);

        Recordatorio reco = reS.insert(r);
        RecordatorioDTO recordatorioDTO = m.map(reco, RecordatorioDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(recordatorioDTO);
    }

    @PutMapping("/actualiza")
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
}

package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.relaxup.Dtos.RecordatorioDTO;

import pe.edu.upc.relaxup.ServiceInterfaces.IRecordatorioService;

import java.util.List;
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
}

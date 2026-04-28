package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.relaxup.Dtos.RecursosDTO;
import pe.edu.upc.relaxup.Dtos.RolDTO;
import pe.edu.upc.relaxup.ServiceInterfaces.IRecursosService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Recursos")
public class RecursosController {

    @Autowired
    private IRecursosService recS;

    @GetMapping
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
}

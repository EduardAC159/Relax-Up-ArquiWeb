package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.relaxup.Dtos.ProgresoDTO;
import pe.edu.upc.relaxup.ServiceInterfaces.IProgresoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Progreso")
public class ProgresoController {
    @Autowired
    private IProgresoService pS;

    @GetMapping
    public ResponseEntity<List<ProgresoDTO>> Listar(){
        ModelMapper m = new ModelMapper();
        List<ProgresoDTO> ListarProgreso = pS.list().stream()
                .map(x->m.map(x,ProgresoDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ListarProgreso);
    }
}

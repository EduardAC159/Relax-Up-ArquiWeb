package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.relaxup.Dtos.ComunidadDTO;
import pe.edu.upc.relaxup.ServiceInterfaces.IComunidadService;

import java.util.List;
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
}

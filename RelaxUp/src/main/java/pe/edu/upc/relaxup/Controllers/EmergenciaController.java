package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.relaxup.Dtos.EmergenciaDTO;
import pe.edu.upc.relaxup.ServiceInterfaces.IEmergenciaServicio;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Emergencias")
public class EmergenciaController {
    @Autowired
    private IEmergenciaServicio eS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<EmergenciaDTO>> Listar(){
        ModelMapper m = new ModelMapper();
        List<EmergenciaDTO> ListarEmergencia = eS.list().stream()
                .map(x->m.map(x,EmergenciaDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ListarEmergencia);
    }
}

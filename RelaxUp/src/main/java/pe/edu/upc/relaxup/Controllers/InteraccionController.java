package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.relaxup.Dtos.InteraccionDTO;
import pe.edu.upc.relaxup.ServiceInterfaces.IInteraccionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Interaccion")
public class InteraccionController {

    @Autowired
    private IInteraccionService iS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> Listar(){
        ModelMapper m = new ModelMapper();
        List<InteraccionDTO> ListarInteraccion = iS.list().stream()
                .map(x->m.map(x, InteraccionDTO.class)).collect(Collectors.toList());

        if (ListarInteraccion.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay interacciones registrados");
        }
        return ResponseEntity.ok(ListarInteraccion);
    }
}

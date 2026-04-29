package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.relaxup.Dtos.RolDTO;
import pe.edu.upc.relaxup.ServiceInterfaces.IRolService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Rol")
public class RolController {
    @Autowired
    private IRolService rS;

    @GetMapping
    public ResponseEntity<?> Listar(){
        ModelMapper m = new ModelMapper();
        List<RolDTO> ListarRol = rS.list().stream()
                .map(x->m.map(x,RolDTO.class)).collect(Collectors.toList());

        if (ListarRol.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay roles registrados");
        }
        return ResponseEntity.ok(ListarRol);
    }
}

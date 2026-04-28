package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.relaxup.Dtos.ContactoEmergenciaDTO;
import pe.edu.upc.relaxup.ServiceInterfaces.IContactoEmergenciaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ContactoEmergencia")
public class ContactoEmergenciaController {

    @Autowired
    private IContactoEmergenciaService ceS;

    @GetMapping
    public ResponseEntity<?> Listar(){
        ModelMapper m = new ModelMapper();
        List<ContactoEmergenciaDTO> ListarContacto = ceS.list().stream()
                .map(x->m.map(x,ContactoEmergenciaDTO.class)).collect(Collectors.toList());

        if (ListarContacto.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay Contactos registrados");
        }
        return ResponseEntity.ok(ListarContacto);
    }
}

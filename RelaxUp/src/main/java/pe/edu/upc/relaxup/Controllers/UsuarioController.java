package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.relaxup.Dtos.UsuarioDTO;
import pe.edu.upc.relaxup.ServiceInterfaces.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService uS;

    @GetMapping
public ResponseEntity<List<UsuarioDTO>>Listar(){
        ModelMapper m = new ModelMapper();
        List<UsuarioDTO> ListarUsuarios = uS.list().stream()
                .map(x->m.map(x,UsuarioDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ListarUsuarios);
    }
}

package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.relaxup.Dtos.MetaEmocionalDTO;
import pe.edu.upc.relaxup.ServiceInterfaces.IMetaEmocionalService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/MetaEmocional")
public class MetaEmocionalController {

    @Autowired
    private IMetaEmocionalService meS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> Listar(){
        ModelMapper m = new ModelMapper();
        List<MetaEmocionalDTO> listarMeta = meS.list().stream()
                .map(x->m.map(x,MetaEmocionalDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(listarMeta);
    }
}

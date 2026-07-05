package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.relaxup.Dtos.InteraccionDTO;
import pe.edu.upc.relaxup.Dtos.QuantityInteraccionesDTO;
import pe.edu.upc.relaxup.Dtos.QuantityMetaEmocionalDTO;
import pe.edu.upc.relaxup.Entities.Comunidad;
import pe.edu.upc.relaxup.Entities.Interaccion;
import pe.edu.upc.relaxup.Entities.Usuario;
import pe.edu.upc.relaxup.ServiceInterfaces.IComunidadService;
import pe.edu.upc.relaxup.ServiceInterfaces.IInteraccionService;
import pe.edu.upc.relaxup.ServiceInterfaces.IUsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Interaccion")
public class InteraccionController {

    @Autowired
    private IInteraccionService iS;
    @Autowired
    private IUsuarioService uS;
    @Autowired
    private IComunidadService cS;

    @GetMapping("/listar")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
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
    @GetMapping("/CantidadInteraccionesUsuario")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> CantidadInteraccionesUsuario() {
        List<Object[]> listaCantidad = iS.CantidadInteraccionesUsuario();
        if (listaCantidad.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay competencias");
        }
        List<QuantityInteraccionesDTO> respuesta = new ArrayList<>();
        for (Object[] fila : listaCantidad) {
            QuantityInteraccionesDTO dto = new QuantityInteraccionesDTO();
            dto.setIdUsuario(((Number) fila[0]).intValue());
            dto.setNombre((String) fila[1]);
            dto.setInteracciones(((Number) fila[2]).intValue());
            respuesta.add(dto);

        }
        return ResponseEntity.ok(respuesta);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<Interaccion> interaccion = iS.listId(id);

        if (interaccion.isPresent()) {
            InteraccionDTO dto = m.map(interaccion.get(), InteraccionDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Interaccion no encontrada");
        }
    }
    @PostMapping("/nuevo")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> registrar(@RequestBody InteraccionDTO dto){
        ModelMapper m = new ModelMapper();
        Optional<Usuario> user = uS.listId(dto.getIdUsuario());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El curso no existe");
        }
        Optional<Comunidad> comu = cS.listId(dto.getIdComunidad());
        if (comu.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La comunidad no existe");
        }
        Interaccion i = m.map(dto, Interaccion.class);
        i.setUsuario(user.get());
        i.setComunidad(comu.get());
        Interaccion inte = iS.insert(i);
        InteraccionDTO responseDTO = m.map(inte, InteraccionDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/actualiza")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<String> actualizar(@RequestBody InteraccionDTO dto) {

        Optional<Interaccion> existente = iS.listId(dto.getIdInteraccion());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Interaccion no encontrado");
        }

        Interaccion inte = existente.get();

        inte.setMensaje(dto.getMensaje());
        inte.setFecha(dto.getFecha());

        iS.update(inte);

        return ResponseEntity.ok("interaccion actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Interaccion> interaccion = iS.listId(id);

        if (interaccion.isPresent()) {
            iS.delete(id);
            return ResponseEntity.ok("interaccion eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("machine no encontrado");
        }
    }

}

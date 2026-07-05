package pe.edu.upc.relaxup.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.relaxup.Dtos.UserDTO;
import pe.edu.upc.relaxup.Entities.Users;
import pe.edu.upc.relaxup.ServiceInterfaces.IUserService;

@RestController
@RequestMapping("/api/User")
public class UserController {
    @Autowired
    private IUserService uS;

    @PostMapping("/nuevo")
    public ResponseEntity<?> registrar(@RequestBody UserDTO dto){
        ModelMapper m = new ModelMapper();
        Users u = m.map(dto, Users.class);

        Users us = uS.insert(u);

        // No devolver la contraseña (ni siquiera el hash) al cliente
        UserDTO responseDTO = new UserDTO();
        responseDTO.setUsername(us.getUsername());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
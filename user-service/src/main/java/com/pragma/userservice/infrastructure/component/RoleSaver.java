package com.pragma.userservice.infrastructure.component;

import com.pragma.userservice.application.dto.request.RoleRequestDto;
import com.pragma.userservice.application.dto.response.RoleResponseDto;
import com.pragma.userservice.application.handler.IRoleHandler;
import com.pragma.userservice.domain.model.ERoles;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class RoleSaver {

    private IRoleHandler roleHandler;

    public RoleSaver(IRoleHandler roleHandler){
        this.roleHandler = roleHandler;
    }

    @PostConstruct
    public void saveRoles() {

        try {
            List<RoleResponseDto> roles = roleHandler.getAllRoles();
        } catch (RuntimeException re) {

            RoleRequestDto roleAdministrador =
                    new RoleRequestDto(ERoles.ROLE_ADMINISTRADOR, "Administrador de la app");
            RoleRequestDto rolePropietario =
                    new RoleRequestDto(ERoles.ROLE_PROPIETARIO, "Propietario de un restaurante");
            RoleRequestDto roleCliente =
                    new RoleRequestDto(ERoles.ROLE_CLIENTE, "Posible cliente de restaurantes");
            RoleRequestDto roleEmpleado =
                    new RoleRequestDto(ERoles.ROLE_EMPLEADO, "Empleado de un restaurante");

            roleHandler.saveRole(roleAdministrador);
            roleHandler.saveRole(rolePropietario);
            roleHandler.saveRole(roleCliente);
            roleHandler.saveRole(roleEmpleado);

        }


    }

}

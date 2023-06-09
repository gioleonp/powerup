package com.pragma.plazoleta.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.rmi.MarshalException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequestDto {

    private Long id;
    @NotBlank(message = "NOMBRE ES UN ATRIBUTO OBLIGATORIO")
    @Pattern(regexp = "(?!^\\d+$)^.+$", message = "NOMBRE INVALIDO")
    private String nombre;

    @NotBlank(message = "DIRECCION ES UN ATRIBUTO OBLIGATORIO")
    private String direccion;

    @NotNull(message = "ID PROPIETARIO ES UN ATRIBUTO OBLIGATORIO")
    @Range(min = 1, message = "ID PROPIETARIO DEBE SER UN NUMERO ENTERO MAYOR A 0")
    private long idPropietario;

    @NotBlank(message = "TELEFONO ES UN ATRIBUTO OBLIGATORIO")
    @Pattern(regexp = "\\+?\\d{12}", message = "TELEFONO INVALIDO")
    private String telefono;

    @NotBlank(message = "URL LOGO ES UN ATRIBUTO OBLIGATORIO")
    private String urlLogo;

    @NotBlank(message = "NIT ES UN ATTRIBUTO OBLIGATORIO")
    @Pattern(regexp = "^\\d+$", message = "NIT INVALIDO")
    private String nit;
}

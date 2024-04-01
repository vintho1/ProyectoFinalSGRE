package co.edu.uniquindio.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {
    private String id;
    private String nombre;
    private String email;

}

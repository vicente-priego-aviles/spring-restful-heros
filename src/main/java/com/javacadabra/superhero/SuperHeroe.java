package com.javacadabra.superhero;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuperHeroe {

    private @Id @GeneratedValue Long id;
    private String nombre;
    private String descripcion;
    private String poder;
}

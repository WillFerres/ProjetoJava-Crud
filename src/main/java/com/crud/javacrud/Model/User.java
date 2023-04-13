package com.crud.javacrud.Model;

import jakarta.persistence.*; //usada para importar todas as classes e interfaces do pacote Jakarta Persistence API em um arquivo Java
import lombok.*; // Lombok é uma biblioteca Java que fornece anotações e recursos para ajudar a reduzir a quantidade de código boilerplate (repetitivo) em classes Java, como getters e setters, construtores,

@Entity //indica que uma classe Java é uma entidade podendo ser mapeada para uma tabela no banco de dados relacional.
@Table(name="users") // Nome da tabela no banco de dados
@Data
@NoArgsConstructor // pacote do lombok pra diminuir codigo
@AllArgsConstructor // pacote do lombok pra diminuir codigo
public class User {

    @Id //Indica que o atributo é a chave primaria da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Define qual atributo vai ser sua chave primaria no banco e vai auto-incrementar
    private Long Id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

}
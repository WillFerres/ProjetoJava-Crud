package com.crud.javacrud.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity // Indica que a classe Produto é uma entidade referenciada pela JPA(tabela no banco)
@Table(name="products") // Nome da tabela no banco de dados
@Data // pacote do lombok pra diminuir codigo
@NoArgsConstructor // pacote do lombok pra diminuir codigo
@AllArgsConstructor // pacote do lombok pra diminuir codigo

public class Product {

    @Id // Indica que o atributo é a chave primaria da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY ) // Define qual atributo vai ser sua chave primaria no banco e vai auto-incrementar
    private Long Id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

}

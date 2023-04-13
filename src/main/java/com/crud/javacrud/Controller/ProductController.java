package com.crud.javacrud.Controller;

import com.crud.javacrud.Model.Product;
import com.crud.javacrud.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; // Retorna um valor personalizado Http
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional; // Se o campo estiver vazio ele retorna vazio, se nao, retorna o dado contido

@RestController // API
@RequestMapping("/products") // rota,  navegador

public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping // Rota Get -> consulta todos os produtos existentes no banco
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productRepository.findAll(); // Criando uma lista com todos os produtos
        return ResponseEntity.ok(products);
    }

    @PostMapping //  Rota Post -> Cadastra um novo produto no banco
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Product newProduct = productRepository.save(product); // Salva o produto criado
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct); // Retorna o código de que foi criado com sucesso, e adiciona a lista.
    }

    @PutMapping("/{id}") // Rota Put -> edita um produto que ja existe no banco e altera seus dados
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) { // a função recebe Id do produto ja existente e os novos dados dele
        Optional<Product> productExists = productRepository.findById(id); // Buscar pelo ID um produto já existente.

        if(productExists.isPresent()) { // Se o produto existir, conseguimos edita-lo.
            Product updatedProduct = productExists.get();

            updatedProduct.setName(product.getName()); // Passamos para o produto, seu novo nome e preço.
            updatedProduct.setPrice(product.getPrice()); // Mesma coisa

            Product savedProduct = productRepository.save(updatedProduct); // Salva o produto com as modificações realizadas.
            return ResponseEntity.ok(savedProduct);
        } else {
            return ResponseEntity.notFound().build(); // Caso o produto não exista, retorna um erro de que não foi encontrado.
        }
    }
    @DeleteMapping("/{id}") // Rota "DELETE" -> Deletar um produto que já existe.
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id); // Verifica se o produto existe.

        if (product.isPresent()) { // Se o produto existir, ele deleta.
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

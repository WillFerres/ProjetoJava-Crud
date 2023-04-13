package com.crud.javacrud.Controller;

import com.crud.javacrud.Model.User;
import com.crud.javacrud.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; // Retorna um valor personalizado Http
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional; // Se o campo estiver vazio ele retorna vazio, se nao, retorna o dado contido

@RestController // API
@RequestMapping("/users") // rota , navegador
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping // Rota que consulta todos os usuários existentes
    private ResponseEntity<?> getAllUser() {
        List<User> user = userRepository.findAll(); // Criando lista com todos os usuarios
        return ResponseEntity.ok(user);
    }

    @PostMapping // Rota que cadastra um novo usuário no banco
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User newUser = userRepository.save(user); // Salva o usuario
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser); // Retorna o código que foi criado e adiciona na lista

    }

    @PutMapping("/{id}") // Rota que edita um usuario que ja existentes para alteralo
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) { // A função recebe o id do usuario existente e os novos dados
        Optional<User> userExists = userRepository.findById(id); // Busca pelo id o usuario existente

        if (userExists.isPresent()) { // Se o produto existir, conseguimos edito-lo
            User updatedUser = userExists.get();

            updatedUser.setFirstName(user.getFirstName()); // Passamos para o produto seus novos dados - nome
            updatedUser.setLastName(user.getLastName()); // Mesma coisa

            User savedUser = userRepository.save(updatedUser); // Salva o usuario com as modificações realiazdas
            return ResponseEntity.ok(savedUser);
        } else {
            return ResponseEntity.notFound().build(); // Caso nao exista o usuario, retorna um erro que nao foi encontrado
        }
    }

    @DeleteMapping("/{id}") // Rota que deleta um usuario existente
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id); // Verifica se o usuario existe
        if (user.isPresent()) { // se o usuario existir conseguimos editar
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

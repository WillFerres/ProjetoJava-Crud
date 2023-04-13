package com.crud.javacrud.Controller;

import com.crud.javacrud.Model.Category;
import com.crud.javacrud.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")

public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping // Rota de consulta
    public ResponseEntity<?> getAllCategory() {
        List<Category> category = categoryRepository.findAll(); // Criando lista com todas categorias
        return ResponseEntity.ok(category);
    }

    @PostMapping // Rota de cadastrar
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        Category newCategory = categoryRepository.save(category); // Salva a categoria criada
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory); // Retorna o codigo criado, e adiciona na lista
    }

    @PutMapping // Rota de editar
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category) { // Recebe o id do produto e seus novos dados
        Optional<Category> categoryExists = categoryRepository.findById(id); // Busca pelo id ja existente

        if(categoryExists.isPresent()){ // Se a categoria existir
            Category updatedCategory = categoryExists.get();

            updatedCategory.setName(category.getName()); // Passamos  para a categoria seu novo nome e descrição
            updatedCategory.setDescription(category.getDescription()); // mesma coisa

            Category savedCategory = categoryRepository.save(updatedCategory); // salva as modificações
            return ResponseEntity.ok(savedCategory);
        } else {
            return ResponseEntity.notFound().build(); // Caso a categoria não exista, retorna erro
        }
    }

    @DeleteMapping // Rota de deletar
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Optional<Category> category = categoryRepository.findById(id);

        if(category.isPresent()) { // Se a categoria existir
            categoryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.notFound().build();
        }

    }

}

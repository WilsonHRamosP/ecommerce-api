
package com.wildev.repository;

import com.wildev.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void shouldSaveAndFindCategory() {
        Category category = new Category(
                "Prueba de categoría",
                "Categoría creada durante la prueba del repositorio"
        );

        Category saved = categoryRepository.save(category);

        assertNotNull(saved.getId());

        Category found = categoryRepository
                .findByNameIgnoreCase("Prueba de categoría")
                .orElseThrow();

        assertEquals("Prueba de categoría", found.getName());
    }
}


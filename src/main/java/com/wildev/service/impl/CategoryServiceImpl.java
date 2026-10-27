package com.wildev.service.impl;

import com.wildev.entity.Category;
import com.wildev.repository.CategoryRepository;
import com.wildev.exception.ResourceNotFoundException;
import com.wildev.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoría no encontrada con ID: " + id));
    }

    @Override
    public Category create(Category category) {
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException(
                    "Ya existe una categoría con ese nombre");
        }

        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        Category existing = findById(id);

        if (!existing.getName().equalsIgnoreCase(category.getName())
                && categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException(
                    "Ya existe una categoría con ese nombre");
        }

        existing.setName(category.getName());
        existing.setDescription(category.getDescription());

        return categoryRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }

}
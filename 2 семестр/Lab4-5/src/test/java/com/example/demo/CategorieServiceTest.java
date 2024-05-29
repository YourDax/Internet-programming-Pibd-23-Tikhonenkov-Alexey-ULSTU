package com.example.demo;

import com.example.demo.categories.model.CategorieEntity;
import com.example.demo.categories.repository.CategorieRepository;
import com.example.demo.categories.service.CategorieService;
import com.example.demo.core.errors.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategorieServiceTest {

    @Mock
    private CategorieRepository categorieRepository;

    @InjectMocks
    private CategorieService categorieService;

    @Test
    public void testGetCategoryById() {
        CategorieEntity category = new CategorieEntity();
        when(categorieRepository.findById(anyLong())).thenReturn(Optional.of(category));

        CategorieEntity result = categorieService.get(1L);

        assertNotNull(result);
        verify(categorieRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testGetCategoryByIdNotFound() {
        when(categorieRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> categorieService.get(1L));
    }

    @Test
    public void testCreateCategory() {
        CategorieEntity category = new CategorieEntity();
        when(categorieRepository.save(any(CategorieEntity.class))).thenReturn(category);

        CategorieEntity createdCategory = categorieService.create(category);

        assertNotNull(createdCategory);
        verify(categorieRepository, times(1)).save(any(CategorieEntity.class));
    }

    @Test
    public void testDeleteCategory() {
        CategorieEntity category = new CategorieEntity();
        when(categorieRepository.findById(anyLong())).thenReturn(Optional.of(category));

        CategorieEntity deletedCategory = categorieService.delete(1L);

        assertNotNull(deletedCategory);
        verify(categorieRepository, times(1)).delete(any(CategorieEntity.class));
    }
}

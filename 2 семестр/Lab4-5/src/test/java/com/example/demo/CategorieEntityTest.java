package com.example.demo;

import org.junit.jupiter.api.Test;

import com.example.demo.categories.model.CategorieEntity;

import static org.junit.jupiter.api.Assertions.*;

public class CategorieEntityTest {

    @Test
    public void testCategorieEntityEqualsAndHashCode() {
        CategorieEntity category1 = new CategorieEntity("Action", "image.jpg");
        CategorieEntity category2 = new CategorieEntity("Action", "image.jpg");

        assertEquals(category1, category2);
        assertEquals(category1.hashCode(), category2.hashCode());

        category2.setName("Drama");
        assertNotEquals(category1, category2);
        assertNotEquals(category1.hashCode(), category2.hashCode());
    }

    @Test
    public void testCategorieEntitySettersAndGetters() {
        CategorieEntity category = new CategorieEntity();
        category.setName("Action");
        category.setImage("image.jpg");

        assertEquals("Action", category.getName());
        assertEquals("image.jpg", category.getImage());
    }
}

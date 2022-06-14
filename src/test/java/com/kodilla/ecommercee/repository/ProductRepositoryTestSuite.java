package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Group;
import com.kodilla.ecommercee.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
class ProductRepositoryTestSuite {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findAllProducts_CartShouldBeEmpty_GroupShouldNotBeEmpty() {
        //Given
        Group fruits = new Group("Fruits", new ArrayList<>());
        Product apple = new Product("Apple", 3.20, fruits);
        Product banana = new Product("Banana", 4.10, fruits);

        //When
        productRepository.save(apple);
        productRepository.save(banana);
        Long appleId = apple.getId();
        Long bananaId = banana.getId();
        List<Group> groupList = groupRepository.findAll();
        List<Product> foundedProducts = productRepository.findAll();
        List<Cart> emptyCart = cartRepository.findAll();

        //Then
        assertEquals(2, foundedProducts.size());
        assertEquals(1, groupList.size());
        assertEquals(0, emptyCart.size());

        //CleanUp
        productRepository.deleteById(appleId);
        productRepository.deleteById(bananaId);
        groupRepository.delete(fruits);
    }

    @Test
    void findProductById() {
        //Given
        Group fruits = new Group("Fruits", new ArrayList<>());
        Product apple = new Product("Apple", 3.20, fruits);

        //When
        groupRepository.save(fruits);
        productRepository.save(apple);
        Long appleId = apple.getId();
        Optional<Product> foundedProduct = productRepository.findById(appleId);

        //Then
        assertTrue(foundedProduct.isPresent());

        //CleanUp
        productRepository.deleteById(appleId);
        groupRepository.delete(fruits);
    }

    @Test
    void updateProduct() {
        //Given
        Group fruits = new Group("Fruits", new ArrayList<>());
        Product apple = new Product("Apple", 3.20, fruits);

        //When
        productRepository.save(apple);
        apple.setName("GoldenApple");
        apple.setPrice(3.80);
        Long appleId = apple.getId();
        Optional<Product> goldenApple = productRepository.findById(appleId);
        Double expectedPrice = 3.80;

        //Then
        assertTrue(goldenApple.isPresent());
        assertEquals("GoldenApple", goldenApple.get().getName());
        assertEquals(expectedPrice, goldenApple.get().getPrice());

        //CleanUp
        productRepository.deleteById(appleId);
        groupRepository.delete(fruits);
    }

    @Test
    void deleteProductById() {
        //Given
        Group fruits = new Group("Fruits", new ArrayList<>());
        Product apple = new Product("Apple", 3.20, fruits);

        //When
        productRepository.save(apple);
        Long appleId = apple.getId();
        productRepository.deleteById(appleId);
        Optional<Product> deletedProduct = productRepository.findById(appleId);

        //Then
        assertFalse(deletedProduct.isPresent());

        //CleanUp
        groupRepository.delete(fruits);
    }
}

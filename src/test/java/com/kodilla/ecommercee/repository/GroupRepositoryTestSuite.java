package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Group;
import com.kodilla.ecommercee.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
class GroupRepositoryTestSuite {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findAllGroupsTest(){

        //Given
        Group group = new Group("mieso", new ArrayList<>());
        Group group2 = new Group("nabial", new ArrayList<>());
        Group group3 = new Group("slodycze", new ArrayList<>());

        Product product = new Product("kielbasa", 12.50, group);
        Product product2 = new Product("szynka", 15.25, group);
        Product product3 = new Product("mleko", 3.39, group2);
        Product product4 = new Product("czekolada", 5.29, group3);
        Product product5 = new Product("cukierki", 2.12, group3);
        Product product6 = new Product("baton", 1.12, group3);

        group.getProducts().add(product);
        group.getProducts().add(product2);
        group2.getProducts().add(product3);
        group3.getProducts().add(product4);
        group3.getProducts().add(product5);
        group3.getProducts().add(product6);

        groupRepository.save(group);
        groupRepository.save(group2);
        groupRepository.save(group3);

        Long id = group.getId();
        Long id1 = group2.getId();
        Long id2 = group3.getId();

        //When
        List<Group> resultGroups = groupRepository.findAll();

        //Then
        assertEquals(3, resultGroups.size());

        //CleanUp
        groupRepository.deleteById(id);
        groupRepository.deleteById(id1);
        groupRepository.deleteById(id2);

    }

    @Test
    void findGroupByIdTest(){

        //Given
        Group group = new Group("mieso", new ArrayList<>());

        Product product = new Product("kielbasa", 12.50, group);
        Product product2 = new Product("szynka", 15.25, group);

        group.getProducts().add(product);
        group.getProducts().add(product2);

        groupRepository.save(group);
        Long id = group.getId();

        //When
        Optional<Group> resultGroup = groupRepository.findById(id);

        //Then
        assertTrue(resultGroup.isPresent());
        assertEquals(group.getName(), resultGroup.get().getName());
        assertEquals(group.getProducts().size(), resultGroup.get().getProducts().size());

        //CleanUp
        groupRepository.deleteById(id);

    }

    @Test
    void updateGroupTest(){
        //Given
        Group group = new Group("mieso", new ArrayList<>());

        Product product = new Product("kielbasa", 12.50, group);
        Product product2 = new Product("szynka", 15.25, group);

        group.getProducts().add(product);
        group.getProducts().add(product2);

        groupRepository.save(group);

        productRepository.save(product);
        productRepository.save(product2);

        Long groupId = group.getId();
        Long productId = product.getId();
        Long product2Id = product2.getId();

        //When
        Product product3 = new Product("boczek", 11.55, group);
        group.getProducts().add(product3);
        productRepository.save(product3);
        Long product3Id = product3.getId();
        groupRepository.save(group);

        Optional<Group> updatedGroup = groupRepository.findById(groupId);
        List<String> resultGroupNames = updatedGroup.get().getProducts().stream()
                .map(Product::getName)
                .collect(Collectors.toList());

        //Then
        assertEquals(3, updatedGroup.get().getProducts().size());
        assertTrue(resultGroupNames.contains("boczek"));

        //CleanUp
        groupRepository.deleteById(groupId);
        productRepository.deleteById(productId);
        productRepository.deleteById(product2Id);
        productRepository.deleteById(product3Id);

    }
}
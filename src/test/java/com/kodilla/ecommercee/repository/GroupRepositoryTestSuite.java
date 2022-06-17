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

        Product product = Product.builder().name("kielbasa").price(12.5).group(group).build();
        Product product2 = Product.builder().name("szynka").price(15.25).group(group).build();
        Product product3 = Product.builder().name("mleko").price(3.39).group(group2).build();
        Product product4 = Product.builder().name("czekolada").price(5.29).group(group3).build();
        Product product5 = Product.builder().name("cukierki").price(2.12).group(group3).build();
        Product product6 = Product.builder().name("baton").price(1.12).group(group3).build();

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

        Product product = Product.builder().name("kielbasa").price(12.5).group(group).build();
        Product product2 = Product.builder().name("szynka").price(15.25).group(group).build();

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

        Product product = Product.builder().name("kielbasa").price(12.5).group(group).build();
        Product product2 = Product.builder().name("szynka").price(15.25).group(group).build();

        group.getProducts().add(product);
        group.getProducts().add(product2);

        groupRepository.save(group);

        productRepository.save(product);
        productRepository.save(product2);

        Long groupId = group.getId();
        Long productId = product.getId();
        Long product2Id = product2.getId();

        //When
        Product product3 = Product.builder().name("boczek").price(11.55).group(group).build();
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

    @Test
    void deleteProductTest(){

        //Given
        Group group = new Group("ptaki", new ArrayList<>());

        Product product = Product.builder().name("wrobel").price(12.50).group(group).build();
        Product product2 = Product.builder().name("kanarek").price(15.25).group(group).build();
        group.getProducts().add(product);
        group.getProducts().add(product2);

        groupRepository.save(group);

        productRepository.save(product);
        productRepository.save(product2);
        Long productId2 = product2.getId();

        //When
        Long productId = product.getId();
        productRepository.deleteById(productId);
        Long groupId = group.getId();
        Optional<Group> resultGroup = groupRepository.findById(groupId);


        //Then
        assertTrue(resultGroup.isPresent());

        //CleanUp
        groupRepository.deleteById(groupId);
        productRepository.deleteById(productId2);
    }
}
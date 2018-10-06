package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test(expected = EntityNotFoundException.class)
    public void findById() {
        String id = "id-inexistente";

        productService.findById(id);
    }

    @Test
    public void save() {
        Product product = new Product();
        product.setId("new-id");
        product.setDescription("test-description");

        Product newProduct = productService.save(product);

        Assert.assertEquals(product.getId(), newProduct.getId());
        Assert.assertEquals(product.getDescription(), newProduct.getDescription());
    }

}
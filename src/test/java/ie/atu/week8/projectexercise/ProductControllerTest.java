package ie.atu.week8.projectexercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void testGetProductById() throws Exception {
        Product product = new Product(1L, "Kettle", "Boils", 35);
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        mockmvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kettle"));


    }

    @Test
    void testCreateProduct() throws Exception {
        Product product = new Product(null, "Kettle", "Boils", 35);
        when(productService.saveProduct(any(Product.class))).thenReturn(product);
        //productService.saveProduct(product);

        ObjectMapper om = new ObjectMapper();
        String valueJson = om.writeValueAsString(product);

        mockmvc.perform(post("/products").contentType("application/json").content(valueJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Kettle"));

    }

    @Test
    void testUpdateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}
import db.ProductRepository;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductDataProcessorTest {

    ProductRepository repository;

    ProductDataProcessor productDataProcessor;
    List<Product> products = new ArrayList<>();

    @BeforeEach
    void setUp() {
        repository = mock(ProductRepository.class);

        products.add(new Product("Macbook Pro 13-inch", "Apple", 1050.0));
        products.add(new Product("Thinkpad T480s", "Lenovo", 1200.0));
        products.add(new Product("XPS 13", "Dell", 1000.0));
    }

    @Test
    void shouldCollectAllProductsPerBrand() {
        //given
        when(repository.getAllProducts()).thenReturn(products);
        productDataProcessor = new ProductDataProcessor(repository);

        //when
        List<Product> productsByBrand = productDataProcessor.collectAllProductsPerBrand("Lenovo");

        //then
        assertThat(productsByBrand.size()).isEqualTo(1);
        assertThat(productsByBrand.get(0).getPrice()).isEqualTo(1200.0);

        //TODO Task 1:
        // Using Mockito, check if correct method from the db.ProductRepository has been invoked

    }

    //TODO Task 2:
    // Check if ProductDataProcessor throws exception if it fails to update products.
    // HINT: Use Mockito to throw exception from repository
    @Test
    void shouldThrowExceptionWhenProductsCannotBeUpdated() {
        //given

        //when

        //then
    }

    //TODO Task 3:
    // Write a test case to check if the prices are actually lower after calling applyDiscountToAllProducts() method.
    // HINT: use argument captor to check if discount was applied
    @Test
    void shouldApplyDiscountToProducts() {
        //given

        //when

        //then
    }

    //TODO Task 4:
    // Write a test case to check if correct method in the repository is called.
    // Make sure it is called for every deleted item.
    @Test
    void shouldDeleteEveryGivenProduct() {
        //given

        //when

        //then
    }
}
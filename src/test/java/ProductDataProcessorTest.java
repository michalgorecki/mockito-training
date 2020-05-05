import exception.ProductDataException;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProductDataProcessorTest {

    ProductRepository repository;

    ProductDataProcessor productDataProcessor;
    List<Product> products = new ArrayList<>();

    @BeforeEach
    void setUp() {
        repository = mock(ProductRepository.class);

        products.add(new Product("Macbook Pro 13-inch","Apple", 1050.0));
        products.add(new Product("Thinkpad T480s","Lenovo", 1200.0));
        products.add(new Product("XPS 13","Dell", 1000.0));
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

        verify(repository, times(1)).getAllProducts();
    }

    @Test
    void shouldApplyDiscountToProducts() throws ProductDataException {
        //given
        when(repository.getAllProducts()).thenReturn(products);
        productDataProcessor = new ProductDataProcessor(repository);
        ArgumentCaptor<List<Product>> captor = ArgumentCaptor.forClass(List.class);

        //when
        productDataProcessor.applyDiscountToAllProducts(10);

        //then

        verify(repository, times(1)).getAllProducts();
        verify(repository, times(1)).updateProducts(captor.capture());

        List<Product> discountedProducts = captor.getValue();
        assertThat(discountedProducts.get(0).getPrice()).isEqualTo(945.0);
    }




}
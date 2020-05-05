import exception.ProductDataException;
import model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getAllProducts();

    List<Product> getProductsByName(final String name) throws ProductDataException;

    void updateProducts(final List<Product> products) throws ProductDataException;
}

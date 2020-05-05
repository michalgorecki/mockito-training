package db;

import exception.ProductDataException;
import model.Product;

import java.util.List;

public interface ProductRepository {

    /**
     * Fetch all products, e.g. from the database
     *
     * @return List of all products
     */
    List<Product> getAllProducts();

    /**
     * Update given products
     *
     * @param products to be updated
     * @throws ProductDataException
     */
    void updateProducts(final List<Product> products) throws ProductDataException;

    /**
     * @param product to be deleted
     */
    void deleteProduct(final Product product);
}

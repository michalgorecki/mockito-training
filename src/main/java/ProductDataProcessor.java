import db.ProductRepository;
import exception.ProductDataException;
import model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDataProcessor {

    public static final String INVALID_DISCOUNT_MESSAGE = "Discount percentage is not in range 1-99";
    private ProductRepository productRepository;

    public ProductDataProcessor(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Fetch all products having the given brand
     *
     * @param brand to filter the products
     * @return List of products
     */
    public List<Product> collectAllProductsPerBrand(final String brand) {
        List<Product> allProductsList = productRepository.getAllProducts();
        return allProductsList.stream()
                .filter(product -> product.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    /**
     * Change all prices by applying a discount
     *
     * @param percent percentage of the discount
     * @throws ProductDataException if the discount is too small or too large
     */
    public void applyDiscountToAllProducts(final int percent) throws ProductDataException {
        if (percent > 99 || percent < 1) {
            throw new ProductDataException(INVALID_DISCOUNT_MESSAGE);
        }
        List<Product> productList = productRepository.getAllProducts();
        productList.forEach(product -> {
            double newPrice = calculateDiscountedPrice(product, percent);
            product.setPrice(newPrice);
        });

        productRepository.updateProducts(productList);
    }

    /**
     * Delete given products
     *
     * @param products to be deleted
     */
    public void deleteProducts(final List<Product> products) {
        products.forEach(product -> productRepository.deleteProduct(product));
    }

    private double calculateDiscountedPrice(final Product product, final int percent) {
        return product.getPrice() * (100 - percent) / 100;
    }
}

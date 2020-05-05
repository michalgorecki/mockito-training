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

    public List<Product> collectAllProductsPerBrand(final String brand) {
        List<Product> allProductsList = productRepository.getAllProducts();
        return allProductsList.stream()
                .filter(product -> product.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

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

    private double calculateDiscountedPrice(final Product product, final int percent) {
        return product.getPrice() * (100 - percent) / 100;
    }
}

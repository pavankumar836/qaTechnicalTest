import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import java.math.BigDecimal;


public class ProductTests extends ProjectTestBase {

    @Test
    public void createNewProductAndValidateResponse() {
       Product product = new Product("item1", new BigDecimal("12.55"));
        Product.product()
                .createProduct(product.name, product.price, 204)
                .validateRecord(Product.productId, product.name, product.price);
    }

    @Test
    public void deleteExistingProductAndVerify() {
        Product product = new Product("item1", new BigDecimal("12.56"));
        Product.product()
                .createProduct(product.name, product.price, 204)
                .verifyProductDelete();
    }

    @Test
    public void updateExistingProductAndValidate() {

        Product existingRecord = Product.product().GetLastItemDetails();
        Product product = new Product("item2", new BigDecimal("12.29"));
        Product.product()
                .createProduct(existingRecord.name, existingRecord.price, 204)
                .updateExistingProduct(Product.productId, product.name, product.price, 204)
                .validateRecord(Product.productId, product.name, product.price);
    }

    @Test
    public void createNewProductWithExistingProductDetails() {

        Product existingRecord = Product.product().GetLastItemDetails();
        Product.product()
                .createProduct(existingRecord.name, existingRecord.price, 204)
                .validateRecord(existingRecord.id + 1, existingRecord.name, existingRecord.price);
    }

    @Test
    public void createNewProductWithDifferentNameAndSamePrice() {
        Product existingRecord = Product.product().GetLastItemDetails();
        Product.product()
                .createProduct("differentName", existingRecord.price, 204)
                .validateRecord(Product.productId, "differentName", existingRecord.price);

    }

    @Test
    public void createNewProductWithoutNameAndWithNegativePrice() {
        Product product = new Product("", new BigDecimal("-0.05"));
        Product.product()
                .createProduct(product.name, product.price, 204)
                .validateRecord(Product.productId, product.name, product.price);

    }

    @Test
    public void createNewProductNameWithLongLength() {
        String name = RandomStringUtils.random(600, true, true);
        Product product = new Product(name, new BigDecimal("6565.12"));
        Product.product()
                .createProduct(product.name, product.price, 204)
                .validateRecord(Product.productId, name, product.price);
    }


    @Test
    public void createNewProductWithLongLengthNameAndPrice() {
        String name = RandomStringUtils.random(600, true, true);
        BigDecimal price = new BigDecimal(RandomStringUtils.random(27, false, true));
        Product product = new Product(name, price);
        Product.product()
                .createProduct(product.name, product.price, 204)
                .validateRecord(Product.productId, name, product.price);
    }



    @Test
    public void createNewProductWithLongPriceLength() {

        BigDecimal price = new BigDecimal("23456789234567892345678923456789.00");
        Product.product()
                .createProduct("item", price, 204)
                .validateRecord(Product.productId, "item", price);
    }
    @Test
    public void createNewProductPriceWithLeadingZeros() {

        BigDecimal price = new BigDecimal("100000000000000000000000.00");
        Product.product()
                .createProduct("item", price, 204)
                .validateRecord(Product.productId, "item", price);
    }
    @Test
    public void createNewProductPriceStartsWith23AndWithLeadingZeros() {

        BigDecimal price = new BigDecimal("2300000000000000000000000.00");
        Product.product()
                .createProduct("item", price, 204)
                .validateRecord(Product.productId, "item", price);
    }

}
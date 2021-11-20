import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;



import java.math.BigDecimal;


public class ProductTests extends ProjectTestBase {

    @Test
    public void createNewProductAndValidateResponse() {

        Product.product()
                .createProduct("item1",12.55,204)
                .validateRecord(Product.productId,"item1",12.55);
    }
    @Test
    public  void deleteExistingProductAndVerify() {

        Product.product()
                .createProduct("item1",12.55,204)
                .verifyProductDelete();
    }

    @Test
    public  void updateExistingProductAndValidate() {

        Product existingRecord = Product.product().GetLastItemDetails();
        Product.product()
                .createProduct(existingRecord.name,existingRecord.price,204)
                .updateExistingProduct(Product.productId,"item2",12.29,204)
                .validateRecord(Product.productId,"item2",12.29);
    }

    @Test
    public  void createNewProductWithExistingProductDetails() {

        Product existingRecord = Product.product().GetLastItemDetails();
        Product.product()
                .createProduct(existingRecord.name,existingRecord.price,204)
                .validateRecord(existingRecord.id+1,existingRecord.name,existingRecord.price);
    }

    @Test
    public void createNewProductWithDifferentNameAndSamePrice() {
        Product existingRecord = Product.product().GetLastItemDetails();
        Product.product()
                .createProduct("differentName",existingRecord.price,204)
                .validateRecord(Product.productId,"differentName",existingRecord.price);

    }
    @Test
    public  void createNewProductWithoutNameAndWithNegativePrice() {

        Product.product()
                .createProduct("",-0.88,204)
                .validateRecord(Product.productId,"",-0.88);

    }

    @Test
    public void createNewProductNameWithLongLength(){
        String name = RandomStringUtils.random(600, true, true);

        Product.product()
                .createProduct(name,6565.88,204)
                .validateRecord(Product.productId,name,6565.88);
    }


    @Test
    public void createNewProductPriceWithLongLength(){

        Double price =23456789234567892345678923456789.00;

        Product.product()
                .createProduct("item",price,204)
                .validateRecord(Product.productId,"item",price);
    }

}
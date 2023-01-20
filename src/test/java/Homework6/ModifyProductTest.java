package Homework6;

import Homework5.api.dto.Product;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModifyProductTest extends AbstractTests {

    Product product = null;
    Product modifiedProduct = null;
    String titleMyProduct = "Сherry";
    String categoryMyProduct = "Food";
    int priceMyProduct = 300;
    int newPriceMyProduct = 350;
    long id;
    db.dao.ProductsMapper productsMapper = session.getMapper(db.dao.ProductsMapper.class);
    db.model.ProductsExample example = new db.model.ProductsExample();

    @BeforeEach
    void setUp() {
        product = new Product()
                .withCategoryTitle(categoryMyProduct)
                .withTitle(titleMyProduct)
                .withPrice(priceMyProduct);
    }

    @Test
    void ModifyProductPositiveTest() throws IOException {
        Response<Product> response = productService.createProduct(product)
                .execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));

        id = response.body().getId();

        example.createCriteria().andIdEqualTo(id);
        List<db.model.Products> list = productsMapper.selectByExample(example);

        assertThat(list.get(0).getId(), equalTo(id));
        assertThat(list.get(0).getTitle(), equalTo(titleMyProduct));
        assertThat(list.get(0).getPrice(), equalTo(priceMyProduct));

        session.commit();

        modifiedProduct = new Product()
                .withId((int) id)
                .withTitle(titleMyProduct)
                .withCategoryTitle(categoryMyProduct)
                .withPrice(newPriceMyProduct);

        Response <Product> response2 = productService.modifyProduct(modifiedProduct)
                .execute();
        assertThat(response2.isSuccessful(), CoreMatchers.is(true));

        example.createCriteria().andIdEqualTo(id);
        List<db.model.Products> list2 = productsMapper.selectByExample(example);

        assertThat(list2.get(0).getId(), equalTo(id));
        assertThat(list2.get(0).getTitle(), equalTo(titleMyProduct));
        assertThat(list2.get(0).getPrice(), equalTo(newPriceMyProduct));

        session.commit();

    }
}


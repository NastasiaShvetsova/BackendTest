package Homework6;

import Homework5.api.dto.Product;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetProductByIdTest extends AbstractTests {

    db.dao.ProductsMapper productsMapper = session.getMapper(db.dao.ProductsMapper.class);
    db.model.ProductsExample example = new db.model.ProductsExample();

    @SneakyThrows
    @Test
    void getProductByIdPositiveTest() {

        Response<Product> response = productService.getProductById(1).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));

        example.createCriteria().andIdEqualTo(1L);
        List<db.model.Products> list = productsMapper.selectByExample(example);
        assertThat(response.body().getTitle(), equalTo(list.get(0).getTitle()));
        assertThat(response.code(), CoreMatchers.equalTo(200));
    }

    @SneakyThrows
    @Test
    void getProductByInvalidId() {

        Response<Product> response = productService.getProductById(10).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(false));

        example.createCriteria().andIdEqualTo(10L);
        assertThat(productsMapper.selectByExample(example).size(), equalTo(0));
        assertThat(response.code(), CoreMatchers.equalTo(404));

    }
}


package Homework6;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteProductTests extends AbstractTests {

    @SneakyThrows
    @Test

    void deleteProductByIdPositiveTest() {

        Response<ResponseBody> response = productService.deleteProduct(15).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

        db.dao.ProductsMapper productsMapper = session.getMapper(db.dao.ProductsMapper.class);
        db.model.ProductsExample example = new db.model.ProductsExample();

        example.createCriteria().andIdEqualTo(15l);
        List<db.model.Products> list = productsMapper.selectByExample(example);
        assertThat(list.isEmpty(), Matchers.is(true));
    }
}

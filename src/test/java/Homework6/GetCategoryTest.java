package Homework6;

import Homework5.api.CategoryService;
import Homework5.api.dto.GetCategoryResponse;
import Homework5.api.utils.RetrofitUtils;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetCategoryTest {
    static CategoryService categoryService;
    static SqlSession session = null;

    static db.dao.CategoriesMapper categoriesMapper;
    static db.model.CategoriesExample example;

    @BeforeAll
    static void beforeAll() throws IOException {

        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
        categoriesMapper = session.getMapper(db.dao.CategoriesMapper.class);
        example = new db.model.CategoriesExample();
    }

    @SneakyThrows
    @Test
    void getCategoryByIdPositiveTest() {

        Response<GetCategoryResponse> response = categoryService.getCategory(1).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(1));
        assertThat(response.body().getTitle(), equalTo("Food"));
        response.body().getProducts().forEach(product ->
                assertThat(product.getCategoryTitle(), equalTo("Food")));
        example.createCriteria().andIdEqualTo(1l);
        List<db.model.Categories> list = categoriesMapper.selectByExample(example);
        assertThat(response.body().getTitle(), equalTo(list.get(0).getTitle()));
        assertThat(response.code(), CoreMatchers.equalTo(200));
    }
    @Test
    void getCategoryByIdNegativeTest() throws IOException {
        Response response = categoryService.getCategory(9).execute();
        assertThat(response.code(), equalTo(404));
    }
    @AfterAll
    static void afterAll(){
        session.close();
    }
}
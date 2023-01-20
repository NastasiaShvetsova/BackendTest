package db.dao;

import db.model.Categories;
import db.model.CategoriesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategoriesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table categories
     *
     * @mbg.generated Tue Jan 17 22:09:07 MSK 2023
     */
    long countByExample(CategoriesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table categories
     *
     * @mbg.generated Tue Jan 17 22:09:07 MSK 2023
     */
    int deleteByExample(CategoriesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table categories
     *
     * @mbg.generated Tue Jan 17 22:09:07 MSK 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table categories
     *
     * @mbg.generated Tue Jan 17 22:09:07 MSK 2023
     */
    int insert(Categories row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table categories
     *
     * @mbg.generated Tue Jan 17 22:09:07 MSK 2023
     */
    int insertSelective(Categories row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table categories
     *
     * @mbg.generated Tue Jan 17 22:09:07 MSK 2023
     */
    List<Categories> selectByExample(CategoriesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table categories
     *
     * @mbg.generated Tue Jan 17 22:09:07 MSK 2023
     */
    Categories selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table categories
     *
     * @mbg.generated Tue Jan 17 22:09:07 MSK 2023
     */
    int updateByExampleSelective(@Param("row") Categories row, @Param("example") CategoriesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table categories
     *
     * @mbg.generated Tue Jan 17 22:09:07 MSK 2023
     */
    int updateByExample(@Param("row") Categories row, @Param("example") CategoriesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table categories
     *
     * @mbg.generated Tue Jan 17 22:09:07 MSK 2023
     */
    int updateByPrimaryKeySelective(Categories row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table categories
     *
     * @mbg.generated Tue Jan 17 22:09:07 MSK 2023
     */
    int updateByPrimaryKey(Categories row);
}
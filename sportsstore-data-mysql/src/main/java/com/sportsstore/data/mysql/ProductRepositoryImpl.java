package com.sportsstore.data.mysql;

import com.sportsstore.data.contracts.ProductRepository;
import com.sportsstore.models.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Repository("mysql")
@Transactional(propagation = Propagation.SUPPORTS)
public class ProductRepositoryImpl implements ProductRepository {

    private final static int ONE_ROW = 1;

    @Inject
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProducts(int page, String category, int itemsPerPage) {

        String query;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("page", ((page - 1) * itemsPerPage))
                .addValue("itemsPerPage", itemsPerPage);

        if (StringUtils.isBlank(category)) {
            query = "select * from products order by productId limit :page, :itemsPerPage";
        } else {
            query = "select * from products where category = :category order by productId limit :page, :itemsPerPage";
            params.addValue("category", category);
        }
        return jdbcTemplate.query(query, params, BeanPropertyRowMapper.newInstance(Product.class));
    }

    @Override
    public int getProductCountFor(String category) {
        if (StringUtils.isBlank(category))
            return jdbcTemplate.queryForObject("select count(*) from products", new MapSqlParameterSource(), Integer.class);

        final String query = "select count(*) from products where category = :category";
        return jdbcTemplate.queryForObject(query, new MapSqlParameterSource("category", category), Integer.class);
    }

    @Override
    public List<String> getAllCategories() {
        return jdbcTemplate.queryForList("select distinct category from products order by category", new MapSqlParameterSource(), String.class);
    }

    @Override
    public Product getProductById(int productId) {
        return jdbcTemplate.queryForObject("select * from products where productId = :id",
                new MapSqlParameterSource("id", productId), BeanPropertyRowMapper.newInstance(Product.class));
    }

    @Override
    public List<Product> getAllProducts() {
        return jdbcTemplate.query("select * from products order by category", BeanPropertyRowMapper.newInstance(Product.class));
    }

    @Override
    public boolean saveProduct(Product p) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", p.getName())
                .addValue("description", p.getDescription())
                .addValue("price", p.getPrice())
                .addValue("category", p.getCategory());

        return  p.getProductId() == 0 ? addProduct(p, params) : updateProduct(p, params);
    }

    @Override
    public boolean delete(int productId) {
        int rowAffected = jdbcTemplate.update("delete from products where productId = :productId", new MapSqlParameterSource("productId", productId));
        return rowAffected == ONE_ROW;
    }

    private boolean addProduct(Product p, SqlParameterSource params) {
        final String sql = "insert into products (name, description, price, category) " +
                "values (:name, :description, :price, :category)";

        return jdbcTemplate.update(sql, params) == ONE_ROW;
    }

    private boolean updateProduct(Product p, MapSqlParameterSource params) {
        final String sql = "update products set name = :name, description = :description, price = :price, " +
                "category = :category where productId = :productId";

        params.addValue("productId", p.getProductId());

        return jdbcTemplate.update(sql, params) == ONE_ROW;
    }

}














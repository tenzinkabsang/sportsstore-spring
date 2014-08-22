package com.sportsstore.data.mysql;

import com.sportsstore.data.contracts.ProductRepository;
import com.sportsstore.models.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("mysql")
@Transactional(propagation = Propagation.SUPPORTS)
public class ProductRepositoryImpl implements ProductRepository {

    @Inject
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProducts(int page, String category, int itemsPerPage) {

        String query;
        SqlParameterSource params;
        if(category == null || category == ""){
            query = "select * from products order by productId limit :page, :itemsPerPage";
            params = new MapSqlParameterSource()
                    .addValue("page", ((page - 1) * itemsPerPage))
                    .addValue("itemsPerPage", itemsPerPage);

        } else {
            query = "select * from products where category = :category order by productId limit :page, :itemsPerPage";
            params = new MapSqlParameterSource()
                    .addValue("category", category)
                    .addValue("page", ((page - 1) * itemsPerPage))
                    .addValue("itemsPerPage", itemsPerPage);
        }



        return jdbcTemplate.query(query, params, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int index) throws SQLException {
                Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setCategory(rs.getString("category"));
                p.setPrice(rs.getBigDecimal("price"));
                return p;
            }
        });
    }

    @Override
    public int getProductCountFor(String category) {
        if(category == null || category.isEmpty())
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
        return jdbcTemplate.queryForObject("select * from products where productId = :id", new MapSqlParameterSource("id", productId),
                new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet rs, int i) throws SQLException {
                        Product p = new Product();
                        p.setProductId(rs.getInt("productId"));
                        p.setName(rs.getString("name"));
                        p.setDescription(rs.getString("description"));
                        p.setCategory(rs.getString("category"));
                        p.setPrice(rs.getBigDecimal("price"));
                        return p;
                    }
                });
    }
}

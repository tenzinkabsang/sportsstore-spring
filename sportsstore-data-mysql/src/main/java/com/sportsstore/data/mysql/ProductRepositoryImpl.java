package com.sportsstore.data.mysql;

import com.sportsstore.data.contracts.ProductRepository;
import com.sportsstore.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Product> getAllProducts(int page, int itemsPerPage) {

        final String query = "select * from products order by productId limit :page, :itemsPerPage";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("page", ((page - 1) * itemsPerPage))
                .addValue("itemsPerPage", itemsPerPage);

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
}

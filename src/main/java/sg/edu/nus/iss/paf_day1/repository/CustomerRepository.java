package sg.edu.nus.iss.paf_day1.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_day1.model.Customer;

@Repository
public class CustomerRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String findAllSql="select id, first_name, last_name from customer";
    private final String findByIdSql= "select * from customer where id = ?";
    private final String findAllLimitOffsetSql="select * from customer limit ? offset ?";

    public List<Customer> getAllCustomers(){
        List<Customer> customerList = new ArrayList<Customer>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllSql);

    
        while (rs.next()){
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));

            customerList.add(customer);
        }
        
        return Collections.unmodifiableList(customerList);
    }

    public Customer getCustomersById(int id){
        Customer customer = new Customer();

        customer = jdbcTemplate.queryForObject
        (findByIdSql, BeanPropertyRowMapper.newInstance(Customer.class),id);

        return customer;
    }

    public List<Customer> getAllCustomersWithLimitOff(int limit, int offset){
        List<Customer> customerList = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllLimitOffsetSql,limit,offset);

        while (rs.next()){
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customerList.add(customer);
        }
        return Collections.unmodifiableList(customerList);
    }

}
    
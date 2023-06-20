package sg.edu.nus.iss.paf_day1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.paf_day1.model.Customer;
import sg.edu.nus.iss.paf_day1.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    @Autowired
    CustomerService custSvc;

    @GetMapping
    public List<Customer> getAllCustomers(){
        return custSvc.getAll();
    }

    @GetMapping("/limit")
    public List<Customer> getAllCustomersWithLimitAndOffset(@RequestParam("limit") int limit, @RequestParam("offset") int offset){
        return custSvc.getCustomerLimitOffset(limit, offset);
    }

    @GetMapping("/{cust-id}")
    public Customer getCustomer(@PathVariable("cust-id") int custId){
        return custSvc.getById(custId);
    }
}

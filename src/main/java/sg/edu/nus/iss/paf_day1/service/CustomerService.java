package sg.edu.nus.iss.paf_day1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_day1.model.Customer;
import sg.edu.nus.iss.paf_day1.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository cRepo;

    public List<Customer> getAll(){
        return cRepo.getAllCustomers();
    }

    public Customer getById(int id){
        return cRepo.getCustomersById(id);
    }

    public List<Customer> getCustomerLimitOffset(int limit, int offset){
        return cRepo.getAllCustomersWithLimitOff(limit, offset);
    }

}

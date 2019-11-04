package com.devaj.happens.config;

import com.devaj.happens.model.*;
import com.devaj.happens.repository.BranchRepository;
import com.devaj.happens.repository.ClientRepository;
import com.devaj.happens.repository.UserRepository;
import com.devaj.happens.service.ProductService;
import com.devaj.happens.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ProductService productService;

    @Autowired
    private StockService stockService;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<Product> products = productService.listAll();

        if(products.isEmpty()){
            this.createProduct("Produto 1", "Descrição", "1234", 100.0, 50);
            this.createProduct("Produto 2", "Descrição", "5678", 200.0, 120);
        }

        List<Branch> branches = branchRepository.findAll();

        if(branches.isEmpty()){
            this.createBranche("Filial 1");
            this.createBranche("Filial 2");
        }

        List<Client> clients = clientRepository.findAll();

        if(branches.isEmpty()){
            this.createClient("Cliente 1");
            this.createClient("Cliente 2");
        }

        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            this.createUser("Usuario 1", "00001");
            this.createUser("Usuario 2", "00002");
        }
    }

    private void createUser(String name, String registration) {
        User newUser = new User(name, registration);

        userRepository.save(newUser);
    }

    private void createClient(String name) {
        Client newClient = new Client(name);

        clientRepository.save(newClient);
    }

    private void createBranche(String name) {
        Branch newBranch = new Branch(name);

        branchRepository.save(newBranch);
    }

    public void createProduct(String name, String description, String barCode, Double price, Integer amount){

        Product newProduct = new Product(name, description, barCode, price);

        productService.save(newProduct);
        this.createStock(newProduct, amount);
    }

    public void createStock(Product product, Integer amount){

        Stock newStock = new Stock(product, amount);

        stockService.save(newStock);
    }

}

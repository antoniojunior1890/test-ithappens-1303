package com.devaj.happens.config;

import com.devaj.happens.model.*;
import com.devaj.happens.model.enums.TypeSolicitation;
import com.devaj.happens.repository.BranchRepository;
import com.devaj.happens.repository.ClientRepository;
import com.devaj.happens.repository.UserSystemRepository;
import com.devaj.happens.service.ItemService;
import com.devaj.happens.service.ProductService;
import com.devaj.happens.service.SolicitationService;
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
    private UserSystemRepository userSystemRepository;

    @Autowired
    private SolicitationService solicitationService;

    @Autowired
    private ItemService itemService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<Product> products = productService.listAll();

        if(products.isEmpty()){
            this.createProduct("Produto 1", "Descrição", "1234", 100.0);
            this.createProduct("Produto 2", "Descrição", "5678", 200.0);
        }

        List<Branch> branches = branchRepository.findAll();

        if(branches.isEmpty()){
            this.createBranche("Filial 1");
            this.createBranche("Filial 2");
        }


        List<Stock> stocks = stockService.listAll();

        if(stocks.isEmpty()){
            this.createStock(1L, 1L, 50);
            this.createStock(1L, 2L, 510);
            this.createStock(2L, 1L, 10);
            this.createStock(2L, 2L, 450);
        }

        List<Client> clients = clientRepository.findAll();

        if(branches.isEmpty()){
            this.createClient("Cliente 1");
            this.createClient("Cliente 2");
        }

        List<UserSystem> userSystems = userSystemRepository.findAll();

        if(userSystems.isEmpty()){
            this.createUser("Usuario 1", "00001");
            this.createUser("Usuario 2", "00002");
        }

        List<Solicitation> solicitations = solicitationService.listAll();

        if(solicitations.isEmpty()){
            this.createSolicitation(1L, 2L, 2L, "Local de Entrega", TypeSolicitation.ENTRADA);
            this.createSolicitation(1L, 2L, 2L, "Local de Entrega", TypeSolicitation.SAIDA);
        }

        List<Item> items = itemService.listAllByIdSolicitation(1L);

        if(items.isEmpty()){
            this.createItem(1L, 5, 19.2, 1L);
        }

    }

    private void createItem(Long stockId, Integer amount, double price, Long solicitationId) {
        Stock stock = new Stock();
        stock.setId(stockId);

        Item item = new Item(stock, amount, price);

        itemService.save(item, solicitationId);
    }

    private void createSolicitation(Long branchId, Long userId, Long clientId, String note, TypeSolicitation typeSolicitation) {
        Branch branch = new Branch();
        branch.setId(branchId);

        UserSystem user = new UserSystem();
        user.setId(userId);

        Client client = new Client();
        client.setId(clientId);

        Solicitation solicitation = new Solicitation(branch, user, client, note, typeSolicitation);
        solicitationService.save(solicitation);
    }

    private void createUser(String name, String registration) {
        UserSystem newUser = new UserSystem(name, registration);

        userSystemRepository.save(newUser);
    }

    private void createClient(String name) {
        Client newClient = new Client(name);

        clientRepository.save(newClient);
    }

    private void createBranche(String name) {
        Branch newBranch = new Branch(name);

        branchRepository.save(newBranch);
    }

    public void createProduct(String name, String description, String barCode, Double price){

        Product newProduct = new Product(name, description, barCode, price);

        productService.save(newProduct);
    }

    public void createStock(Long productId, Long branchId, Integer amount){
//        Product product = productService.getById(1);
        Product product = new Product();
        product.setId(productId);

        Branch branch = branchRepository.findById(branchId).get();
//        Branch branch = new Branch();
//        branch.setId(branchId);

        Stock newStock = new Stock();
        newStock.setAmount(amount);
        newStock.setBranch(branch);
        newStock.setProduct(product);

        stockService.save(newStock);
    }

}

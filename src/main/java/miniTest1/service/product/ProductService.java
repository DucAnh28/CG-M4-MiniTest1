package miniTest1.service.product;

import miniTest1.model.Product;
import miniTest1.repository.product.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class ProductService implements IProductService {
    @Autowired
    private IProductRepo productRepo;

    @Override
    public List<Product> findAll() {
       return productRepo.findAll();
    }

    @Override
    public Product findById(String id) {
        return productRepo.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepo.save(product);
    }

    @Override
    public void remove(String id) {
        productRepo.remove(id);
    }
}

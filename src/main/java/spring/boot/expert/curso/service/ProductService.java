package spring.boot.expert.curso.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.expert.curso.dto.ProductDTO;
import spring.boot.expert.curso.model.OrderItem;
import spring.boot.expert.curso.model.Product;
import spring.boot.expert.curso.repository.OrderItemRepository;
import spring.boot.expert.curso.repository.ProductRepository;
import spring.boot.expert.curso.service.exception.DatabaseException;
import spring.boot.expert.curso.service.exception.ExceptionBusinessRules;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        if (productRepository.existsByName(dto.getName())) {
            throw new DatabaseException("Product already registered");
        }
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product = productRepository.save(product);
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    @Transactional
    public ProductDTO update(Integer id, ProductDTO dto) {
        Product product = productRepository.findById(id).
            orElseThrow(() -> new ExceptionBusinessRules("Product not found, id does not exist: " + id));
        if (productRepository.existsByName(dto.getName())) {
                throw new DatabaseException("Product already registered");
        }
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product = productRepository.save(product);
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Integer id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ExceptionBusinessRules("Product not found, id does not exist: " + id));
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    @Transactional
    public void delete(Integer id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ExceptionBusinessRules("Product not found, id does not exist: " + id));
        
        List<OrderItem> list = orderItemRepository.findAll();
        List<Product> productsId = list.stream().map(x -> x.getProduct()).collect(Collectors.toList());
        
        if (productsId.contains(product)) {
            throw new DatabaseException("Product has orders and cannot be deleted");
        }
        productRepository.delete(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> list = productRepository.findAll(pageable);
        return list.map(x -> new ProductDTO(x.getId(), x.getName(), x.getDescription(), x.getPrice()));
    }
}

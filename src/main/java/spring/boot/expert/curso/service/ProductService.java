package spring.boot.expert.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.expert.curso.dto.ProductDTO;
import spring.boot.expert.curso.model.Product;
import spring.boot.expert.curso.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product = productRepository.save(product);
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    @Transactional
    public ProductDTO update(Integer id, ProductDTO dto) {
        Product product = productRepository.findById(id).get();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product = productRepository.save(product);
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Integer id) {
        Product product = productRepository.findById(id).get();
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> list = productRepository.findAll(pageable);
        return list.map(x -> new ProductDTO(x.getId(), x.getName(), x.getDescription(), x.getPrice()));
    }
}

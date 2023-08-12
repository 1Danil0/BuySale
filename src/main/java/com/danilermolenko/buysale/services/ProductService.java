package com.danilermolenko.buysale.services;

import com.danilermolenko.buysale.entities.Image;
import com.danilermolenko.buysale.entities.Product;
import com.danilermolenko.buysale.entities.User;
import com.danilermolenko.buysale.repositories.ProductRepository;
import com.danilermolenko.buysale.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Product> findAll(String title){
        if(title==null || title.isEmpty() || title.isBlank()){
            return productRepository.findAll();
        }
        return productRepository.findByTitle(title);
    }
    public Product findById(long id){
        return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("not fond"));
    }
    public void save(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        User user = getUserByPrincipal(principal);
        Image image1;
        Image image2;
        Image image3;
        if(file1.getSize() != 0){
            image1 = Image.toImage(file1);
            image1.setPreview(true);
            product.addImage(image1);
        }
        if(file2.getSize() != 0){
            image2 = Image.toImage(file2);
            product.addImage(image2);
        }
        if(file3.getSize() != 0){
            image3 = Image.toImage(file3);
            product.addImage(image3);
        }
        Product productFromDb = productRepository.save(product);
        product.setPreviewImage(productFromDb.getImages().stream().filter(image -> image.isPreview()==true)
                .findFirst().get().getId());
        user.addProduct(product);
        productRepository.save(product);
    }
    public void delete(long id){
        productRepository.deleteById(id);
    }

    public User getUserByPrincipal(Principal principal){
        if(principal == null){
            return new User();
        }
        return userRepository.findByEmail(principal.getName());
    }
}

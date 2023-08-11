package com.danilermolenko.buysale.controllers;

import com.danilermolenko.buysale.entities.Image;
import com.danilermolenko.buysale.entities.Product;
import com.danilermolenko.buysale.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/buysell")
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(@RequestParam(value = "title", required = false) String title, Model model){
        model.addAttribute("products", productService.findAll(title));
        return "products";
    }
    @PostMapping("/addProduct")
    public String add(@RequestParam("file1") MultipartFile file1,
                      @RequestParam("file2") MultipartFile file2,
                      @RequestParam("file3") MultipartFile file3,
                      Product product) throws IOException {
        productService.save(product, file1, file2, file3);
        return "redirect:products";
    }
    @GetMapping("/products/{id}")
    public String products(@PathVariable("id")long id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "product";
    }

//    @PostMapping("/products/delete/{id}")
//    public String delete(@PathVariable("id") long id){
//        productService.delete(id);
//        return "redirect:products";
//    }
    @PostMapping("/products/delete/{id}")
    public String delete(@PathVariable("id")long id){
        productService.delete(id);
        return "redirect:/buysell/products";
    }


}

package com.cybertek.controller;

import com.cybertek.entity.Product;
import com.cybertek.entity.ResponseWrapper;
import com.cybertek.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id){

        return ResponseEntity.ok(productService.getProduct(id));
//        return productService.getProduct(id);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        //adding custom header, way 1 - HttpHeaders
        HttpHeaders responseHttpHeaders = new HttpHeaders();
        responseHttpHeaders.set("Version", "Cybertek.v1.0.0.1");
        responseHttpHeaders.set("Operation", "Get List");
        //For ResponseEntity, need to pass 3 things: status, header, body
        //return type need to be changed ResponseEntity<List<Product>>
        return ResponseEntity.ok()//.ok == status 200
                .headers(responseHttpHeaders)
                .body(productService.getProducts());
    }

    //create Product - POST
    @PostMapping
    public ResponseEntity<List<Product>> createProduct(@RequestBody Product product){
        List<Product> set = productService.createProduct(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Version", "Cybertek.v1.0.0.1")
                .header("Operation", "Create ")
                .body(set);

    }
    //Delete Product -DELETE
    @DeleteMapping(value="/{id}")
    public ResponseEntity<List<Product>> deleteProduct(@PathVariable("id") long id){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Version", "Cybertek.v1.0.0.1");
        responseHeader.set("Operation", "Delete");

        List<Product> list = productService.delete(id);
        return new ResponseEntity<>(list, responseHeader, HttpStatus.OK);

    }

    //Update Product - @RequestBody - PUT
    @PutMapping(value="/{id}")
    public ResponseEntity<List<Product>> updateProduct(@PathVariable("id") long id, @RequestBody Product product){
        //adding custom header, way 1 - HttpHeaders
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>(); //header
        map.add("Version", "Cybertek.v1.0.0.1");
        map.add("Operation", "Update");

        List<Product> list = productService.updateProduct(id, product); //body

        return new ResponseEntity<>(list, map, HttpStatus.OK); //have to pass body, header, status codes


    }

    @GetMapping("/read")
    public ResponseEntity<ResponseWrapper> readAllProduct(){
        return ResponseEntity
                .ok(new ResponseWrapper("products successfully retrieved", productService.getProducts()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper> deleteProduct1(@PathVariable("id") long id){
        return ResponseEntity.ok(new ResponseWrapper("product successfully deleted !!!"));
    }
    @DeleteMapping("/delete2/{id}")
    public ResponseEntity<ResponseWrapper> deleteProduct2(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseWrapper("Product Successfully Deleted!!!"));
    }


}

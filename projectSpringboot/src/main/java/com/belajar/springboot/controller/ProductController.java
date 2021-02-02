package com.belajar.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.belajar.springboot.model.Product;
import com.belajar.springboot.model.Supplier;
import com.belajar.springboot.payload.ProductPayload;
import com.belajar.springboot.payload.ErrorResponse;
import com.belajar.springboot.repository.ProductRepo;
import com.belajar.springboot.repository.SupplierRepo;





@RestController
@RequestMapping(path = "/product")
//public class ProductController {
//
//	@GetMapping(path = "/product", produces = "application/json")
//	public String home() {
//		return "HELLO";
//	}
//	@RequestMapping(path = "/", method = RequestMethod.POST)
//	public String home() {
//		return "HELLO";
//	}
//	
//}

public class ProductController {
	
	@Autowired
	ProductRepo productRepo;
	@Autowired
	SupplierRepo supplierRepo;
	
	@GetMapping(path = "/getall", produces = "application/json")
	public ResponseEntity<?> getAll(){
		List <Product> products = productRepo.findAll();
		List<ProductPayload> response = new ArrayList<ProductPayload>();
		for(Product product : products) {
			response.add(new ProductPayload( 
					product.getName(), 
					product.getPrice(), 
					product.getQuantity(), 
					product.getReorderLevel(),
					product.getSupplier().getName())					
					);
		}
		return new ResponseEntity<List<ProductPayload>>(response, HttpStatus.OK);
		
		
	}
	@GetMapping(path = "/get/{id}", produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id){
		Product product = productRepo.findById(id).orElse(null);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
		
	}

	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createProduct(@RequestBody ProductPayload payload) {
		Supplier supplier = supplierRepo.findByNameIgnoreCase(payload.getSupplier());
		if(supplier == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Supplier is null",
					"Posisi yang anda masukkan tidak tersedia"),HttpStatus.BAD_REQUEST);					
		}
		Product existProduct = productRepo.findByNameIgnoreCase(payload.getName());
		if(existProduct != null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Already exist",
					"Ganti dengan nik lain"),HttpStatus.BAD_REQUEST);					
						
		}
		try {
			Product newProduct = new Product(
					payload.getName(), 
					payload.getPrice(), 
					payload.getQuantity(),
					payload.getReorderLevel(),
					supplier);
			productRepo.save(newProduct);
		} catch (Exception e){
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(					
					
					"Error",
					"Maaf request anda tidak dapat diproses"),HttpStatus.INTERNAL_SERVER_ERROR);					
				
		}
		return new ResponseEntity<ProductPayload>(payload, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createProduct(@PathVariable("id") Integer id, @RequestBody ProductPayload payload) {
		Supplier supplier = supplierRepo.findByNameIgnoreCase(payload.getSupplier());
		if(supplier == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Supplier is null",
					"Posisi yang anda masukkan tidak tersedia"),HttpStatus.BAD_REQUEST);					
		}
		Product existProduct = productRepo.findById(id).orElse(null);;
		if(existProduct == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Product not found",
					"update data failed"),HttpStatus.BAD_REQUEST);					
						
		}
		existProduct.setName(payload.getName());
		existProduct.setPrice(payload.getPrice());
		existProduct.setQuantity(payload.getQuantity());
		existProduct.setReorderLevel(payload.getReorderLevel());
		existProduct.setSupplier(supplier);
		productRepo.save(existProduct);
		
		return new ResponseEntity<ProductPayload>(payload, HttpStatus.CREATED);
	}
	@DeleteMapping(path = "/delete/{id}", produces="application/json")
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
		productRepo.deleteById(id);
		return new ResponseEntity<String>("success deleted data", HttpStatus.OK);
	}
}




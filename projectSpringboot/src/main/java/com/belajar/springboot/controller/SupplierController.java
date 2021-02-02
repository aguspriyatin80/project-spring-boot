package com.belajar.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.belajar.springboot.model.Supplier;
import com.belajar.springboot.payload.SupplierPayload;
import com.belajar.springboot.payload.ErrorResponse;
import com.belajar.springboot.repository.SupplierRepo;

@RestController
@RequestMapping(path = "/supplier")
public class SupplierController {
	@Autowired
	SupplierRepo supplierRepo;
	@GetMapping(path = "/getall", produces = "application/json")
	public ResponseEntity<?> getAll(){
		List <Supplier> suppliers = supplierRepo.findAll();
		return new ResponseEntity<List<Supplier>>(suppliers, HttpStatus.OK);
		
	}
	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createSupplier(@RequestBody SupplierPayload payload) {
		Supplier existSupplier = supplierRepo.findByNameIgnoreCase(payload.getName());
		if(existSupplier != null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Already exist",
					"Ganti dengan nama lain"),HttpStatus.BAD_REQUEST);					
						
		}
		try {
			Supplier newSupplier = new Supplier(
					payload.getName(), 
					payload.getAddress(), 
					payload.getContact());
			supplierRepo.save(newSupplier);
		} catch (Exception e){
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(					
					
					"Error",
					"Maaf request anda tidak dapat diproses"),HttpStatus.INTERNAL_SERVER_ERROR);					
				
		}
		return new ResponseEntity<SupplierPayload>(payload, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createSupplier(@PathVariable("id") Integer id, @RequestBody SupplierPayload payload) {
		Supplier existSupplier = supplierRepo.findById(id).orElse(null);;
		if(existSupplier == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Supplier not found",
					"update data failed"),HttpStatus.BAD_REQUEST);					
						
		}
		existSupplier.setName(payload.getName());
		existSupplier.setAddress(payload.getAddress());
		existSupplier.setContact(payload.getContact());
		supplierRepo.save(existSupplier);
		
		return new ResponseEntity<SupplierPayload>(payload, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/delete/{id}", produces="application/json")
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
		supplierRepo.deleteById(id);
		return new ResponseEntity<String>("success deleted data", HttpStatus.OK);
	}
}

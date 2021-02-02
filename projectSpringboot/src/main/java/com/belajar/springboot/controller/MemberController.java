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

import com.belajar.springboot.model.Member;
import com.belajar.springboot.payload.ErrorResponse;
import com.belajar.springboot.payload.MemberPayload;
import com.belajar.springboot.repository.MemberRepo;

@RestController
@RequestMapping(path = "/member")
public class MemberController {
	@Autowired
	MemberRepo memberRepo;
	@GetMapping(path = "/getall", produces = "application/json")
	public ResponseEntity<?> getAll(){
		List <Member> members = memberRepo.findAll();
		return new ResponseEntity<List<Member>>(members, HttpStatus.OK);		
	}
	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createMember(@RequestBody MemberPayload payload) {
		Member existMember = memberRepo.findByFirstNameIgnoreCase(payload.getFirstName());
		if(existMember != null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Already exist",
					"Ganti dengan nama lain"),HttpStatus.BAD_REQUEST);					
						
		}
		try {
			Member newMember = new Member(
					payload.getFirstName(),
					payload.getSurname(),
					payload.getAddress(), 
					payload.getContact(),
					payload.getExpires()
					);
			memberRepo.save(newMember);
		} catch (Exception e){
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(					
					
					"Error",
					"Maaf request anda tidak dapat diproses"),HttpStatus.INTERNAL_SERVER_ERROR);					
				
		}
		return new ResponseEntity<MemberPayload>(payload, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createMember(@PathVariable("id") Integer id, @RequestBody MemberPayload payload) {
		Member existMember = memberRepo.findById(id).orElse(null);;
		if(existMember == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Member not found",
					"update data failed"),HttpStatus.BAD_REQUEST);					
						
		}
		existMember.setFirstName(payload.getFirstName());
		existMember.setSurname(payload.getAddress());
		existMember.setAddress(payload.getAddress());
		existMember.setContact(payload.getContact());
		existMember.setExpires(payload.getExpires());
		memberRepo.save(existMember);
		
		return new ResponseEntity<MemberPayload>(payload, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/delete/{id}", produces="application/json")
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
		memberRepo.deleteById(id);
		return new ResponseEntity<String>("success deleted data", HttpStatus.OK);
	}
}

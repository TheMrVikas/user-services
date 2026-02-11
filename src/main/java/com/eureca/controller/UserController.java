package com.eureca.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureca.dto.UserRequestDTO;
import com.eureca.dto.UserResponseDTO;
import com.eureca.service.UserService;

import lombok.AllArgsConstructor;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It will use the UserService to perform business logic and return appropriate responses.
 */


@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {

	private final UserService userService;
	
	/**
	 * Endpoint to create a new user. It accepts a UserRequestDTO in the request body and returns a UserResponseDTO.
	 * @param userRequestDTO
	 * @return
	 */
	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
		UserResponseDTO response = userService.createUser(userRequestDTO);
		return Objects.nonNull(response) ? ResponseEntity.ok(response) : ResponseEntity.status(500).build();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") Long id) {
		UserResponseDTO response = userService.getUserById(id);
		return Objects.nonNull(response) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
	}
	
	/**
	 * 
	 * @param id
	 * @param userRequestDTO
	 * @return
	 */
	@PutMapping(value = "/{id}/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserRequestDTO userRequestDTO) {
		UserResponseDTO response = userService.updateUser(id, userRequestDTO);
		return Objects.nonNull(response) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}/delete")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
		List<UserResponseDTO> allUsers = userService.getAllUsers();
		return Objects.nonNull(allUsers) ? ResponseEntity.ok(allUsers) : ResponseEntity.notFound().build();
	}
}

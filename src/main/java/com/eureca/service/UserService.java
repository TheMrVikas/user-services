package com.eureca.service;

import java.util.List;

import com.eureca.dto.UserRequestDTO;
import com.eureca.dto.UserResponseDTO;

public interface UserService {

	// Define service methods here, e.g., createUser, getUserById, updateUser, deleteUser, etc.
	public UserResponseDTO createUser(UserRequestDTO userRequestDTO);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public UserResponseDTO getUserById(Long id);
	
	/**
	 * 
	 * @param id
	 * @param userRequestDTO
	 * @return
	 */
	public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
	
	/**
	 * 
	 * @param id
	 */
	public void deleteUser(Long id);
	
	/**
	 * 
	 * @return
	 */
	public List<UserResponseDTO> getAllUsers();
}

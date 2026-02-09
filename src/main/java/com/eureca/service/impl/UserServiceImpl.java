package com.eureca.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.eureca.dto.UserRequestDTO;
import com.eureca.dto.UserResponseDTO;
import com.eureca.entity.UserEntity;
import com.eureca.exception.UserServiceException;
import com.eureca.repository.UserRepository;
import com.eureca.service.UserService;

import lombok.AllArgsConstructor;

/**
 * UserServiceImpl is the implementation of the UserService interface. It
 * provides the actual logic for handling user-related operations such as
 * creating, retrieving, updating, and deleting users. This class is annotated
 * with @Service to indicate that it is a service component in the Spring
 * framework.
 */

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    /**
     * Creates a new user based on the provided UserRequestDTO and returns a
     * UserResponseDTO.
     */
	@Override
	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
		//1. Map UserRequestDTO to UserEntity
		UserEntity entity = modelMapper.map(userRequestDTO, UserEntity.class);
		
		//2. handle isActive string -> boolean if DTO uses String
		if (userRequestDTO.getIsActive() != null) {
			entity.setActive(Boolean.parseBoolean(userRequestDTO.getIsActive()));
		}
		
		UserEntity saved = userRepository.save(entity);
		
		//3. Map saved UserEntity to UserResponseDTO
		UserResponseDTO response = modelMapper.map(saved, UserResponseDTO.class);
		
		//4. ensure response IsActive is string representation
		response.setIsActive(Boolean.toString(saved.isActive()));
		return response;
	}

	
    /**
     * Retrieves a user by their unique identifier (id) and returns a
     * UserResponseDTO.
     */
    @Override
    public UserResponseDTO getUserById(Long id) {
    	//1. Retrieve UserEntity by id, throw exception if not found
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new UserServiceException("User not found with id: " + id));
        
        //2. Map UserEntity to UserResponseDTO
        UserResponseDTO response = modelMapper.map(entity, UserResponseDTO.class);
        
        //3. ensure response IsActive is string representation
        response.setIsActive(Boolean.toString(entity.isActive()));
        return response;
    }

    
    /**
     * Updates an existing user identified by their unique identifier (id) using the
     * provided UserRequestDTO and returns a UserResponseDTO.
     */
    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
    	//1. Retrieve existing UserEntity by id, throw exception if not found
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new UserServiceException("User not found with id: " + id));

        //2. Map incoming DTO onto existing entity to update fields
        modelMapper.map(userRequestDTO, entity);
        if (userRequestDTO.getIsActive() != null) {
            entity.setActive(Boolean.parseBoolean(userRequestDTO.getIsActive()));
        }

        //3. Save updated entity and map to response DTO
        UserEntity updated = userRepository.save(entity);
        
        //4. Map updated UserEntity to UserResponseDTO
        UserResponseDTO response = modelMapper.map(updated, UserResponseDTO.class);
        
        //5. ensure response IsActive is string representation
        response.setIsActive(Boolean.toString(updated.isActive()));
        return response;
    }

    /**
     * Deletes a user identified by their unique identifier (id).
     */
    @Override
    public void deleteUser(Long id) {
    	//1. Check if user exists before attempting to delete, throw exception if not found
        if (!userRepository.existsById(id)) {
            throw new UserServiceException("User not found with id: " + id);
        }
        
        //2. Delete user by id
        userRepository.deleteById(id);
    }

    /**
     * Retrieves a list of all users and returns it as a List of UserResponseDTO.
     */
    @Override
    public List<UserResponseDTO> getAllUsers() {
    	//1. Retrieve all UserEntity records from the database
        List<UserEntity> users = userRepository.findAll();
        
        //2. Map each UserEntity to UserResponseDTO and ensure IsActive is string representation
        return users.stream().map(u -> {
            UserResponseDTO r = modelMapper.map(u, UserResponseDTO.class);
            r.setIsActive(Boolean.toString(u.isActive()));
            return r;
        }).collect(Collectors.toList());
    }
}
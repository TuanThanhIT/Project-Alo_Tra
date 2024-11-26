package vn.iotstar.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.User;
import vn.iotstar.repository.UserRepository;
import vn.iotstar.services.IUserService;


@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Override
	public User login(String username, String password) {
	    Optional<User> userOptional = userRepository.findByUserName(username);
	    
	    if (userOptional.isPresent() && password.equals(userOptional.get().getPassword())) {
	        return userOptional.get();
	    }
	    
	    return null;
	}


	@Override
	public User addUser(User user) {
	    return userRepository.save(user); // Thêm mới
	}


	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}


	@Override
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}


	@Override
	public boolean checkUserNameExists(String username) {
		return userRepository.existsByUserName(username);
	}


	@Override
	public boolean register(String fullName, String address, String phone, String email, String userName,
			String password, String image, int roleID) 
	{
		if(checkUserNameExists(userName))
		{
			return false;
		}
		// Lấy ngày hiện tại
		Date currentDate = new Date(System.currentTimeMillis());
		// Tạo đối tượng User với ngày hiện tại
		userRepository.save(new User(fullName, currentDate, address, phone, email, userName, password, true, image, roleID));
		return true;
	}
	
	
	
	/*
	public User updateUser(int id, User newUserDetails) {
	    // Tìm đối tượng theo ID
	    Optional<User> optionalUser = userRepository.findById(id);
	    if (optionalUser.isPresent()) {
	        User existingUser = optionalUser.get();
	        
	        // Cập nhật các trường khác nếu cần
	        return userRepository.save(existingUser); // Cập nhật
	    } else {
	        throw new EntityNotFoundException("User not found with ID: " + id);
	    }
	    */
	

	
}
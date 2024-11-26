package vn.iotstar.services;

import java.util.List;
import java.util.Optional;

import vn.iotstar.entity.User;

public interface IUserService {

	Optional<User> findById(Integer id);

	List<User> findAll();

	User addUser(User user);

	User login(String username, String password);
	
	boolean checkUserNameExists(String username);
	
	boolean register(String fullName, String address, String phone, String email, String userName, String password, String image, int roleID);

}

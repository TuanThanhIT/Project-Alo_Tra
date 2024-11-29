package vn.iotstar.services;

import java.util.List;
import java.util.Optional;

import vn.iotstar.entity.User;

public interface IUserService {

	Optional<User> findById(Integer id);

	List<User> findAll();

	User addUser(User user);

	User login(String username, String password);

	boolean existsByUserName(String username);

	User getUserByUsername(String username);

	<S extends User> S save(S entity);

	
	
	
	

}

package vn.iotstar.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.User;
import vn.iotstar.repository.UserRepository;
import vn.iotstar.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	public List<User> findAll(Sort sort) {
		return userRepository.findAll(sort);
	}

	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public long count() {
		return userRepository.count();
	}

	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	public void delete(User entity) {
		userRepository.delete(entity);
	}

	public void deleteAll() {
		userRepository.deleteAll();
	}

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
	public User getUserByUsername(String username) {
		return userRepository.findByUserName(username).orElse(null); // Trả về null nếu không tìm thấy người dùng
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
	public boolean existsByUserName(String username) {
		return userRepository.existsByUserName(username);
	}

	@Override
	public <S extends User> S save(S entity) {
		return userRepository.save(entity);
	}

	@Override
	public long countByRole(String role) {
		return userRepository.countByRole(role);
	}

	@Override
	public long countShipperRole() {
		return userRepository.countShipperRole();
	}

	@Override
	public long countSellerRole() {
		return userRepository.countSellerRole();
	}

	@Override
	public List<User> findByRoleID(int roleID) {
		return userRepository.findByRoleID(roleID);
	}

}

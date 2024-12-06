package vn.iotstar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.User;
import vn.iotstar.models.UserDto;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUserName(String userName);
    boolean existsByUserName(String username);
    boolean existsByEmail(String email);
    User findByEmail(String email);
}

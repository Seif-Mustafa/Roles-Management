package RolesManagement.service.serviceImpl;

import RolesManagement.dto.request.CreateUserRequest;
import RolesManagement.dto.request.UpdateUserRequest;
import RolesManagement.mapper.UserMapper;
import RolesManagement.model.AppUser;
import RolesManagement.repository.UserRepository;
import RolesManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public AppUser createUser(CreateUserRequest createUserRequest) {
        AppUser createdUser = userRepository.save(userMapper.toEntity(createUserRequest));
        return createdUser;
    }

    @Override
    public AppUser getUserById(Long userId) {
        Optional<AppUser> user = userRepository.findById(userId);
        return user.isPresent() ? user.get() : null;
    }


    @Override
    public AppUser updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        AppUser updatedUser = userRepository.save(userMapper.toEntity(userId, updateUserRequest));
        return updatedUser;
    }

    @Override
    public AppUser deleteUser(Long userId) {
        Optional<AppUser> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return user.get();
        }
        return null;
    }

    public List<AppUser> getAllUsers(){
        return userRepository.findAll();
    }

}

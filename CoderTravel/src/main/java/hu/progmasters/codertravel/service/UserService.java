package hu.progmasters.codertravel.service;

import hu.progmasters.codertravel.domain.User;
import hu.progmasters.codertravel.dto.UserCreateCommand;
import hu.progmasters.codertravel.dto.UserInfo;
import hu.progmasters.codertravel.exceptionhandling.UserNotFoundException;
import hu.progmasters.codertravel.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private ModelMapper mapper;
    private UserRepository userRepository;

    public UserService(ModelMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public UserInfo saveUser(UserCreateCommand createCommand) {
        User toSave = mapper.map(createCommand, User.class);
        User saved = userRepository.save(toSave);
        return mapper.map(saved, UserInfo.class);
    }

    public UserInfo findUserById(Integer id) {
        Optional<User> searchedUser = userRepository.findById(id);
        if (searchedUser.isEmpty()) {
            throw new UserNotFoundException(id);
        } else {
            return mapper.map(searchedUser.get(), UserInfo.class);
        }
    }

    public UserInfo updateUser(UserCreateCommand createCommand) {
        User toUpdate = mapper.map(createCommand, User.class);

        if (!userRepository.existsById(toUpdate.getId())) {
            throw new UserNotFoundException(toUpdate.getId());
        } else {
            User updated = userRepository.save(toUpdate);
            return mapper.map(updated, UserInfo.class);
        }
    }
}

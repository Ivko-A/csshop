package softuni.csshop.service;

import softuni.csshop.model.UserEntity;
import softuni.csshop.model.view.UserViewModel;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean exists(String email);

    UserEntity createUser(String userEmail, String userPassword);

    void registerAndLoginUser(String userEmail, String userPassword);


    List<UserViewModel> findAllUsers();

    UserViewModel findById(String id);

    void delete(String id);

    void makeAdmin(String id);

    void removeAdmin(String id);
}

package softuni.csshop.service;

import softuni.csshop.model.UserEntity;

public interface UserService {

    boolean exists(String email);

    UserEntity createUser(String userEmail, String userPassword);

    void registerAndLoginUser(String userEmail, String userPassword);


}

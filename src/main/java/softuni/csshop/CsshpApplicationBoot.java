package softuni.csshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import softuni.csshop.model.RoleEntity;
import softuni.csshop.model.UserEntity;
import softuni.csshop.repository.UserEntityRepository;

import java.util.List;

@Component
public class CsshpApplicationBoot implements CommandLineRunner {
    private final UserEntityRepository userEntityRepository;

    public CsshpApplicationBoot(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        createUsers();

    }






    private void createUsers() {


        if (userEntityRepository.count() == 0) {
        // user - admin
        UserEntity adminUser = new UserEntity();
        adminUser.setEmail("pesho@abv.bg");
        adminUser.setPassword(new BCryptPasswordEncoder().encode("123"));

        RoleEntity adminRoleAdminUser = new RoleEntity();
        adminRoleAdminUser.setRole("ROLE_ADMIN");

        RoleEntity roleUserAminUser = new RoleEntity();
        roleUserAminUser.setRole("ROLE_USER");

        adminUser.setRoles(List.of(adminRoleAdminUser, roleUserAminUser));

        userEntityRepository.save(adminUser);

//        // normal user
        UserEntity normalUser = new UserEntity();
        normalUser.setEmail("ivan@abv.bg");
        normalUser.setPassword(new BCryptPasswordEncoder().encode("123"));

        RoleEntity roleUserNormalUser = new RoleEntity();
        roleUserNormalUser.setRole("ROLE_USER");

        normalUser.setRoles(List.of(roleUserNormalUser));

        userEntityRepository.save(normalUser);
      }


    }



}

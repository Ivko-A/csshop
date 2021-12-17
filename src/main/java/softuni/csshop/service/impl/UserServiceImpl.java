package softuni.csshop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.csshop.model.RoleEntity;
import softuni.csshop.model.UserEntity;
import softuni.csshop.model.view.UserViewModel;
import softuni.csshop.repository.UserEntityRepository;
import softuni.csshop.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, ModelMapper modelMapper) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean exists(String email) {
        Optional<UserEntity> userEntityOpt =
                userEntityRepository.findByEmail(email);

        return userEntityOpt.isPresent();
    }

    @Override
    public UserEntity createUser(String userEmail, String userPassword) {

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userEmail);
        userEntity.setPassword(passwordEncoder.encode(userPassword));

        RoleEntity userRole = new RoleEntity().setRole("ROLE_USER");
        userEntity.setRoles(List.of(userRole));

        return userEntityRepository.save(userEntity);
    }

    @Override
    public void registerAndLoginUser(String userEmail, String userPassword) {
        UserEntity userEntity = createUser(userEmail, userPassword);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getEmail());

        Authentication authentication = new
                UsernamePasswordAuthenticationToken(
                userDetails,
                userEntity.getPassword(),
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Override
    public List<UserViewModel> findAllUsers() {
        return this.userEntityRepository
                .findAll()
                .stream()
                .map(userEntity -> {

                    return this.modelMapper
                            .map(userEntity, UserViewModel.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserViewModel findById(String id) {

        return this.userEntityRepository
                .findById(id)
                .map(userEntity -> {
                    UserViewModel userViewModel = this.modelMapper
                            .map(userEntity, UserViewModel.class);

                    return userViewModel;
                })
                .orElse(null);
    }

    @Override
    public void delete(String id) {
        this.userEntityRepository.deleteById(id);

    }

    @Override
    public void makeAdmin(String id) {

        RoleEntity adminRole = new RoleEntity().setRole("ROLE_ADMIN");

        Optional<UserEntity> optional = this.userEntityRepository.findById(id);

        if (optional.isPresent()) {
            UserEntity userEntity = optional.get();
            userEntity.getRoles().add(adminRole);
            this.userEntityRepository.save(userEntity);

        }


    }


}

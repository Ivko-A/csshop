package softuni.csshop.service.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.csshop.model.RoleEntity;
import softuni.csshop.model.UserEntity;
import softuni.csshop.repository.UserEntityRepository;
import softuni.csshop.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public UserServiceImpl(UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
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
}

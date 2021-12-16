package softuni.csshop.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import softuni.csshop.model.UserEntity;
import softuni.csshop.repository.UserEntityRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

  private final UserEntityRepository userEntityRepository;

  public UserDetailsServiceImpl(UserEntityRepository userEntityRepository) {
    this.userEntityRepository = userEntityRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Optional<UserEntity> userOpt = userEntityRepository.findByEmail(email);

    LOGGER.debug("Trying to load user {}. Success = {}.", email, userOpt.isPresent());

    return userOpt.map(this::map).orElseThrow(
        () -> new UsernameNotFoundException("No user " + email));
  }

  private User map(UserEntity user) {

    List<GrantedAuthority> authorities = user.
        getRoles().
        stream().
        map(r -> new SimpleGrantedAuthority(r.getRole())).
        collect(Collectors.toList());

    User result = new User(user.getEmail(),
        user.getPassword(), authorities);



    return result;
  }
}

package github.tourism.service.user.security;


import github.tourism.data.entity.user.User;
import github.tourism.data.repository.user.UserRepository;
import github.tourism.web.dto.user.sign.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Primary
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userPrincipal = userRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException("email 에 해당하는 UserPrincipal가 없습니다"));

        Set<Authority> roles = userPrincipal.getAuthorities();

        return CustomUserDetails.builder()
                .userId(userPrincipal
                        .getUserId())
                .email(userPrincipal.getEmail())
                .password(userPrincipal.getPassword())
                .userName(userPrincipal.getUserName())
                .authorities(roles)
                .build();
    }
}

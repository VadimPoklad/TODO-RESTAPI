package com.petproject.todo.service.serv;


import com.petproject.todo.exception.EntityIllegalArgumentsException;
import com.petproject.todo.repository.entity.User;
import com.petproject.todo.repository.entity.User$;
import com.petproject.todo.repository.repo.RoleRepository;
import com.petproject.todo.repository.repo.UserRepository;
import com.petproject.todo.service.dto.request.UserDtoRequest;
import com.petproject.todo.service.dto.response.UserDtoResponse;
import com.petproject.todo.service.mapper.UserMapper;
import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.streamconfiguration.StreamConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService{
    private final UserRepository userRepository;
    private final JPAStreamer jpaStreamer;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;

    @Autowired
    public UserService(JPAStreamer jpaStreamer, UserRepository userRepository, RoleRepository roleRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.jpaStreamer = jpaStreamer;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    public List<UserDtoResponse> getALl() {
        List<User> list = jpaStreamer.stream(
                StreamConfiguration.of(User.class)
                        .joining(User$.authorities)).toList();
        return mapper.listToDtoList(list);
    }

    public UserDtoResponse getById(Long id) {
        return mapper.toDto(userRepository.findById(id).orElseThrow());
    }

    public UserDtoResponse create(UserDtoRequest dto) {
        try {
            User user = mapper.toEntity(dto);
            user.setAuthorities(Set.of(roleRepository.findByName("USER")));
            return mapper.toDto(userRepository.save(user));
        } catch (Exception e) {
            throw new EntityIllegalArgumentsException(e);
        }
    }

    public UserDtoResponse update(Long id, UserDtoRequest dto) {
        try {
            User user = userRepository.findById(id).orElseThrow();

            user.setPassword(dto.getPassword());
            user.setUsername(dto.getUsername());
            return mapper.toDto(userRepository.save(user));
        } catch (Exception e) {
            throw new EntityIllegalArgumentsException(e);
        }

    }

    public void deleteById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
        throw new RuntimeException();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return SingleResult.getSingleResult(jpaStreamer.stream(
                        StreamConfiguration.of(User.class)
                        .joining(User$.authorities))
                .filter(user -> user.getUsername().equals(username)));
    }
}

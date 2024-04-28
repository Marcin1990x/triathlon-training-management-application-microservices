package pl.koneckimarcin.usersservice.user.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.usersservice.exception.ResourceNotFoundException;
import pl.koneckimarcin.usersservice.user.UserEntity;
import pl.koneckimarcin.usersservice.user.repository.UserRepository;

@Service
public class RoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void addRoleToUserById(Long id, Role role) {

        if (!userRepository.findById(id).isPresent())
            throw new ResourceNotFoundException("User", "id", String.valueOf(id));

        UserEntity user = userRepository.findById(id).get();

        RoleEntity roleToAdd = roleRepository.findByRole(role);

        roleToAdd.getUsers().add(user);
        user.getRoles().add(roleToAdd);

        roleRepository.save(roleToAdd);
    }
}

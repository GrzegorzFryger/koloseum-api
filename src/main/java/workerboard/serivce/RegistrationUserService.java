package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import workerboard.exception.ApplicationNotFound;
import workerboard.model.ApplicationUser;
import workerboard.model.Role;
import workerboard.model.enums.UserRole;
import workerboard.repository.ApplicationUserRepository;

import java.util.*;

@Service
public class RegistrationUserService {


    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationUserService(ApplicationUserRepository applicationUserRepository,
                                   BCryptPasswordEncoder passwordEncoder) {

        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public ApplicationUser registrationNewUser(ApplicationUser newApplicationUser) throws ApplicationNotFound {

        if (applicationUserRepository
                .findByEmail(newApplicationUser.getEmail())
                .isPresent()) {
            throw new ApplicationNotFound("User with email: " + newApplicationUser.getEmail() + " exist");
        }

        newApplicationUser.setPassword(passwordEncoder.encode(newApplicationUser.getPassword()));

        newApplicationUser.setRoles(Arrays.asList(new Role(UserRole.UNSPECIFED)));

        return applicationUserRepository.save(newApplicationUser);
    }
}

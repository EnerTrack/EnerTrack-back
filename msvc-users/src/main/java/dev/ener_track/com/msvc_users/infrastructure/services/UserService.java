package dev.ener_track.com.msvc_users.infrastructure.services;

import dev.ener_track.com.msvc_users.api.dto.request.UserRequest;
import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.VerifyEmail;
import dev.ener_track.com.msvc_users.api.dto.response.basicResponse.VerifyLogin;
import dev.ener_track.com.msvc_users.api.dto.response.relationsResponse.UserRelationResponse;
import dev.ener_track.com.msvc_users.domain.entities.PersonEntity;
import dev.ener_track.com.msvc_users.domain.entities.UserEntity;
import dev.ener_track.com.msvc_users.domain.repositories.PersonRepository;
import dev.ener_track.com.msvc_users.domain.repositories.UserRepository;
import dev.ener_track.com.msvc_users.infrastructure.adstract_service.IUserService;
import dev.ener_track.com.msvc_users.infrastructure.mappers.UserMapper;
import dev.ener_track.com.msvc_users.utils.emuns.SortType;
import dev.ener_track.com.msvc_users.utils.exeptions.ErrorMessages;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final UserMapper userPersonMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserRelationResponse> getAll(int page, int size, SortType sortType) {

        if (page < 0)
            page = 0;
        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        System.out.println("###############################################" +userRepository.findAll());
        return this.userRepository.findAll(pagination).map(userPersonMapper::toResponse);
    }

    @Override
    public UserRelationResponse create(UserRequest request) throws BadRequestException {

        Optional<PersonEntity> PersonExisting = this.personRepository.findById(request.getPersonId());

        if(PersonExisting.isEmpty()) throw new BadRequestException(ErrorMessages.IdNotFound("Person"));

        UserEntity newUser = userPersonMapper.toEntity(request);
        newUser.setPerson(PersonExisting.get());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        UserEntity savedUser = userRepository.save(newUser);

        return userPersonMapper.toResponse(savedUser);
    }

    @Override
    public VerifyLogin verifyEmailAuth(VerifyEmail request) throws BadRequestException {

        PersonEntity person = personRepository.findByEmailWithUser(request.getEmail())
                .orElseThrow(() -> new BadRequestException(
                        ErrorMessages.emailNotFound(request.getEmail())
                ));

        System.out.println(person);
        if (person.getUser() == null)
            throw new BadRequestException(ErrorMessages.userNotFound(request.getEmail()));

        return VerifyLogin.builder()
                .name(person.getName())
                .email(person.getEmail())
                .password(person.getUser().getPassword())
                .build();
    }


    @Override
    public UserRelationResponse update(String id, UserRequest userRequest) throws BadRequestException {

        UserEntity user = this.find(id);

        this.userPersonMapper.updateUser(userRequest, user);
        UserEntity savedUser = userRepository.save(user);

        return userPersonMapper.toResponse(savedUser);
    }

    private UserEntity find(String id) throws BadRequestException {
        return this.userRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.IdNotFound("User")));
    }

}

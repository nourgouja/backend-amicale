package tn.star.Pfe.mapper;

import jakarta.persistence.DiscriminatorValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.star.Pfe.dto.auth.UserResponse;
import tn.star.Pfe.entity.User;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", expression = "java(extraireRole(u))")
    UserResponse toResponse(User u);

    List<UserResponse> toResponseList(List<User> liste);

    default String extraireRole(User u) {
        DiscriminatorValue dv = u.getClass().getAnnotation(DiscriminatorValue.class);
        return dv != null ? dv.value() : "INCONNU";
    }
}
package tn.star.Pfe.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tn.star.Pfe.dto.auth.UserResponse;
import tn.star.Pfe.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-26T14:44:30+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toResponse(User u) {
        if ( u == null ) {
            return null;
        }

        int id = 0;
        String email = null;
        String nom = null;
        String prenom = null;
        boolean actif = false;
        LocalDateTime createdAt = null;

        id = u.getId();
        email = u.getEmail();
        nom = u.getNom();
        prenom = u.getPrenom();
        actif = u.isActif();
        createdAt = u.getCreatedAt();

        String role = extraireRole(u);

        UserResponse userResponse = new UserResponse( id, email, nom, prenom, role, actif, createdAt );

        return userResponse;
    }

    @Override
    public List<UserResponse> toResponseList(List<User> liste) {
        if ( liste == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( liste.size() );
        for ( User user : liste ) {
            list.add( toResponse( user ) );
        }

        return list;
    }
}

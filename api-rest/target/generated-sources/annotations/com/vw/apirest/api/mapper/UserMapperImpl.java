package com.vw.apirest.api.mapper;

import com.vw.apirest.api.dto.UserRequest;
import com.vw.apirest.api.dto.UserResponse;
import com.vw.domain.aggregate.User;
import com.vw.domain.entity.Consent;
import com.vw.user.create.CreateUserRequest;
import java.util.ArrayList;
import java.util.List;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-10T00:34:39+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
*/
public class UserMapperImpl implements UserMapper {

    @Override
    public CreateUserRequest asCreateUserRequest(UserRequest dto) {
        if ( dto == null ) {
            return null;
        }

        CreateUserRequest.CreateUserRequestBuilder createUserRequest = CreateUserRequest.builder();

        createUserRequest.email( dto.getEmail() );
        createUserRequest.consents( asConsent( dto.getConsents() ) );

        return createUserRequest.build();
    }

    @Override
    public List<Consent> asConsent(List<com.vw.apirest.api.dto.Consent> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Consent> list = new ArrayList<Consent>( dto.size() );
        for ( com.vw.apirest.api.dto.Consent consent : dto ) {
            list.add( consentToConsent( consent ) );
        }

        return list;
    }

    @Override
    public UserResponse asUserResponse(User dto) {
        if ( dto == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        return userResponse;
    }

    protected Consent consentToConsent(com.vw.apirest.api.dto.Consent consent) {
        if ( consent == null ) {
            return null;
        }

        Consent consent1 = new Consent();

        consent1.setId( consent.getId() );
        if ( consent.getEnabled() != null ) {
            consent1.setEnabled( consent.getEnabled() );
        }

        return consent1;
    }
}

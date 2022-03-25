/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.system.auth;

import com.mycompany.library.system.entity.User;
import com.mycompany.library.system.repository.UserRepository;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 *
 * @author swiat
 */
@ApplicationScoped
public class UserIdentityStore implements IdentityStore {

    @Inject
    UserRepository userRepository;

    @Override
    public Set<ValidationType> validationTypes() {
        return EnumSet.of(ValidationType.VALIDATE);
    }

    @Override
    public int priority() {
        return 70;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {
        return validateUsernameAndPassword((UsernamePasswordCredential) credential);
    }

    public CredentialValidationResult validateUsernameAndPassword(UsernamePasswordCredential credential) {
        User user = userRepository.getUserByUsername(credential.getCaller());
        if (user == null) {
            return INVALID_RESULT;
        }
        if (isPasswordCorrect(credential.getPasswordAsString(), user)) {
            return buildValidCredentialValidationResult(user);
        }
        return INVALID_RESULT;
    }

    private CredentialValidationResult buildValidCredentialValidationResult(User user) {
        return new CredentialValidationResult(user.getUsername(), new HashSet<>(Arrays.asList(user.getRole())));
    }

    private boolean isPasswordCorrect(String password, User user) {
        return user.getPassword().equals(password);
    }

}

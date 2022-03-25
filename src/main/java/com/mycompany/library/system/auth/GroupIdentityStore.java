/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.system.auth;

import java.util.EnumSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 *
 * @author swiat
 */
@ApplicationScoped
public class GroupIdentityStore implements IdentityStore {

    @Override
    public Set<ValidationType> validationTypes() {
        return EnumSet.of(ValidationType.PROVIDE_GROUPS);
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int priority() {
        return 90; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        if (validationResult.getStatus().equals(INVALID_RESULT.getStatus())) {
            return null;
        }
        return validationResult.getCallerGroups();
    }

}

package com.mycompany.library.system.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author swiat
// */

@ManagedBean
@RequestScoped
public class LoginBean {

    @Inject
    private SecurityContext securityContext;

    String username;
    String password;

    String redirection;

    public void authenticate(ActionEvent ev) {
        Credential credential = new UsernamePasswordCredential(
                username, new Password(password));
        FacesContext facesContext = extractContext();
        AuthenticationStatus status = tryAutneticateAndGetStatus(credential, facesContext);
        chooseRedirection(status);
    }

    private void chooseRedirection(AuthenticationStatus status) {
        if (status.equals(SUCCESS)) {
            redirection = "home";
        } else {
            redirection = "error";
        }
    }

    private AuthenticationStatus tryAutneticateAndGetStatus(Credential credential, FacesContext context) {
        return securityContext
                .authenticate(
                        extractRequest(context),
                        extractResponse(context),
                        withParams().credential(credential));
    }

    public String redirect() {
        return redirection;
    }

    private FacesContext extractContext() {
        return FacesContext.getCurrentInstance();
    }

    private HttpServletRequest extractRequest(FacesContext ctx) {
        return (HttpServletRequest) ctx.getExternalContext().getRequest();
    }

    private HttpServletResponse extractResponse(FacesContext ctx) {
        return (HttpServletResponse) ctx.getExternalContext().getResponse();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

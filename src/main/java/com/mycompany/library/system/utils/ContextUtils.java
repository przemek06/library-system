/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.system.utils;

import javax.faces.event.ActionEvent;

/**
 *
 * @author swiat
 */
public class ContextUtils {
    public static String extractParameter(ActionEvent event, String name) {
        return event.getFacesContext().getExternalContext().getRequestParameterMap().get(name);
    }
}

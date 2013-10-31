/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.template.utils;

/**
 *
 * @author leandro
 */
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class FacesUtil {

    public static String getRequestParameter(String name) {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }
    
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static Object getBean(String nome){
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getELContext().getELResolver().getValue(context.getELContext(), null, nome);
    }

    public static void mostraMensagem(final String message) {
        FacesMessage msg = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public static String getViewAnterior(){
        return FacesContext.getCurrentInstance().getViewRoot().getViewId();
    }

}
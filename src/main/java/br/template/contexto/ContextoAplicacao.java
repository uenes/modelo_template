/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.template.contexto;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextoAplicacao implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

        try {
            System.out.println("CONTEXTO EM P !");
        } catch (Exception e) {
            System.out.println("Erro ao inicializar o contexto da aplicacao: " + e.getMessage());
        }
    }

    public void contextDestroyed(ServletContextEvent sce) { }
}

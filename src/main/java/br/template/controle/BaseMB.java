package br.template.controle;

import br.template.utils.MensagemUtils;
import java.util.Locale;
import java.util.TimeZone;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

class BaseMB {

    /**
     * Retorna o contexto JSF
     */
    protected FacesContext getContexto() {
        return FacesContext.getCurrentInstance();
    }

    /**
     *
     * Retorna o timezone do GMT (o default de Brasilia  GMT-3, logo
     * a data aparecer com trs horas a mais.
     *
     * Deve ser utilizado no atributo timeZone do rich:calendar para datas
     * que no possuem horas.
     *
     * Corrige o problema da data aparecer com um dia a menos
     * (reduo de uma hora da data por causa do horrio de vero)
     *
     */
    public TimeZone getTimeZoneGMT(){
        return TimeZone.getTimeZone("GMT");
    }

    protected void fatal(final String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, null));
    }

    protected void error(final String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    protected void info(final String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    protected void warn(final String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
    }

    /**
     * Recupera a mensagem do arquivo de mensagens
     * @param key - nome que referencia a mensagem
     * @return - mensagem do arquivo de mensagens referenciada pela key
     */
    protected String getMensagemBundled(String key, String... argumentos) {
        return MensagemUtils.getMessageResourceString(MensagemUtils.MENSAGENS_MBEANS, key, argumentos, new Locale("pt","BR"));
    }
}

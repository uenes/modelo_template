package br.template.modelo;

import br.template.utils.MensagemUtils;
import java.io.Serializable;
import java.util.Locale;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Identificacao implements Serializable {

    public static final Integer PERIL_USUARIO_ADMIN = 1;
    public static final Integer PERIL_USUARIO_LIDER_GF = 2;

    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private String senha;
    private String nome;
    private Integer perfil;
    
    public Identificacao() {
        
    }
    
    public Identificacao(Long id, String login, String senha, Integer perfil) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getPerfilString() {

        if (getPerfil().equals(PERIL_USUARIO_ADMIN)) {
            return MensagemUtils.getMessageResourceString(MensagemUtils.MENSAGENS_VIEWS, "perfilAdministradorLabel", null, Locale.getDefault());
        } else if (getPerfil().equals(PERIL_USUARIO_LIDER_GF)) {
            return MensagemUtils.getMessageResourceString(MensagemUtils.MENSAGENS_VIEWS, "perfilFaculdadeLabel", null, Locale.getDefault());
        }
        
        return null;
    }

    public boolean isPerfilAdministrador() {
        return getPerfil().equals(PERIL_USUARIO_ADMIN);
    }

    public boolean isPerfilFaculdade() {
        return getPerfil().equals(PERIL_USUARIO_LIDER_GF);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the perfil
     */
    public Integer getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(Integer perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
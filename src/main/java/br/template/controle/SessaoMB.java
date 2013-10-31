package br.template.controle;

import br.template.DAO.IdentificacaoDAO;
import br.template.modelo.Identificacao;
import br.template.utils.FacesUtil;

public class SessaoMB {

    private Identificacao usuarioLogado;
    private String login;
    private String senha;
    private IdentificacaoDAO identificacaoDAO = new IdentificacaoDAO();

    public String logar() {
        Identificacao usuario = getUsuario(login, senha);
        if (usuario != null) {
            usuario.setPerfil(1);
            setUsuarioLogado(usuario);
        } else {
            FacesUtil.mostraMensagem("No foi possvel realizar o login.");
        }

        return "ENTRAR";
    }

    private Identificacao getUsuario(String login, String senha) {
        return identificacaoDAO.getPorLoginESenha(login, senha);
    }

    public String sair() {
        setUsuarioLogado(null);
        FacesUtil.getSession().invalidate();
        return "SAIR";
    }

    public String home() {
        return "HOME";
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

    public Identificacao getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Identificacao usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}

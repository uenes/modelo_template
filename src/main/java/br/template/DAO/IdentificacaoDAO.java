package br.template.DAO;

import br.template.DAO.jpa.generico.DaoGenerico;
import br.template.contexto.Constantes;
import br.template.modelo.Identificacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.ObjectNotFoundException;

public class IdentificacaoDAO implements DaoGenerico<Identificacao, Long> {
    private Connection connection;
    
    public Identificacao getPorLoginESenha(String login, String senha) {
        connection  = ConnectionFactory.getConnection();
        Identificacao identificacao = new Identificacao();
        String sql =  
                "select login, senha "
                + "from " + Constantes.NOME_DB + ".identificacao "
                + "where login = ? and senha = ?";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);
            // seta os valores
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            
            if (!rs.next()) {
                return null;
            }
                
            while (rs.next()){
                identificacao.setLogin(rs.getString("login"));
            }
            
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return identificacao;        
    }

    @Override
    public Identificacao inclui(Identificacao obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Identificacao getPorId(Long id) throws ObjectNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Identificacao getPorIdComLock(Long id) throws ObjectNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void altera(Identificacao obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exclui(Identificacao obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Identificacao> getPorNome(String nome) {
        connection  = ConnectionFactory.getConnection();
        List<Identificacao> listaIdentificacao = new ArrayList<Identificacao>();
        String sql =  
                "select id, login, senha, perfil, nome "
                + "from " + Constantes.NOME_DB + ".identificacao "
                + "where UPPER(nome) like ? ";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);
            // seta os valores
            stmt.setString(1, "%" + nome.toUpperCase() + "%");
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                Identificacao identificacao = new Identificacao();
                identificacao.setId(rs.getLong("id"));
                identificacao.setLogin(rs.getString("login"));
                identificacao.setSenha(rs.getString("senha"));
                identificacao.setPerfil(rs.getInt("perfil"));
                identificacao.setNome(rs.getString("nome"));
                
                listaIdentificacao.add(identificacao);
            }
            
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaIdentificacao; 
    }
}

package br.template.DAO.jpa.generico;
import java.io.Serializable;
import javax.ejb.ObjectNotFoundException;

public interface DaoGenerico <T, PK extends Serializable> {
    T inclui(T obj);

    T getPorId(PK id) throws ObjectNotFoundException;

    T getPorIdComLock(PK id) throws ObjectNotFoundException;
    
    void altera(T obj);

    void exclui(T obj);    
}
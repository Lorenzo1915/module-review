package it.module.DAO.interfaces;

import it.module.entities.Coffee;
import it.module.entities.Sell;

import java.sql.SQLException;
import java.util.List;

public interface SellsDAO {
    public List<Sell> getAll() throws SQLException;
    public List<Sell> getAllOver100(long id) throws SQLException;
    public int insert(Sell sell) throws SQLException;
    public List<Sell> getByIdCoffee(long idCoffee) throws SQLException;
    public int delete(long id)  throws SQLException;                                                              // elimina da database, tramite codice direttamente



}

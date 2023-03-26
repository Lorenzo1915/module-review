package it.module.DAO.interfaces;

import it.module.entities.Coffee;
import it.module.entities.Sell;

import java.sql.SQLException;
import java.util.List;

public interface CoffeesDAO {
    public List<Coffee> getAll() throws SQLException;
    public int insert(Coffee coffee) throws SQLException;
    public int update (String name, long id) throws SQLException;


}

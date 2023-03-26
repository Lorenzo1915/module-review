package it.module.DAO;

import it.module.DAO.interfaces.CoffeesDAO;
import it.module.entities.Coffee;
import it.module.entities.Sell;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoffeesRepository implements CoffeesDAO {
    private final Connection connection;
    public CoffeesRepository(String dbName, String user, String password)throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, user, password);
    }

    @Override
    public List<Coffee> getAll() throws SQLException {
        ArrayList<Coffee> coffees = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM coffees");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Coffee coffee = new Coffee();


            coffee.setId(resultSet.getLong("id"));
            coffee.setName(resultSet.getString("name"));
            coffee.setPrice(resultSet.getDouble("price"));



            coffees.add(coffee);
        }
        resultSet.close();
        preparedStatement.close();

        return coffees;
    }

    @Override
    public int insert(Coffee coffee) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO coffees (name, price) " +
                "VALUES (?,?);");

        preparedStatement.setString(1, coffee.getName());
        preparedStatement.setDouble(2, coffee.getPrice());



        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public int update( String name, long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE coffees SET name = ? "  +
                "WHERE id =?" );

        preparedStatement.setLong(2, id);
        preparedStatement.setString(1, name);

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }


}

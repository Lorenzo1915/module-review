package it.module.DAO;

import it.module.DAO.interfaces.SellsDAO;
import it.module.entities.Coffee;
import it.module.entities.Sell;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellsRepository implements SellsDAO {
    private final Connection connection;
    public SellsRepository(String dbName, String user, String password)throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, user, password);

    }
    @Override
    public List<Sell> getAll() throws SQLException {
        ArrayList<Sell> sells = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM sells");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Sell sell = new Sell();


            sell.setId(resultSet.getLong("id"));
            sell.setDateSell(resultSet.getString("date_sell"));
            sell.setQuantity(resultSet.getInt("quantity"));
            sell.setId(resultSet.getLong("id_coffee"));



            sells.add(sell);
        }
        resultSet.close();
        preparedStatement.close();

        return sells;
    }

    @Override
    public List<Sell> getAllOver100(long id) throws SQLException {
        ArrayList<Sell> sells = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM sells WHERE quantity > 100 AND id_coffee =?");
        preparedStatement.setLong(1,id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Sell sell = new Sell();


            sell.setId(resultSet.getLong("id"));
            sell.setDateSell(resultSet.getString("date_sell"));
            sell.setQuantity(resultSet.getInt("quantity"));
            sell.setId(resultSet.getLong("id_coffee"));



            sells.add(sell);
        }
        resultSet.close();
        preparedStatement.close();

        return sells;
    }

    @Override
    public int insert(Sell sell) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO sells (date_sell, quantity, id_coffee) " +
                "VALUES (?,?,?);");

        preparedStatement.setString(1, sell.getDateSell());
        preparedStatement.setInt(2, sell.getQuantity());
        preparedStatement.setLong(3, sell.getIdCoffee());



        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public List<Sell> getByIdCoffee(long idCoffee) throws SQLException {
        ArrayList<Sell> sells = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM sells WHERE id_coffee = ?");
        preparedStatement.setLong(1,idCoffee);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Sell sell = new Sell();


            sell.setId(resultSet.getLong("id"));
            sell.setDateSell(resultSet.getString("date_sell"));
            sell.setQuantity(resultSet.getInt("quantity"));
            sell.setIdCoffee(resultSet.getLong("id_coffee"));

            sells.add(sell);
        }
        resultSet.close();
        preparedStatement.close();

        return sells;
    }

    @Override
    public int delete(long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM sells WHERE id= ?");
        preparedStatement.setLong(1,id);

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }


}

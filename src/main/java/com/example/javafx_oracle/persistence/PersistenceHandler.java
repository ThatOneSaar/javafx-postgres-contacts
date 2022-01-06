package com.example.javafx_oracle.persistence;

import com.example.javafx_oracle.domain.Friend;
import com.example.javafx_oracle.domain.IPersistenceHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersistenceHandler implements IPersistenceHandler {
    private static PersistenceHandler instance;
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "friends";
    private String username = "postgres";
    private String password = "kirgizia";
    private Connection connection = null;

    private PersistenceHandler(){
        initializePostgresqlDatabase();
    }

    public static PersistenceHandler getInstance(){
        if (instance == null) {
            instance = new PersistenceHandler();
        }
        return instance;
    }

    private void initializePostgresqlDatabase() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
            System.out.println("DB connected");
        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) {
                System.exit(-1);
            }
        }
    }

    @Override
    public List<Friend> getFriends() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM friends");
            ResultSet sqlReturnValues = stmt.executeQuery();

            List<Friend> returnValues = new ArrayList<>();

            while (sqlReturnValues.next()){
                returnValues.add(new Friend(sqlReturnValues.getInt(1),sqlReturnValues.getString(2),sqlReturnValues.getInt(3)));
            }
            return returnValues;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Friend> sortFriendsByID() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM friends ORDER BY id;");
            ResultSet sqlReturnValues = stmt.executeQuery();

            List<Friend> returnValues = new ArrayList<>();

            while (sqlReturnValues.next()){
                returnValues.add(new Friend(sqlReturnValues.getInt(1),sqlReturnValues.getString(2),sqlReturnValues.getInt(3)));
            }
            return returnValues;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Friend> sortFriendsByName() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM friends ORDER BY Name;");
            ResultSet sqlReturnValues = stmt.executeQuery();

            List<Friend> returnValues = new ArrayList<>();

            while (sqlReturnValues.next()){
                returnValues.add(new Friend(sqlReturnValues.getInt(1),sqlReturnValues.getString(2),sqlReturnValues.getInt(3)));
            }
            return returnValues;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean createFriend(Friend friend) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO friends (name, phone_number) VALUES (?,?);");
            insertStatement.setString(1, friend.getName());
            insertStatement.setInt(2,friend.getPhone());

            insertStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public boolean updateFriend(Friend friend, int id) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement(
                    "UPDATE friends SET name = ?, phone_number = ? WHERE id = ?;");
            updateStatement.setString(1, friend.getName());
            updateStatement.setInt(2,friend.getPhone());
            updateStatement.setInt(3,id);

            updateStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public boolean deleteFriend(int id) {
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(
                    "DELETE FROM friends WHERE id = ?;");
            deleteStatement.setInt(1,id);

            deleteStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        try {
            PreparedStatement deleteallStatement = connection.prepareStatement(
                    "DELETE FROM friends;");
            deleteallStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }
}
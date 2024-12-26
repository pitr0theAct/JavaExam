package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection GetDbConnection() throws ClassNotFoundException,SQLException{
        String connectionString =  "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";

        //Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser (User user){
        String insert = "INSERT INTO " + Const.USER_TABLE+ "(" +
                Const.USERS_FIRSTNAME + "," +Const.USERS_SECONDNAME +
                "," + Const.USERS_THIRDNAME + "," +Const.USERS_PASSWORD + ")" +
                "VALUES(?,?,?,?)";

        try {
            PreparedStatement prSt = GetDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstname());
            prSt.setString(2, user.getSecondname());
            prSt.setString(3, user.getThirdname());
            prSt.setString(4, user.getPassword());

            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getUser(User user){
        ResultSet resSet= null;

        String select="SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_SECONDNAME + "=? AND " + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = GetDbConnection().prepareStatement(select);
            prSt.setString(1, user.getSecondname());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSet;
    }



}

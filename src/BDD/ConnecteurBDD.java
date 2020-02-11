/*package BDD;

import java.sql.*;

public class ConnecteurBDD {

private static final String user = "root";
private static final String password = "charolife22!";
private static final String url = "jdbc:mysql://localhost:3308/zombie?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";

private static volatile Connection connexionBdd = null;

public static Connection getInstance(){
        if(connexionBdd == null){
            new ConnecteurBDD();
        }
        return connexionBdd;
    }

    /**
     * Constructeur se connectant à la BDD
     */
  /*  private ConnecteurBDD(){
        try{
            connexionBdd = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}*/
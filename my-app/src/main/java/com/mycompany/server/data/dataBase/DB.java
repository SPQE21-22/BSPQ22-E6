package com.mycompany.server.data.dataBase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/** The Class DB. */
public class DB {
    

    /** The Constant LOG. */
    private static final Logger LOG = Logger.getLogger(DB.class.getName());

    /**
     * Inicializes SQLITE DB y return a conection with it.
     *
     * @param nameDB  Name of the file of DataBase
     * @return Conexion with the indicated DataBase. If there is an error it will return null
     */
	
	public static Connection initDB( String nameDB ) {
		try {
		    Class.forName("org.sqlite.JDBC");
		    Connection con = DriverManager.getConnection("jdbc:sqlite:" + nameDB );
		    LOG.log(Level.INFO,"Succesfull conection");
		    return con;
		} catch (ClassNotFoundException | SQLException e) {
			LOG.log(Level.WARNING,e.getMessage());
			return null;
		}
	}


    /**
     *  Close the open DataBase.
     *
     * @param con DataBase open Conexion
     * @param st DataBase open Sentence
     */
	public static void closeDB( Connection con, Statement st ) {
		try {
			if (st!=null) st.close();
			if (con!=null) con.close();
		LOG.log(Level.INFO,"Has been successfully close");
		} catch (SQLException e) {
		LOG.log(Level.WARNING,e.getMessage());
		}
	}

    //TODO:
        /*
            Methods:
                CreateBD
                UserExist
                CheckUser
                InsertUser

        */


    /**
     * Creates the event.
     *
     * @param name the name
     * @param date the date
     * @param place the place
     */
    public static void createEvent(String name, String date, String place) {

        String s = "INSERT INTO Event (name, date, place) VALUES('"+ name +"','"+ date +"','"+place+"')";
        Connection c = DB.initDB("database.sql");
        try {
            Statement st = c.createStatement();
            st.executeUpdate(s);
            closeDB(c, st);
            LOG.log(Level.INFO,"Succesfull");
        } catch (SQLException e) {
            LOG.log(Level.WARNING,e.getMessage());
        }

    }





}

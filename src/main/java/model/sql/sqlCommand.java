package model.sql;

public class DaoSqls {
    public static final String INSERT_USER = "INSERT INTO userinfo VALUES (?,?,?,?,?);";
    public static final String SELECT_ALL_USER = "SELECT* FROM userinfo;";
    public static final String SELECT_USER = "SELECT*  FROM userinfo WHERE id = ?";
    public static final String DELETE_USER = "DELETE FROM userinfo where id = ?";
    public static final String UPDATE_USER =  "UPDATE userinfo SET name ";
}

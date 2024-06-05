package Model;
import java.sql.*;
import javax.swing.JOptionPane;
public class Conectar {
    public Conectar(){
    }
        public Connection getConnection(){
        String url = "jdbc:sqlserver://localhost:1433; databaseName= BDTrabalho; user=sa; Password=Pedro4815162342; encrypt=false";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        
        try{
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            System.out.println("Class.forName error");
            System.out.println(e.getMessage());
        }
        try{
            return DriverManager.getConnection(url);
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro na inserção!\nErro: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

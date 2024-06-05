package Model;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FuncionarioDAO {
    Conectar conectar;
    
    public FuncionarioDAO(){
        conectar = new Conectar();
    }
    
    public String insertFuncionario (String cpf, String nome, String cargo, String celular, String endereco, int nr_end, String dt_nasc, Double salario){
        String respReg = null;
        try{
            Connection acessoDB = conectar.getConnection();
            CallableStatement cs = acessoDB.prepareCall("{call sp_insertFuncionario(?,?,?,?,?,?,?,?)}");
            cs.setString(1, cpf);
            cs.setString(2, nome);
            cs.setString(3, cargo);
            cs.setString(4, celular);
            cs.setString(5, endereco);
            cs.setInt(6, nr_end);
            cs.setString(7, dt_nasc);
            cs.setDouble(8, salario);
            
            int numRegAfetados = cs.executeUpdate();
            
            if(numRegAfetados > 0){
                respReg = "Registro gravado.";
            }
        }catch (Exception e){
        }
        return respReg;
}

    public ArrayList<Funcionario> listaFuncionario(){
        ArrayList listaFuncionario;
        listaFuncionario = new ArrayList();
        Funcionario funcionario;
        try{
            Connection acessoDB = conectar.getConnection();
            PreparedStatement ps = acessoDB.prepareStatement("select cpf, nome, cargo, celular, endereco, nr_end, dt_nasc, salario from Funcionario");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                funcionario = new Funcionario();
                funcionario.setCpf(rs.getString(1));
                funcionario.setNome(rs.getString(2));
                funcionario.setCargo(rs.getString(3));
                funcionario.setCelular(rs.getString(4));
                funcionario.setEndereco(rs.getString(5));
                funcionario.setNr_end(rs.getInt(6));
                funcionario.setDt_nasc(rs.getString(7));
                funcionario.setSalario(rs.getDouble(8));
                listaFuncionario.add(funcionario);
            }
        }catch (Exception e){
        }
        return listaFuncionario;
    }
    public int excluirFuncionario(String cpf){
        int numReg = 0;
        try{
            Connection acessoDB = conectar.getConnection();
            CallableStatement cs = acessoDB.prepareCall("{call sp_deleteFuncionario(?)}");
            cs.setString(1, cpf);
            numReg = cs.executeUpdate();
        }catch (Exception e){
        }
        return numReg;
    }
    
    public ArrayList<Funcionario> searchFuncionario(String cpf){
        ArrayList<Funcionario> listaFuncionario = new ArrayList();
        Funcionario funcionario;
        try{
            Connection acessoDB = conectar.getConnection();
            CallableStatement cs = acessoDB.prepareCall("{call sp_searchFuncionario(?)}");
            cs.setString(1, cpf);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                funcionario = new Funcionario();
                funcionario.setCpf(rs.getString(1));
                funcionario.setNome(rs.getString(2));
                funcionario.setCargo(rs.getString(3));
                funcionario.setCelular(rs.getString(4));
                funcionario.setEndereco(rs.getString(5));
                funcionario.setNr_end(rs.getInt(6));
                funcionario.setDt_nasc(rs.getString(7));
                funcionario.setSalario(rs.getDouble(8));
                listaFuncionario.add(funcionario);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return listaFuncionario;
    }
    
    public int updateFuncionario(String cpf, String nome, String cargo, String celular, String endereco, int nr_end, String dt_nasc, Double salario){
        int numReg = 0;
        try{
            Connection acessoDB = conectar.getConnection();
            CallableStatement cs = acessoDB.prepareCall("{call sp_updateFuncionario(?,?,?,?,?,?,?,?)}");
            cs.setString(1, cpf);
            cs.setString(2, nome);
            cs.setString(3, cargo);
            cs.setString(4, celular);
            cs.setString(5, endereco);
            cs.setInt(6, nr_end);
            cs.setString(7, dt_nasc);
            cs.setDouble(8, salario);
            
            numReg = cs.executeUpdate();
        }catch(Exception e){
        }
        return numReg;
    }
    
}
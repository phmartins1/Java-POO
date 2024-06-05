package bdtrabalho;

import Model.*;
import View.*;
import Controller.*;

public class BDTrabalho {
    public static void main(String[] args) {
        frmFuncionario viewC = new frmFuncionario();
        FuncionarioDAO modelC = new FuncionarioDAO();
        ControllerCRUD controleC = new ControllerCRUD(viewC,modelC);
        
        viewC.setVisible(true);
        viewC.setLocationRelativeTo(null);
    }
    
}

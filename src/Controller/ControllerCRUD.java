package Controller;
import Model.*;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControllerCRUD implements ActionListener{
    frmFuncionario viewCRUD = new frmFuncionario();
    FuncionarioDAO modelCRUD = new FuncionarioDAO();
    
    public ControllerCRUD (frmFuncionario viewCRUD, FuncionarioDAO modelCRUD){
        this.modelCRUD = modelCRUD;
        this.viewCRUD = viewCRUD;
        this.viewCRUD.btnSalvar.addActionListener(this);
        this.viewCRUD.btnListar.addActionListener(this);
        this.viewCRUD.btnModificar.addActionListener(this);
        this.viewCRUD.btnExcluir.addActionListener(this);
        this.viewCRUD.btnBuscar.addActionListener(this);
        this.viewCRUD.btnLimpar.addActionListener(this);
        this.viewCRUD.btnAtualizar.addActionListener(this);
    }
    
    public void InicializarCRUD(){
    }
    
    public void CreateTable(JTable tableD){
        DefaultTableModel modelT = new DefaultTableModel();
        tableD.setModel(modelT);
        
        modelT.addColumn("cpf");
        modelT.addColumn("nome");
        modelT.addColumn("cargo");
        modelT.addColumn("celular");
        modelT.addColumn("endereco");
        modelT.addColumn("nr_end");
        modelT.addColumn("dt_nasc");
        modelT.addColumn("salario");
        
        Object[] coluna = new Object[8];
        
        int numRegistros = modelCRUD.listaFuncionario().size();
        
        for (int i = 0; i <numRegistros; i++){
            coluna[0] = modelCRUD.listaFuncionario().get(i).getCpf();
            coluna[1] = modelCRUD.listaFuncionario().get(i).getNome();
            coluna[2] = modelCRUD.listaFuncionario().get(i).getCargo();
            coluna[3] = modelCRUD.listaFuncionario().get(i).getCelular();
            coluna[4] = modelCRUD.listaFuncionario().get(i).getEndereco();
            coluna[5] = modelCRUD.listaFuncionario().get(i).getNr_end();
            coluna[6] = modelCRUD.listaFuncionario().get(i).getDt_nasc();
            coluna[7] = modelCRUD.listaFuncionario().get(i).getSalario();
            modelT.addRow(coluna);
        }
    }
   
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == viewCRUD.btnSalvar){
            String cpf = viewCRUD.txtCpf.getText();
            String nome = viewCRUD.txtNome.getText();
            String cargo = viewCRUD.txtCargo.getText();
            String celular = viewCRUD.txtCelular.getText();
            String endereco = viewCRUD.txtEndereco.getText();
            Integer nr_end = Integer.parseInt(viewCRUD.txtNr_end.getText());
            String dt_nasc = viewCRUD.txtDt_nasc.getText();
            Double salario = Double.parseDouble(viewCRUD.txtSalario.getText());
            
            String rptaRegistro = modelCRUD.insertFuncionario(cpf, nome, cargo, celular, endereco, nr_end, dt_nasc, salario);
            
            if(rptaRegistro != null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
            }else{
                JOptionPane.showMessageDialog(null, "Registro com problemas.");
            }
        }
        if(e.getSource() == viewCRUD.btnListar){
            CreateTable(viewCRUD.jtDados);
        }
        
        if(e.getSource() == viewCRUD.btnLimpar){
           LimparTela();
        }
        
        if(e.getSource() == viewCRUD.btnExcluir){
            int RegInicio = viewCRUD.jtDados.getSelectedRow();
            int numReg = viewCRUD.jtDados.getSelectedRowCount();
            ArrayList<String> listaCpf = new ArrayList();
            String cpf="";
            if(RegInicio>=0){
                for (int i = 0; i < numReg; i++){
                    cpf = String.valueOf(viewCRUD.jtDados.getValueAt(i+RegInicio, 0));
                    listaCpf.add(cpf);
                }
                for (int i = 0; i < numReg; i++){
                    int rptaUser = JOptionPane.showConfirmDialog(null, "Deseja Excluir o Registro:" + listaCpf.get(i) + "?");
                    if(rptaUser == 0){
                        modelCRUD.excluirFuncionario(listaCpf.get(i));
                    }
                }
                CreateTable(viewCRUD.jtDados);
                
            }else{
                JOptionPane.showMessageDialog(null,"Selecione um Registro");
            }
        }
        
        if(e.getSource() == viewCRUD.btnBuscar){
            String cpf = viewCRUD.txtCpf.getText();
            DefaultTableModel modelT = new DefaultTableModel();
            viewCRUD.jtDados.setModel(modelT);
            
            modelT.addColumn("cpf");
            modelT.addColumn("nome");
            modelT.addColumn("cargo");
            modelT.addColumn("celular");
            modelT.addColumn("endereco");
            modelT.addColumn("nr_end");
            modelT.addColumn("dt_nasc");
            modelT.addColumn("salario");

            Object[] coluna = new Object[8];
            int numRegistros = modelCRUD.searchFuncionario(cpf).size();

            for (int i = 0; i < numRegistros; i++){
                coluna[0] = modelCRUD.searchFuncionario(cpf).get(i).getCpf();
                coluna[1] = modelCRUD.searchFuncionario(cpf).get(i).getNome();
                coluna[2] = modelCRUD.searchFuncionario(cpf).get(i).getCargo();
                coluna[3] = modelCRUD.searchFuncionario(cpf).get(i).getCelular();
                coluna[4] = modelCRUD.searchFuncionario(cpf).get(i).getEndereco();
                coluna[5] = modelCRUD.searchFuncionario(cpf).get(i).getNr_end();
                coluna[6] = modelCRUD.searchFuncionario(cpf).get(i).getDt_nasc();
                coluna[7] = modelCRUD.searchFuncionario(cpf).get(i).getSalario();
                modelT.addRow(coluna);
            }
        }
        
        if(e.getSource() == viewCRUD.btnModificar){
            int RegUpdate = viewCRUD.jtDados.getSelectedRow();
            int numReg = viewCRUD.jtDados.getSelectedRowCount();
            
            if(RegUpdate >= 0 && numReg == 1){
                viewCRUD.txtCpf.setText(String.valueOf(viewCRUD.jtDados.getValueAt(RegUpdate, 0)));
                viewCRUD.txtCpf.setEditable(false);
                viewCRUD.txtNome.setText(String.valueOf(viewCRUD.jtDados.getValueAt(RegUpdate, 1)));
                viewCRUD.txtCargo.setText(String.valueOf(viewCRUD.jtDados.getValueAt(RegUpdate, 2)));
                viewCRUD.txtCelular.setText(String.valueOf(viewCRUD.jtDados.getValueAt(RegUpdate, 3)));
                viewCRUD.txtEndereco.setText(String.valueOf(viewCRUD.jtDados.getValueAt(RegUpdate, 4)));
                viewCRUD.txtNr_end.setText(String.valueOf(viewCRUD.jtDados.getValueAt(RegUpdate, 5)));
                viewCRUD.txtDt_nasc.setText(String.valueOf(viewCRUD.jtDados.getValueAt(RegUpdate, 6)));
                viewCRUD.txtSalario.setText(String.valueOf(viewCRUD.jtDados.getValueAt(RegUpdate, 7)));
                viewCRUD.btnExcluir.setEnabled(false);
                viewCRUD.btnLimpar.setEnabled(false);
                viewCRUD.btnListar.setEnabled(false);
                viewCRUD.btnSalvar.setEnabled(false);
                viewCRUD.btnBuscar.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(null, "Selecione um Registro");
            }
        }
        
        if(e.getSource() == viewCRUD.btnAtualizar){
            String cpf = viewCRUD.txtCpf.getText();
            String nome = viewCRUD.txtNome.getText();
            String cargo = viewCRUD.txtCargo.getText();
            String celular = viewCRUD.txtCelular.getText();
            String endereco = viewCRUD.txtEndereco.getText();
            Integer nr_end = Integer.parseInt(viewCRUD.txtNr_end.getText());
            String dt_nasc = viewCRUD.txtDt_nasc.getText();
            Double salario = Double.parseDouble(viewCRUD.txtSalario.getText());
            
            int rptaModificado = modelCRUD.updateFuncionario(cpf, nome, cargo, celular, endereco, nr_end, dt_nasc, salario);
            if(rptaModificado>0){
                JOptionPane.showMessageDialog(null, "Registro modificado com sucesso");
            }else{
                JOptionPane.showMessageDialog(null, "Registro não pode ser modificado");
            }
            LimparTela();
            viewCRUD.txtCpf.setEditable(true);
            viewCRUD.btnExcluir.setEnabled(true);
            viewCRUD.btnLimpar.setEnabled(true);
            viewCRUD.btnListar.setEnabled(true);
            viewCRUD.btnSalvar.setEnabled(true);
            viewCRUD.btnBuscar.setEnabled(true);
        }
        
    }
    public void LimparTela(){
        viewCRUD.txtCpf.setText("");
        viewCRUD.txtNome.setText("");
        viewCRUD.txtCargo.setText("");
        viewCRUD.txtCelular.setText("");
        viewCRUD.txtEndereco.setText("");
        viewCRUD.txtNr_end.setText("");
        viewCRUD.txtDt_nasc.setText("");
        viewCRUD.txtSalario.setText("");
    }
       
}

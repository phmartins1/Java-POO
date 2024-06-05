
package Model;

public class Funcionario {
    String cpf;
    String nome;
    String cargo;
    String celular;
    String endereco;
    int nr_end;
    String dt_nasc;
    Double salario;
    
    public Funcionario () {
        cpf = "";
        nome = "";
        cargo = "";
        celular = "";
        endereco = "";
        nr_end = 0;
        dt_nasc = "";
        salario = 0.00;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNr_end() {
        return nr_end;
    }

    public void setNr_end(int nr_end) {
        this.nr_end = nr_end;
    }

    public String getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(String dt_nasc) {
        this.dt_nasc = dt_nasc;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}
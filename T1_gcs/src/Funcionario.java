public class Funcionario {
    private int id;
    private String nome;
    private String departamento;

    // Construtor
    public Funcionario(int id, String nome, String departamento) {
        this.id = id;
        this.nome = nome;
        this.departamento = departamento;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}

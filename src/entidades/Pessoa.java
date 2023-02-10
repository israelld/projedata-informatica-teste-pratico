package entidades;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;

    public Pessoa() {}

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;

    }

    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataNascimentoFormated = dataNascimento.format(formatter);

        return "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimentoFormated;
    }
}
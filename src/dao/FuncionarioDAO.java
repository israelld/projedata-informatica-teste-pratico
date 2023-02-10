package dao;
import entidades.Funcionario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class FuncionarioDAO {

    public List<Funcionario> lerFuncionarios() {

        List<Funcionario> funcionarioList = new ArrayList<>();
        File filePath = new File("src\\recursos\\lista-de-funcionarios.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.getAbsolutePath()))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(data[0].trim());
                funcionario.setDataNascimento(LocalDate.parse(data[1].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                funcionario.setSalario(new BigDecimal(data[2].trim()));
                funcionario.setFuncao(data[3].trim());
                funcionarioList.add(funcionario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return funcionarioList;
    }

}

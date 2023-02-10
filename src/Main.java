import entidades.*;
import dao.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Main {
    public static void main(String[] args) {

        List<Funcionario> listaDefuncionarios = new FuncionarioDAO().lerFuncionarios();

        String funcionarioRemovido = "João";
        System.out.println("\nLista de Funcionários sem o " + funcionarioRemovido + "!\n");

        listaDefuncionarios.removeIf(funcionario -> funcionario.getNome().equals(funcionarioRemovido));

        listaDefuncionarios.forEach(System.out::println);

        System.out.println("\nLista de Funcionários com 10% de aumento!\n");

        for (Funcionario funcionario : listaDefuncionarios) {
            funcionario.setSalario(
                    funcionario.getSalario().add(
                            funcionario.getSalario().divide(new BigDecimal("10"), RoundingMode.CEILING)));
        }

        listaDefuncionarios.forEach(System.out::println);

        System.out.println("\nMapeamento de Funcionários agrupados por função!\n");

        Map<String, List<Funcionario>> mapDeFuncionarios = listaDefuncionarios.stream()
                .collect(groupingBy(Funcionario::getFuncao));

        mapDeFuncionarios.forEach((key, value) -> System.out.println(key + ":" + value));

        System.out.println("\nLista de Funcionários que fazem aniversário no mês 10 e 12!\n");

        List<Funcionario> listaAniversariantes = listaDefuncionarios.stream()
                .filter(func -> func.getDataNascimento().getMonthValue() == 10 ||
                        func.getDataNascimento().getMonthValue() == 12)
                .collect(Collectors.toList());

        listaAniversariantes.forEach(System.out::println);

        System.out.println("\nFuncionário com a maior idade é...\n");

        LocalDate currentDate = LocalDate.now();
        Period periodoAtual = Period.between(currentDate, currentDate);
        String nomeDoMaisVelho = null;
        Integer idadeDoMaisVelho = null;

        for (Funcionario funcionario : listaDefuncionarios) {
            if (Period.between(funcionario.getDataNascimento(), currentDate).toTotalMonths() > periodoAtual.toTotalMonths()) {
                periodoAtual = Period.between(funcionario.getDataNascimento(), currentDate);
                nomeDoMaisVelho = funcionario.getNome();
                idadeDoMaisVelho = Period.between(funcionario.getDataNascimento(), currentDate).getYears();
            }
        }
        System.out.println(nomeDoMaisVelho + " com " + idadeDoMaisVelho + " anos!");

        System.out.println("\nLista de funcionárioa por ordem alfabética!\n");

        List<Funcionario> funcionariosOrdenados = listaDefuncionarios.stream()
                .sorted((func1, func2) -> func1.getNome().compareTo(func2.getNome()))
                .collect(Collectors.toList());


        funcionariosOrdenados.forEach(System.out::println);

        System.out.println("\nTotal dos salários dos funcionários!\n");

        BigDecimal totalDosSalarios = listaDefuncionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("R$" + NumberFormat.getNumberInstance(new Locale("pt", "BR")).format(totalDosSalarios));

        System.out.println("\nSalários mínimos de cada funcionário!\n");

        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        List<BigDecimal> listaDeSalariosMinimo = listaDefuncionarios.stream()
                .map(func -> func.getSalario().divide(salarioMinimo, RoundingMode.UP))
                .collect(Collectors.toList());

        for(int i = 0; i < listaDefuncionarios.size(); i++){
            System.out.println(
                    listaDefuncionarios.get(i).getNome() +
                    " ganha " +
                    listaDeSalariosMinimo.get(i) +
                    " salarios mínimos!");


        }

    }

}
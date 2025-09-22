import java.util.Map;
import java.util.HashMap;

public class ContaBancaria {

    private double saldo; //o saldo padrão será inicializado automaicamente com 0.0
    private Data dataAbertura = new Data();

    public Map<String, Integer> getDataAbertura() { //os tipos que são declarados devem ser referentes a uma certa classe, portanto, deve-se utilizar de Integer ao invés de int
        Map<String, Integer> dataAbertura = new HashMap<>(); //criando um dicionário para armazenar a data de abertura
        dataAbertura.put("Dia", this.dataAbertura.getDia());
        dataAbertura.put("Mês", this.dataAbertura.getMes());
        dataAbertura.put("Ano", this.dataAbertura.getAno());
        return dataAbertura; //a variável de retorno corresponde ao tipo definido pela interface Map
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setDataAbertura(int dia, int mes, int ano) {
        dataAbertura.setDia(dia);
        dataAbertura.setMes(mes);
        dataAbertura.setAno(ano);
    }

    public String getDataAberturaFormatada() {
        Map<String, Integer> dataAbertura = this.getDataAbertura(); //dessa forma, foco o acesso às informações referentes à data por meio do método getDataAbertura(), trazendo maior facilidade de manutenção à medida que o código aumenta
        String dataAberturaFormatada = (dataAbertura.get("Dia") < 10 ? ("0" + dataAbertura.get("Dia")) : dataAbertura.get("Dia"))  + "/" + (dataAbertura.get("Mês") < 10 ? ("0" + dataAbertura.get("Mês")) : dataAbertura.get("Mês")) + "/" + dataAbertura.get("Ano"); //.get() está fazendo a leitura das informações de um suposto "dicionário"
        return dataAberturaFormatada;
    }

    public String getSaldoFormatado() {

        //será necessário realizar uma pequena formatação da variável saldo antes de poder utilizá-la
        double get_saldo = getSaldo();
        long parte_inteira = (long) get_saldo; //estour realizando o truncamento da parte decimal do número, apenas armazenando sua parte inteira!
        double parte_decimal = get_saldo - parte_inteira; //nessa operação de subtração, prevalece o tipo double!
        //optou-se por trabalhar com o tipo long para conseguir abranger possíveis saldos que extrapolariam o limite do tipo int
        long decimal; //foi necessário definir a variável fora do if...else para que todo o método tivesse acesso a essa variável, caso contrário, a variável só seria acessada localmente pelo if...else
        parte_decimal = parte_decimal * 100; //deslocando a parte decimal duas casas à esquerda
        double aux = parte_decimal - ((long) parte_decimal); //verificando se existe algo além da segunda casa decimal para conferir se é necessário calcular uma aproximação dos centavos
        if(aux >= 0.5) {
            decimal = ((long) parte_decimal) + 1; //não é possível redefinir o tipo da variável que já foi declarada, portanto, tive que criar uma outra variável para conseguir realizar essa manipulação
        }
        else {
            decimal = (long) parte_decimal; //não é possível redefinir o tipo da variável que já foi declarada, portanto, tive que criar uma outra variável para conseguir realizar essa manipulação
        }

        //construindo a string formatada (Ex.: "R$ 1000,75")
        String saldoFormatado = "R$ " + parte_inteira + "," + (decimal == 0 ? "00" : decimal); //dessa forma, foco o acesso ao saldo por meio do método getSaldo(), trazendo maior facilidade de manutenção à medida que o código aumenta
        return saldoFormatado;
    }

    public void depositar(double deposito) {
        this.setSaldo(this.getSaldo() + deposito);
    }

    public int sacar(double saque) {
        if(saque > this.getSaldo()){
            System.out.println("Não foi possível finalizar a operação: saldo insuficiente");
            return 1;
        }
        this.setSaldo(this.getSaldo() - saque);
        return 0;
    }
}
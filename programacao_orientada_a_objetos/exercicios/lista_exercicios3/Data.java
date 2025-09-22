public class Data { //encapsulamento de uma classe

    private int dia; //apenas esta classe é capaz de acessar/modificar os valores presentes dentro dessas variáveis
    private int mes;
    private int ano;

    public int getDia() {
        return this.dia;
    }

    public int getMes() {
        return this.mes;
    }

    public int getAno() {
        return this.ano;
    }

    public void setDia(int dia) {
        this.dia = dia; //precisamos utilizar do "this" para conseguir diferenciar qual variável estamos utilizando, posto que ambas possuem o mesmo nome
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
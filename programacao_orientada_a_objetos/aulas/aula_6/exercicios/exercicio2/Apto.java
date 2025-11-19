public class Apto extends Imovel{
    private int andar;
    private int numero;

    public Apto(int id, int numero, int andar) {
        super(id);
        this.numero = numero;
        this.andar = andar; //coloquei como parte do construtor, posto que essas variáveis não deveriam mudar com o decorrer do programa!
    }

    public int getNumero() {
        return numero;
    }

    public int getAndar() {
        return andar;
    }

}
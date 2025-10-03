public class CasaIsenta extends Casa {
    public CasaIsenta(double p) {
        super(p); 
    }

    public double getPrecoFinal() {
        return getPreco(); //não é preciso usar super.getPreco() porque o método getPreco() é único da classe Imovel e nenhuma classe dentro da hierarquia dessa classe tenta sobrescrevê-la
    }
}
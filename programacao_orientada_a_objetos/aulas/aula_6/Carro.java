public class Carro {
    private String placaCarro;
    private String donoCarro;

    public Carro(String placaCarro, String donoCarro) {
        this.placaCarro = placaCarro;
        this.donoCarro = donoCarro;
    }
    
    //getters e setters
    //...
    public String getDonoCarro() {
        return this.donoCarro;
    }
}
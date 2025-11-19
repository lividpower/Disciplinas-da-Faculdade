public abstract class Imovel { 
    private String nomeProprietario;
    private String endereco;
    private int id;
    private String tipoNegocio;  
    private double valorVenda;
    private double valorAluguel;

    public Imovel(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void setNomeProprietario(String nome) {
        this.nomeProprietario = nome;
    }

    public String getNomeProprietario() {
        return this.nomeProprietario;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setTipoNegocio(String tipo) {
        if(tipo.toLowerCase().equals("aluguel")) {
            tipoNegocio = "aluguel";
        }
        else if(tipo.toLowerCase().equals("venda")) {
            tipoNegocio = "venda";
        }
    }

    public String getTipoNegocio() {
        return tipoNegocio;
    }

    public void setValorComercial(double valor) {
        if(tipoNegocio.equals("aluguel")) {
            valorAluguel = valor;
        }
        else if(tipoNegocio.equals("venda")) {
            valorVenda = valor;
        }
    }

    public double getValorComercial() {
        if(tipoNegocio.equals("aluguel")) {
            return valorAluguel;
        }
        else {
            return valorVenda;
        }
    }
}
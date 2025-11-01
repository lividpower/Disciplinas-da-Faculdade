public class Aeroporto {
    public static void main(String args[]) {
        Aeroporto galeao = new Aeroporto(); //é possível criar uma instância de uma classe mesmo ela implementando o método main!
        Passaro p = new Passaro();
        Aviao v = new Aviao();
        gaelao.darPermissaoPouso(p);
        galeao.darPermissaoPouso(v);
    }

    public void darPermissaoPouso(Voador v) {
        v.pousar();
    }
}
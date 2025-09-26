public class Leao extends Animal {
    public void rugir() {
        //System.out.println("Leão " + this.nome + " está rugindo!"); // "nome" é uma variável privada da classe Animal
        System.out.println("Leão " + this.getNome() + " está rugindo!"); //portanto, utilizamos do método publicoooo para conseguir acessar esse valor
    }

    public void correr() { //este método possui a mesma assinatura do método correr presente na classe Animal
        System.out.println("Leão correndo"); //este método irá acabar sobrescrevendo o método já criado lá na classe Animal
    }
}
public class Processa {

	public static void main(String[] args) {
		Processa p = new Processa(); 
		//é possível declarar uma variável referente a classe que estamos construindo, como se fosse algo "recursivo"...estamos chamando a classe dentro da própria classe
		System.out.println("Chamando o método main() de forma recursiva"); 
		p.main(args);
	}
	public double calcula(double a, double b) {
		return (a+b) * (a*0.1) + (b*0.9);
	}
}
public class Processa {

	public static void main(String[] args) {
		Processa p = new Processa(); 
		//é possível declarar uma variável referente a classe que estamos construindo, como se fosse algo "recursivo"...estamos chamando a classe dentro da própria classe
		System.out.println(p.calcula(2, 3)); 
		//estamos tentando utilizar de um método não-estático, 
		//mas sem declarar uma instância para esta respectiva classe
		//poderíamos simplesmente declarar calcula como um método static
	}
	public double calcula(double a, double b) {
		return (a+b) * (a*0.1) + (b*0.9);
	}
}
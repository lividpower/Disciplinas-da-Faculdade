public class Processa {
//este código não compila!!!
	public static void main(String[] args) {
		System.out.println(this.calcula(2,3)); 
		//estamos tentando utilizar de um método não-estático, 
		//mas sem declarar uma instância para esta respectiva classe
		//o certo seria utilizar, nesse caso, de this.calcula(2,3)
		//ou simplesmente declarar calcula como um método static
	}
	public double calcula(double a, double b) {
		return (a+b) * (a*0.1) + (b*0.9);
	}
}
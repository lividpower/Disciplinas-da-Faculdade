public class Processa {
	//esse código compila!
	public static void main(String[] args) {
		System.out.println(new Processa().calcula(2,3)); 
		//é indiferente utilizar de new Processa() com ou sem a separação de parênteses
		//o que estamos fazendo aqui basicamente é criando uma instância para conseguir manipular o método calcula
	}
	public double calcula(double a, double b) {
		return (a+b) * (a*0.1) + (b*0.9);
	}
}
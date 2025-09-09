//este código está servindo para testarmos diferentes tipos de acesso às variáveis declarados dentro da classe Elefante

public class Elefante {

    //a definição de todas essas variáveis está correta 
    //estas palavras vindo antes do tipo da variável são chamados de "modificadores de acesso", ou seja, definem quem terá acesso àquela determinada variável
	private String nome; 
	private int	idade;
	public boolean morto;
	String cor;

	//não existe este tal modificador de acesso "default"
	//default double peso; (esta declaração está incorreta)

    //esta variável está definida de forma errada
    //a ordem da declaração está invertida
    //uma forma correta seria "public int tamanho;"
	//public tamanho int; (esta declaração está incorreta)
}
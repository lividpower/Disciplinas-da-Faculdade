public	class	Conta {
	double	saldo;
					
	public	void	calcula	()	{
        //double testeSaldo; (possível correção para o código)
        //com essa declaração geral dentro do método calcula(), tudo que está presente dentro desse método terá acesso a essa variável, evitando que ocorra o problema que aconteceu logo abaixo!
        if	(saldo	<	1000) {
	        double	testeSaldo	=	saldo*0.1; //a variável testeSaldo está sendo declarada localmente dentro da função/método if()
        }
		System.out.println(testeSaldo); //quando tentamos acessar a variável testeSaldo fora do if(), já não é mais possível acessá-la!
        //para corrigir esse erro, seria necessário realizar uma declaração dessa variável de forma mais abrangente, antes de sua utilização da estrutura if()
    }
}
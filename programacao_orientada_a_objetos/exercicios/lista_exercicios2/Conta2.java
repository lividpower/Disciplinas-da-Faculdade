/*
public	class	Conta2 {
	double	saldo; //uma variável do tipo double possui 64 bits, por padrão
					
	public	void	calcula	()	{
        if	(saldo	<	1000) { //é possível referenciar a variável "saldo" que foi definida fora do método porque dentro dessa classe ela é conhecida! 
            int	teste	=	saldo - 1000; //uma variável do tipo int possui 32 bits, por padrão 
            //ao tentar atribuir uma variável double a uma variável int, o compilador sinaliza um erro, evitando que essa perda considerável de dados ocorra! 																										
            if	(teste	<	0) {
            System.out.println("Deu	zebra");																				
            }
        }
    }
} */

//correção do código
public	class	Conta2 {
	double	saldo; 
					
	public	void	calcula	()	{
        if	(saldo	<	1000) { 
            double	teste	= saldo - 1000; //mais seguro, pois evita a perda de dados durante a conversão
            //int teste = (int) (saldo - 1000); //corre risco de perda de dados, dependendo do valor que estiver armazenado em double!
            if	(teste	<	0) {
            System.out.println("Deu	zebra");																				
            }
        }
    }
} 
public	class	Conta3 {
	double	saldo;
					
	public	void	calcula	()	{
        if	(saldo) { //Java não é capaz de converter valores numéricos para valores booleanos!
			System.out.println("Tem	saldo");
		} else	{ 
			System.out.println("Não	tem	saldo");
        }
	}
}
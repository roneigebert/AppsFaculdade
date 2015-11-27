Feature: Exercicio Pagamento para Sociedade
	To: Verificar se cobranças são divididas corretamente entre os sócios
	
	Scenario: Exame do cavalo "Mensagem Polimórfica"
		Given O cavalo de corrida "Mensagem Polimórfica"
		And é possuído pelas seguintes pessoas:
			|     nome      | cota |
			| Grady Booch   |  30  |
			| Martin Fowler |  20  |
			| Ralph Johnson |  20  |
			| Erich gama    |  10  |
			| Brian Foote   |  20  |
		Then O cavalo está começando a mostrar alguns sinais de claudicação, assim Ralph Johnson traz o cavalo em um exame 
		Then Dr. Roberts decide que não é motivo de preocupação e prescreve algum analgésico tópico.
		Then Ralph Johnson é fornecido com uma fatura discriminando as acusações
		And A fatura também mostra o percentual da conta que cada proprietário é responsável.
		Then No final do mês, um boleto é enviado para Grady, Martin, Ralph, Erich e Brian detalhando a quantidade que eles devem.
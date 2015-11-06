Feature: Exercicio Pagamento Boleto
	To: Permitir faturar no final do mês
	
	Scenario: Castrar gatos e pagar no final do mês
		Given Traci Heinrich traz seus dois gatos, Tweedle Dee e Tweedle Dum, para serem castrados.
		And Dr. Roberts realiza as esterilizações (que tem uma taxa normal).
		And Traci pega seus gatos naquela noite, mas deseja pagar pelo serviço ao final do mês.
		Then Naquele dia, ela recebe uma única fatura com os procedimentos detalhadas.
		And No final do mês, ela recebe um boleto referente àquela conta, com o qual ela paga pelo serviço.
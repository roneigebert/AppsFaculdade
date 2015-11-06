Feature: Exercicio Exame de Rotina
	To: Verificar se cobranças de exames rotineiros são cobrados corretamente 
	
	Scenario: Exame de rotina do Doberman
		Given Dave Atkins traz seu Doberman de estimação chamado Fofo na clínica para exames de rotina. 
		Then O veterinário cobra dele a consulta de rotina e a vacinação contra a raiva, que custam 25 e 40 reais, respectivamente.. 
		Then Dave paga em dinheiro antes de ele sair para o trabalho e lhe é fornecido um recibo de pagamento para os serviços.
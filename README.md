# fj22-argentum-web
Projeto desenvolvido como parte do treinamento FJ 22 da Caelum.
O Argentum Web tem o objetivo de simular váriações de negociações na bolsa de valores, mostrando indicadores atravé de gráficos.

Descrição
* As negociações são carregadas de um web service disponibilizado pela propria Caelum.
* Estas negociações são arganizadas dentro de um elemento denominado Candle, no qual são resumidas todas as negociações de mesmo dia.
Cada Candle tem alguns atributos como: valor da ultima negociação do dia(fechamento), valor da primeira negociação do dia(abertura), valor total das negociações do dia, etc.
* Cada ponto do grafico representa uma media entre os Candles dos três ultimos dias (a quantidade de dias pode ser alterada atravé do código fonte).
* O usuario tem a liberdade de decidir o tipo de media que será considerada no gráfico(simples ou ponderada)
* O usuario tem a liberdade de decidir se o valor dos Candles considerados no gráfico será de abertura ou de fechamento

Aspectos Tecnicos
* Os dados carregados do web service(em XML) são convertidos em objetos do tipo Negociacao atravé da ferramenta XStream.
* Os componentes vistos pelo usuario no navegador são baseados em JSF e Primefaces.
* Para os teste foi utilizado JUnit com tecnicas de TDD(Test Driven Design)

# Exercício Caixa
https://lostechies.com/gabrielschenker/2015/05/26/event-sourcing-revisited/

Este exemplo de caixa eletr�nico implementa a l�gica do Event Sourcing, onde toda opera��o que ocorre no caixa eletr�nico � lan�ado
um evento. A classe conta corrente possui uma lista desse eventos, onde � poss�veil percorrer todos eventos adicionados a essa lista
obtendo assim um hist�rico de todos eventos..

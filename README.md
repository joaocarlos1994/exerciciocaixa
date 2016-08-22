# ExercÃ­cio Caixa

O exemplo do caixa eletronico tem como exemplo o Sourcing Event.
Artigo: https://lostechies.com/gabrielschenker/2015/05/26/event-sourcing-revisited/

Este exemplo de caixa eletrônico implementa a lógica do Event Sourcing, onde toda operação que ocorre no caixa eletrônico é lançado
um evento. A classe conta corrente possui uma lista desse eventos, onde é possíveil percorrer todos eventos adicionados a essa lista
obtendo assim um histórico de todos eventos.




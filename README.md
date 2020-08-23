#  :file_folder: Trabalho da disciplina de JAVA 2 :coffee:

## :money_with_wings: Aplicação de gerenciamento de contas bancárias 


:chart_with_upwards_trend: O projeto consiste na criação de uma aplicação 
de gerenciamento de contas bancárias em Java que calcula operações de 
crédito e débito.

## Conteúdo abordado:

O programa envolve os seguintes conceitos e recursos:

- REST

- POST - Inserir (Create) - INSERT

- GET - Ler (Read) - SELECT

- PUT - Atualizar (Update) - UPDATE

- DELETE - Apagar (Delete) - DELETE

- MAVEN

- RESPONSEENTITY

- EXCEPTIONS


### :bar_chart: Especificação de acesso REST:

GET /conta -> lista todas as contas;

GET /conta/<numero> -> retorna a conta com o número passado;

POST /conta -> insere uma nova conta;

PUT /conta/numero -> atualiza a conta (somente nome do titular 
e numero);

POST /conta/numero/operacao -> recebe uma operacao com parâmetro
e a partir dela atualiza o saldo;

DELETE /conta/numero -> remove a conta cujo número foi passado.


### :chart_with_downwards_trend:Tabela de Titulares das contas bancárias 

 Número  | Titular   | Saldo    
|--------|-----------|----------
1        | Victor    | 5000.00
2        | Lais      | 4000.00
3        | Priscila  | 5000.00
4        | Evodio    | 7000.00
5        | Carlos    | 5000.00

OBS: Os valores acima citados estão sujeitos as alterações das operações.


## Contribuidores

 :key:[Carlos Elian](https://github.com/CarlosElian)
 :key:Evodio
 :key:[Lais Moreira](https://github.com/lais-mm)
 :key:[Priscila Barcala](https://github.com/priscilabarcala)
 :key:[Victor de Freitas](https://github.com/FrtsVictor)


## Mentoria
:key:[Marcelo Assis](https://github.com/massisjr)

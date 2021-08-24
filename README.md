# Exploring-mars

 * Um planeta pode possuir vários planaltos, que nesse projeto é representado sempre por um retângulo, dado uma medida X e Y, onde X e Y não podem ser igual ou menor que 0
Sendo o ponto ao norte de (x,y) sempre (x, y + 1), e o ponto leste sempre (x + 1, y)
* Uma sonda é cadastrada virada para ponto cardeal (Norte, Sul, Leste ou Oeste) e podem se mover na direção que estão viradas, ou rotacionar 90º para esquerda ou direita. Ela são posicionadas em um ponto X e Y, onde os mesmos devem estar válido dentro do planalto na qual esta foi inserida
 * Várias sondas podem ser colocadas nesse planalto e elas podem se movimentar recebendo instruções, das quais:
   - M -> Se move para a direção na qual a sonda está virada
   - R -> Vira a direção a qual a sonda está para o lado direito
   - L -> Vira a direção a qual a sonda está para o lado esquedo


Essa instruções podem ser passadas uma a uma, ou em conjunto em uma String única
Ex: "MRMLM" Onde é instruído à sonda em sequência: mover uma casa, virar a direita, se mover, virar a esquerda, se mover

## API

- Cadastrar planeta (/planets) POST
   * O nome do planeta deve ser único
- Parâmetros:
    - name: Nome do planeta
   ``` Params: 
    {
        "name": String
    }
    ```
    Retornos: 
        201 -> Criado com sucesso + Body do objeto criado
           ``` Params: 
    {
        "id": Int
        "name": String
    }
    ```
        422 -> Planeta com nome igual já registrado
    
- Cadastrar planalto (/planets/{planetId}/plateaus) POST
    * xSize e ySize devem ser maiores que 0
    * Planeta deve existir
    Parâmetros:
    - name: Nome do planalto
    - planetId: planeta a salvar o planalto
    - xSize: tamanho do eixo X do planalto
    - ySize: tamanho do eixo Y do planalto
    ``` Params: 
    {
        "name": String,
        "xSize": Int,
        "ySize": Int
    }
    ```
        Retornos: 
        201 -> Criado com sucesso + Body do objeto criado
            ``` Params: 
    {
        "id": Int,
        "planetId": Int,
        "name": String,
        "xSize": Int,
        "ySize": Int
    }
    ```
        422 -> Planalto com tamanho inválido (xSize < 0 ou ySize < 0)
        422 -> Planeta não cadastrado
    
    - Cadastrar sonda (/planets/{planetId}/plateaus/{plateauId}/rovers) POST
    * xPosition e yPosition devem ser maiores que 0
    * xPosition e yPosition devem ser uma posição válida dentro do planalto em que a sonda está sendo cadastrada
    * Planalto deve pertencer ao planeta
    Parâmetros:
    - name: Nome da sonda
    - planetId: planeta que pertence ao planalto em que a sonda será inserioda
    - plateauId: planalto em que a sonda será inserida
    - xPosition: posição inicial X da sonda no planalto
    - yPosition: posição inicial Y da sonda no planalto
    - facingSide: ponto cardeal que a sonda estará apontada inicialmente
    * E = EAST
    * W = WEST
    * N = NORTH
    * S = SOUTH
    ``` Params: 
    {
        "id": Int,
        "planetId": Int,
        "plateauId": Int,
        "name": String,
        "xPosition": Int,
        "yPosition": Int,
        "facingSide": String 
    }
    ```
        Retornos: 
        201 -> Criado com sucesso + Body do objeto criado
        422 -> Sonda inserida em um espaço inválido dentro do planalto
        422 -> Planalto não pertence ao planeta
        422 -> Ponto cardeal inicial inválido
        
    - Enviar instruções a sonda (/planets/{planetId}/plateaus/{plateauId}/rovers/{roverId}/ṕositions) PATCH
    * Sonda deve pertencer ao planalto
    * Planalto deve pertencer ao planeta
    * Sondas não podem ocupar o mesmo espaço dentro de um planalto
    * Sonda não pode se movimentar para um espaço inválido de um planalto
    Parâmetros:
    - instructions: instrução ou conjunto de instruções
    ``` Params: 
    {
        "instructions": "MRMRMRMLMLMLM"
    }
    ```
        Retornos: 
        200 -> Instruções executadas com sucesso + Body da sonda movimentada
            ``` Params: 
    {
        "id": Int,
        "planetId": Int,
        "plateauId": Int,
        "name": String,
        "xPosition": Int,
        "yPosition": Int,
        "facingSide": String 
    }
    ```
        422 -> Sonda movimentada para espaço inválido do planalto
        422 -> Sonda encontrou com outra sonda e não pode executar movimentos nessa direção
        
    ## Como rodar

  ``` gradle build ```
 
  ``` docker-compose build```
  
  ``` docker-compose up```
  
  Porta default do app é 9011
  
  ## To-do Melhorias
  - [ ] Padronização de código de retorno em erros com i18n

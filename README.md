- Cadastrar planeta (/planets) POST
    Um planeta é representado por um planalto, que nesse projeto é representado sempre por um retangular, dado uma medida X e Y, onde X e Y não podem ser igual ou menor que 0
    Sendo o ponto ao norte de (x,y) sempre (x, y+1), e o ponto leste sempre (x+1, y) 
    Params: 
        - name: Nome do planeta
        - xSize: tamanho do eixo X do planeta
        - ySize: tamanho do eixo Y do planeta
    {
        "name": String,
        "xSize": Integer,
        "ySize": Integer
    }
  
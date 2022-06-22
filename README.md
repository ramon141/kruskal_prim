# Atividade
Tema III – Testar a conectividade antes da MST
Sabemos que para calcular a Árvore Geradora Mínima de um dado grafo, esse
grafo deve ser não-dirigido, ponderado e conexo. Dessa forma, implemente o
algoritmo de Kruskal e o algoritmo de Prim, mas antes de executar o algoritmo
propriamente dito, você deve verificar se o grafo cumpre as condições
necessárias.

## Ferramentas utilizadas
+ Eclipse (Versão 2022-03)
+ + Pode ser obtido no link: https://www.eclipse.org/downloads/
+ Java 11
+ + Pode ser obtido no link: https://www.oracle.com/java/technologies/downloads/
+ + Obs: Se você usa a melhor distribuição linux existente (Linux Mint) ele já vem instalado por padrão.

## Etapa atual
Interface para o algoritmo de kruskal implementado, abaixo algumas imagens:
![](https://i.imgur.com/rvkgC1G.png)
Rótulo: Detectou que dois vértices estão no mesmo conjunto.


![](https://i.imgur.com/aNocisE.png)
Rótulo: Finalizou todos os passos do algoritmo de Kruskal

+ Ainda falta fazer funcionar a interface para o algoritmo de Prim.
+ Falta adicionar um label onde "calcula" a complexidade até o momento.
+ Falta uma parte onde fica o código, e informa que aquele trecho X está executando.

## Abrir o projeto
Siga os passos:

+ Passo 1:
  
  ![](https://i.imgur.com/8ALTerU.png)

+ Passo 2: Você deve clicar em "Open projects from FileSystem"

+ Passo 3: Clica em "Directory"
  
    ![](https://i.imgur.com/PBYEgJs.png)

+ Passo 4: Seleciona a pasta que você baixou deste github
    ![](https://i.imgur.com/ZQehhqQ.png)

+ Passo 5: Depois de selecionado a pasta, clique em "Finish"
    ![](https://i.imgur.com/w7cmjJW.png)


+ Passo 6: Agora o projeto já aparece no Eclipse e você poderá modificar
    ![](https://i.imgur.com/4Khivq0.png)


## Problemas conhecidos
Em alguns casos ao tentar abrir o projeto, ocorrerá falhas em todas as linhas, como no exemplo abaixo:
![](https://i.imgur.com/fUaylnx.png)

Este erro ocorre por uma má configuração do projeto no eclipse, para corrigir este problema é simples, basta seguir os passos abaixo:
+ Passo 1: Clique no triâgulo ao lado do botão de executar:
![](https://i.imgur.com/HQRnO8K.png)

+ Passo 2: Clique na opção "Run Configurations".

+ Passo 3: Esta tela irá se abrir. Você deverá clicar na opção "JRE", como mostrado na imagem abaixo.
![](https://i.imgur.com/XcdO7f4.png)

+ Passo 4: Altere o JRE padrão para esta opção selecionada na imagem de baixo:
![](https://i.imgur.com/XWjif2U.png)

+ Passo 5: Clique em "Apply", e em seguida em "Run". Como mostrado abaixo:
![](https://i.imgur.com/zbJitT5.png)







  
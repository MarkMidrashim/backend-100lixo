Coleta Seletiva
===============

> APIs 

## Índice
* [Intuíto](#intuito)
* [Tecnologias](#tecnologias)
* [Instalação](#instalacao)
* [Execução](#execucao)
* [Estrutura](#estrutura)

### Intuíto:
API responsável por conter os serviços importantes para o sistema de coleta seletiva

### Tecnologias
As tecnologias escolhidas para o projeto são:
* Java 11
* SpringBoot Framework 2.3.9
* Maven
* MySQL 8

### Instalação
> É importante que o ambiente utilizado seja **Linux**

É necessário instalar o [Java 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html), para isso execute o seguinte comando:
```Bash
$ sudo yum install -y java-11-openjdk java-11-openjdk-devel
```

Com a instalação do java concluida é necessário configurar o **PATH** dele, portanto, será necessário adicionar um export para o **JAVA_HOME**.
Vamos inserir a configuração no bashrc do usuário:
```Bash
$ vim ~/.bashrc
```

Depois de acessar o arquivo, basta inserir o seguinte comando no final do arquivo:
```Bash
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk
```

Após salvar a alteração, precisamos aplicar a alteração no ambiente linux, para isso execute o comando:
```Bash
$ source ~/.bashrc
```
### Execução


Depois que os serviços forem iniciados, basta acessá-los.

### Estrutura
O projeto deverá ter a seguinte estrutura:

```
```

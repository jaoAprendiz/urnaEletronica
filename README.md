# 🗳️ Projeto Urna Eletrônica em Java

Este projeto implementa uma **simulação de urna eletrônica** utilizando Java, com interface gráfica Swing.  
O sistema permite registrar votos em candidatos, votos em branco e votos nulos, além de exibir informações e foto do candidato durante a votação.

---

## 🚀 Funcionalidades

- ✅ Digitar o número do candidato e confirmar o voto.  
- ✅ Registrar **voto em branco**.  
- ✅ Registrar **voto nulo** ao digitar "00" ou outro número inválido.  
- ✅ Exibir **nome e foto do candidato** durante a votação.  
- ✅ Opção de **corrigir** antes de confirmar.  
- ✅ Registro de todos os votos no modelo `UrnaModel`.  

---

## 🖥️ Estrutura do Projeto

A estrutura de pacotes segue a divisão **MVC (Model-View-Controller)**:

```

src/
├── Controllers/
│    └── UrnaController.java
│
├── Models/
│    └── UrnaModel.java
│
├── View/
│    └── TelaVotacao.java
│
└── Images/
├── 12.jpg
├── 34.jpg
└── ...

```

- **Controllers** → Lógica da votação (confirmar, corrigir, votar em branco).  
- **Models** → Registro e manipulação dos votos.  
- **View** → Interface gráfica (Swing).  
- **Images** → Pasta com fotos dos candidatos (nomeadas pelo número do candidato).  

---

## 📸 Exibição de Fotos

As imagens dos candidatos devem ser salvas na pasta `Images/` dentro do projeto.  
O nome do arquivo deve seguir o **número do candidato**. Exemplo:

```

Images/
├── 12.jpg   → Candidato número 12
├── 34.png   → Candidato número 34
└── ...

````

No código, a imagem é carregada assim:

```java
String caminho = "src/Images/" + numeroCandidato + ".jpg";
ImageIcon foto = new ImageIcon(caminho);
View.telaVotacao.fotoCandidato.setIcon(foto);
````

---

## 🔄 Fluxo da Votação

1. Usuário digita o número do candidato.
2. A urna exibe nome e foto do candidato.
3. Usuário pode:

   * **Confirmar** → Voto é registrado.
   * **Corrigir** → Tela é limpa e imagem removida.
   * **Branco** → Voto registrado como "BRANCO".
   * Digitar **00** → Voto registrado como "NULO".

---

## 🛠️ Tecnologias Utilizadas

* **Java SE 17+**
* **Swing (javax.swing)** para interface gráfica
* **Padrão MVC** para organização do projeto

---

## ▶️ Como Executar

1. Clone este repositório:

   ```bash
   git clone https://github.com/jaoAprendiz/urnaEletronica.git
   cd urna-eletronica-java
   ```

2. Compile o projeto:

   ```bash
   javac src/**/*.java
   ```

3. Execute a aplicação:

   ```bash
   java src/View/TelaVotacao
   ```

---

## 📊 Exemplo de Votos Registrados

O modelo `UrnaModel` armazena os votos. Exemplo de saída esperada:

```
Voto confirmado: 12
Voto em BRANCO confirmado
Voto NULO confirmado
Voto confirmado: 34
```

---

## 📌 Melhorias Futuras

* Geração de relatório final com a contagem dos votos.
* Persistência em arquivo ou banco de dados.
* Interface gráfica mais próxima de uma urna eletrônica real.
* Adição de som de confirmação de voto.

---

## 👨‍💻 Autor

Projeto desenvolvido para fins acadêmicos na disciplina de **Programação Orientada a Objetos**.
Professor: *Fabio Eduardo dos Santos*
Aluno: *João Victor Soave*


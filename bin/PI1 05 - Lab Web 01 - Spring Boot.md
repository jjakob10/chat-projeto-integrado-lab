# Lab Web 01 - Spring Boot

Neste roteiro, vamos criar uma aplicação simples usando Spring Boot: um chat que armazene as conversas apenas temporariamente, em memória.

Ao longo do roteiro, há referências no estilo **(slide X)**, indicando que instruções de como fazer o que está sendo pedido encontram-se no slide número X da apresentação "Reuso de Frameworks", disponível no Classroom.


## Backend e API

1. Crie a aplicação utilizando a [interface Web do Spring Boot](https://start.spring.io/) **(slide 34)**;

2. Pense em uma forma de armazenar as mensagens de chat que serão enviadas à API do backend numa lista. Uma variável global (atributo estático e público) é a solução mais rápida. Uma solução mais desafiadora é aprender sobre o [padrão de projeto Singleton](https://refactoring.guru/pt-br/design-patterns/singleton);

3. Implemente um _endpoint_ da API que receba via POST conteúdo JSON relativo a uma mensagem enviada ao chat **(slides 47-48)** e armazene a mensagem na lista, adicionando à mesma a data/hora local. Não é necessário retornar nada. Exemplo de mensagem:

    ```json
    {
      "remetente": "Vítor",
      "mensagem": "Qual a a resposta para a vida, o universo e tudo mais?"
    }
    ```

4. Para testar, utilize um software como o [Postman](https://www.postman.com/downloads/), [Insomnia](https://insomnia.rest/download) ou [curl](https://curl.se/) para enviar mensagens para o chat e verifique o console;

5. Implemente um outro _endpoint_ da API usando método GET sem nenhum parâmetro/conteúdo e que retorne em formato JSON a lista de mensagens enviadas ao chat até o momento;

6. Novamente, utilize um dos softwares mencionados no passo 4 para testar a API.


## Frontend

7. Na pasta `src/main/resources/static` crie uma página HTML com o seguinte conteúdo:

    ```html
    <!DOCTYPE html>
    <html><head>
      <meta charset="UTF-8" />
      <title>Chat</title>
      <script>
        function enviarMensagem() {
          let remetente = document.getElementById('remetente').value;
          let mensagem = document.getElementById('mensagem').value;
          var xhr = new XMLHttpRequest();
          xhr.open("POST", "http://localhost:8080/chat", true);
          xhr.setRequestHeader("Content-Type", "application/json");
          xhr.onreadystatechange = function () {
              if (xhr.readyState === 4 && xhr.status === 200) {
                atualizarChat();
              }
          };
          var data = JSON.stringify({"remetente": remetente, "mensagem": mensagem});
          xhr.send(data);
        }

        function atualizarChat() {
          var xhr = new XMLHttpRequest();
          xhr.open("GET", "http://localhost:8080/chat", true);
          xhr.setRequestHeader("Content-Type", "application/json");
          xhr.onreadystatechange = function () {
              if (xhr.readyState === 4 && xhr.status === 200) {
                  var json = JSON.parse(xhr.response);
                  var chat = "";
                  for (var i = 0; i < json.length; i++) {
                    chat += `[${json[i].horario}] ${json[i].remetente}: ${json[i].mensagem}<br />`;
                  }
                  document.getElementById('chat').innerHTML = chat;
              }
          };
          xhr.send();			
        }
      </script>
    </head>
    <body>
      <h1>Enviar</h1>
      <p>Remetente: <input id="remetente" type="text" /></p>
      <p>Mensagem: <input id="mensagem" type="text" /></p>
      <button type="button" onclick="enviarMensagem()">
        Enviar!
      </button>

      <hr />

      <h1>Chat</h1>

      <button type="button" onclick="atualizarChat()">
        Atualizar!
      </button>

      <div id="chat"></div>
    </body></html>
    ```

8. Observe que a URL `http://localhost:8080/chat` foi usada no script, tanto para o envio de uma nova mensagem via POST quanto para a obtenção das mensagens já enviadas via GET. Ajuste para as URLs dos _endpoints_ que você implementou no backend.


## Desafios

- Melhorar o _design_ da página utilizando CSS;

- Explorar frameworks JavaScript como [JQuery](https://jquery.com/), [Angular](https://angular.io/), [React](https://react.dev/), [Vue.js](https://vuejs.org/), [Svelte](https://svelte.dev/), etc. para ajudar a fazer o frontend que consulte esta mesma API.
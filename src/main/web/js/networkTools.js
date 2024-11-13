/**
Usamos o objeto XMLHttpRequest para processar requisições GET no endpoint /network/add exposto
pelo adaptador REST no aplicativo hexagonal. É um código JavaScript curto que captura os valores
inseridos no formulário HTML, os processa e, em seguida, mostra uma mensagem de sucesso se
tudo der certo ou uma mensagem de erro se não

*/


function addNetworkToRouter() {
    const routerId = document.getElementById("routerId").value;
    const address = document.getElementById("address").value;
    const name = document.getElementById("name").value;
    const cidr = document.getElementById("cidr").value;
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/network/add?routerId="+routerId+"&"+
        "address="+address+"&"+
        "name="+name+"&"+
        "cidr="+cidr, true);
    xhttp.onload = function () {
        if (xhttp.status === 200) {
            document.getElementById("message").innerHTML = "Network added with success!"
        } else{
            document.getElementById("message").innerHTML = "An error occurred while trying to add the network."
        }
    };
    xhttp.send();

}

/**
Para criar uma visualização gráfica baseada em JSON do roteador e suas redes, usaremos uma
biblioteca JavaScript chamada D3 que consome os dados JSON e produz a visualização gráfica. O
código JavaScript processa o formulário e, em seguida, usa a resposta JSON com as bibliotecas D3
Aqui, estamos passando o endpoint /network/get definido anteriormente no aplicativo hexagonal.
A função getRouter processa as solicitações GET e usa a resposta JSON como
parâmetro para a função createTree que construirá a visualização gráfica da rede.

*/

function getRouter() {
    const routerId = document.getElementById("routerId").value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        console.log(this.responseText);
        if (this.readyState == 4 && this.status == 200) {
            const json = JSON.parse(this.responseText)
            createTree(json)
        }
    };
    xhttp.open("GET", "http://localhost:8080/network/get?routerId="+routerId, true);
    xhttp.send();
}

function createTree(json) {
    const container = document.getElementById("container");
    const vt = new VTree(container);
    const reader = new VTree.reader.Object();

    var data = reader.read(json);
    vt.data(data).update();
}
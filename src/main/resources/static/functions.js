function salvarUsuario(){
    var id = $("#idForm").val();
    var name = $("#nameForm").val();
    var age = $("#ageForm").val();

    if(name == null || name != null && name.trim() == ''){
        $("#nameForm").focus();
        alert('Informe o nome');
        return;
    }

    if(age == null || age != null && age.trim() == ''){
        $("#ageForm").focus();
        alert('Informe a idade');
        return;
    }

    $.ajax({
        method: "POST",
        url: "salvar",
        data: JSON.stringify({
            id : id,
            nome: name,
            idade: age
        }),
        contentType: "application/json; charset=utf-8",
        success : function (response) {

            $("#id").val(response.id);
            alert("Salvo com sucesso!");
        }
    }).fail(function (xhr, status, errorThrown){ //xrh - texto da resposta
        alert("Erro ao salvar: " + xhr.responseText);
    });

}

function pesquisaUser(){
    var name = $("#nameBusca").val();

    if(name == null || name.trim() == ''){
        alert("Digite o nome de usuário para busca!");
    }

    if (name != null && name.trim() != ''){
        $.ajax({
            method: "GET",
            url: "buscarpornome",
            data: "name= " + name,
            success : function (response) {
                $('#tabelaResultados > tbody > tr').remove();

                for(var i = 0; i < response.length; i++){
                    $('#tabelaResultados > tbody').append(
                        '<tr id="'+response[i].id+'"> ' +
                        '<td>'+response[i].id+'</td> ' +
                        '<td>'+response[i].nome+'</td> ' +
                        '<td><button type="button" class="btn btn-outline-info" onclick="colocarEmEdicao('+response[i].id+')">Ver</button></td>' +
                        '<td><button type="button" class="btn btn-danger" onclick="excluirUsuario('+response[i].id+')">Excluir</button></td>' +
                        '</tr>')
                }
            }
        }).fail(function (xhr, status, errorThrown){ //xrh - texto da resposta
            alert("Erro ao buscar: " + xhr.responseText);
        });
    }
}

function listarTodos(){

    $.ajax({
        method: "GET",
        url: "listatodos",
        success : function (response) {
            $('#tabelaResultados > tbody > tr').remove();

            for(var i = 0; i < response.length; i++){
                $('#tabelaResultados > tbody').append(
                    '<tr id="'+response[i].id+'"> ' +
                    '<td>'+response[i].id+'</td> ' +
                    '<td>'+response[i].nome+'</td> ' +
                    '<td><button type="button" class="btn btn-outline-info" onclick="colocarEmEdicao('+response[i].id+')">Ver</button></td>' +
                    '<td><button type="button" class="btn btn-danger" onclick="excluirUsuario('+response[i].id+')">Excluir</button></td>' +
                    '</tr>')
            }
        }
    }).fail(function (xhr, status, errorThrown){ //xrh - texto da resposta
        alert("Erro ao buscar: " + xhr.responseText);
    });

}

function colocarEmEdicao(id){
    $.ajax({
        method: "GET",
        url: "buscaruserid",
        data: "idUser= " + id,
        success : function (response) {
            $("#idForm").val(response.id);
            $("#nameForm").val(response.nome);
            $("#ageForm").val(response.idade);

            $('#modalPesquisaUser').modal('hide');
        }
    }).fail(function (xhr, status, errorThrown){ //xrh - texto da resposta
        alert("Erro ao buscar usuário por id: " + xhr.responseText);
    });
}

function excluirUsuario(id){

    if(confirm("Deseja realmente deletar?")) {

        $.ajax({
            method: "DELETE",
            url: "delete",
            data: "idUser= " + id,
            success : function (response) {
                $('#' + id).remove();

                alert(response);
                document.getElementById('formCadastroUser').reset();
            }
        }).fail(function (xhr, status, errorThrown){ //xrh - texto da resposta
            alert("Erro ao deletar usuário: Pesquise pelo seu ID em 'Pesquisar'");
        });
    }
}

function botaoExcluirTela(){
    var id = $("#idForm").val();

    if(id != null) {
        var id = $('#idForm').val();
        excluirUsuario(id);
        document.getElementById('formCadastroUser').reset();
    }
}
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8">
	<title>Lista de Autores</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h2 class="text-center">Cadastro de Autor</h2>
		<form method="post" action="<c:url value="/autores"/>" class="needs-validation" novalidate>
			<div class="form-group">
				<label>Nome</label>
				<input name="nome" class="form-control" required>
			</div>
			
			<div class="form-group">
				<label>Email</label>
				<input name="email" class="form-control" type="email" placeholder="nome@exemplo.com" required>
			</div>
			
			<div class="form-group">
				<label>Data de Nascimento(dd/mm/yyyy)</label>
				<input name="data_nascimento" class="form-control" required>
			</div>
			
			<div class="form-group">
				<label>Mini currículo (resume)</label>
				<input name="resume" class="form-control" required>
			</div>
			
			<input type="submit" value="Salvar" class="btn btn-primary mt-1">
		</form>
		
		<h2 class="text-center">Lista de Autores</h2>
	
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>NOME</th>
					<th>EMAIL</th>
					<th>DATA DE NASCIMENTO</th>
					<th>MINI CURRÍCULO (resume)</th>
			</thead>
			<tbody>
				<c:forEach var="autor" items="${autores}">
					<tr>
						<td>${autor.nome}</td>
						<td>${autor.email}</td>
						<td>${autor.data_nascimento}</td>
						<td>${autor.resume}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<script>
// Exemplo de JavaScript inicial para desativar envios de formulário, se houver campos inválidos.
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Pega todos os formulários que nós queremos aplicar estilos de validação Bootstrap personalizados.
    var forms = document.getElementsByClassName('needs-validation');
    // Faz um loop neles e evita o envio
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
</script>
</body>
</html>

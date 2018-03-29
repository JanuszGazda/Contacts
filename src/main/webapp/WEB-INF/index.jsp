<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache"> 
    <meta http-equiv="Cache-Control" content="no-cache"> 
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
    
    <title>Contacts | Home</title>
     <link href="static/css/style.css" rel="stylesheet">

</head>
<body>

	<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="/" class="navbar-brand">Hello</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="newPerson">Nowy kontakt</a></li>
					<li><a href="all">Kontakty</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	<c:choose>
		<c:when test="${mode == 'MODE_HOME'}">
			<div class="container" id="homeDiv">
				<div class="jumbotron text-center">
					<h1>Witaj w kontaktach</h1>
				</div>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_ALL'}">
			<div class="container text-center" id="tasksDiv">
				<h3>Moje kontakty</h3>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered text-left">
						<thead>
							<tr>
								<th>Imie</th>
								<th>Nazwisko</th>
								<th>Plec</th>
								<th>Data urodzenia</th>
								<th>Pesel</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="person" items="${people}">
								<tr>
									<td>${person.name}</td>
									<td>${person.surname}</td>
									<td>
										<c:choose>
											<c:when test="${person.sex == true}">Kobieta</c:when>
											<c:when test="${person.sex == false}">Mezczyzna</c:when>
										</c:choose>
									</td>
									<td>${person.date}</td>
									<td>${person.pesel}</td>
									<td><a href="updatePerson?id=${person.pesel}"><span class="glyphicon glyphicon-pencil"></span></a></td>
									<td><a href="deletePerson?id=${person.pesel}"><span class="glyphicon glyphicon-trash"></span></a></td>
									<tr>
										<td></td>
									</tr>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_NEW' || mode == 'MODE_UPDATE'}">
			<div class="container text-center">
				<h3>Zarzadzaj</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="savePerson">
					<input type="hidden" name="id" value="${person.pesel}"/>
					<div class="form-group">
						<label class="control-label col-md-3">Imie</label>
						<div class="col-md-7">
							<input type="text" required="required" pattern="[A-Za-z]+{,25}" class="form-control" name="name" value="${person.name}"/>
						</div>				
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Nazwisko</label>
						<div class="col-md-7">
							<input type="text" required="required" pattern="[A-Za-z]+{,25}" class="form-control" name="surname" value="${person.surname}"/>
						</div>				
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Plec</label>
						<div class="col-md-7">
							<input type="radio" class="col-sm-1" name="sex" value="true" required/>
							<div class="col-sm-1">K</div>
							<input type="radio" class="col-sm-1" name="sex" value="false"/>
							<div class="col-sm-1">M</div>
						</div>				
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Data urodzenia</label>
						<div class="col-md-7">
							<input type="text" required="required" placeholder="YYYY-MM-DD" class="form-control" name="date"
								   <c:choose>
								   		<c:when test="${mode == 'MODE_NEW'}">value="<fmt:formatDate value="${person.date}" pattern="yyyy-MM-dd" />"</c:when>
								   		<c:when test="${mode == 'MODE_UPDATE'}"> value="${person.date}"</c:when>
								   </c:choose>
							/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Pesel</label>
						<div class="col-md-7">
							<input type="text" required="required" pattern="[0-9]{11}" class="form-control" name="pesel" value="${person.pesel}"/>
						</div>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-primary" value="Save"/>
					</div>
				</form>
			</div>
		</c:when>		
	</c:choose>

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>

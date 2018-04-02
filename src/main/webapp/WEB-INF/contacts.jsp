<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">

    <title>Lista kontaktów</title>
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
    <c:when test="${mode == 'MODE_ALL_CONTACTS'}">
        <div class="container text-center" id="tasksDiv">
            <h3>Twoj kontakt</h3>
            <hr>
            <div class="table-responsive">
                <table class="table table-striped table-bordered text-left">
                    <thead>
                    <tr>
                        <th>Kontakt</th>
                        <th></th>
                        <th><a href="newContact?personId=${personId}">New</a></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="contact"  items="${contacts}">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${contact.type == 1}">E-mail: ${contact.value}</c:when>
                                    <c:when test="${contact.type == 2}">Telefon: ${contact.value}</c:when>
                                    <c:when test="${contact.type == 3}">Adres zamieszkania: ${contact.value}</c:when>
                                </c:choose>
                            </td>
                            <td><a href="updateContact?contactId=${contact.id}&personId=${contact.person.pesel}"><span class="glyphicon glyphicon-pencil"></span></a></td>
                            <td><a href="deleteContact?contactId=${contact.id}&personId=${contact.person.pesel}"><span class="glyphicon glyphicon-trash"></span></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:when>
    <c:when test="${mode == 'MODE_NEW_CONTACT' || mode == 'MODE_UPDATE_CONTACT'}">
        <div class="container text-center">
            <h3>Zarzadzaj</h3>
            <hr>
            <form class="form-horizontal" method="POST" action="saveContact?personId=${personId}">
                <input type="hidden" name="id" value="${contact.id}"/>
                <div class="form-group">
                    <label class="control-label col-md-3">Wartosc</label>
                    <div class="col-md-7">
                        <input type="text" required="required" pattern="[A-Za-z]+{,25}" class="form-control" name="value" value="${contact.value}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Typ</label>
                    <div class="col-md-7">
                        <input type="radio" class="col-sm-1" name="type" value="1" required/>
                        <div class="col-sm-1">E-mail</div>
                        <input type="radio" class="col-sm-1" name="type" value="2"/>
                        <div class="col-sm-1">Telefon</div>
                        <input type="radio" class="col-sm-1" name="type" value="3"/>
                        <div class="col-sm-1">Adres</div>
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

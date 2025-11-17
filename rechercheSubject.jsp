<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter une matiere</title>
</head>
<body>
<c:out value="${sessionScope.activeUser.name}"/>
<c:out value="${sessionScope.activeUser.FirstName}"/>
<ul>
<c:forEach var="sub" items="${requestScope.listSubjects}">
<c:out value="<li>${sub.subjectTitle} : ${sub.teacher.firstName}${sub.teacher.name}</li>" ></c:out><br/>
</c:forEach>
</ul>
    <form action="Controller">
        <label>Indiquez le titre de la matiere :</label>
        <input type="text" name="intitule" required/>
        <label>Indiquez l'affiliation de l'enseignant :</label>
        <input type="text" name="affiliation" required/>
        <input type="submit" name="myBtn" value="Rechercher Matieres"></form>
</body>
</html>

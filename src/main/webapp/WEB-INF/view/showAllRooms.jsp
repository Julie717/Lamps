<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
</head>
<body>
<h2>Все комнаты</h2>
<form method="get">
<table>
   <tr>
       <td><c:out value="Наименование комнаты"/></td>
       <td><c:out value="Страна создателя"/></td>
       <td><c:out value="Лампочка"/></td>
   </tr>
     <c:if test="${(building.size()-1)>=0}">
      <c:forEach var = "i" begin = "0" end = "${building.size()-1}">
        <tr>
           <td>
             <a href="/room?roomName=${building.get(i).name}&roomCountry=${building.get(i).country}"
             id="roomEnter" onclick="page(${isAvailable.get(i)})">
             <c:out value="${building.get(i).name}"/></a>
           </td>
           <td><c:out value="${building.get(i).country}"/></td>
           <td><c:out value="${building.get(i).switchOn}"/></td>
        </tr>
      </c:forEach>
     </c:if>
</table>
<button formaction="/">Главная страница</button>
<button formaction="/add">Добавить комнату</button>
<input type="hidden" id="clientCountry" value=${clientCountry}/>
<input type="hidden" id="isAvailable" value=${isAvailable}/>
<script type="text/javascript" src="scripts/javascript.js"> </script>
</form>
</body>
</html>
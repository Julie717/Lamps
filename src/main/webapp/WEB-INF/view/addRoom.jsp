<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>Добавление комнаты</h2>
<form method="post" action="add">
    <label>Название комнаты</label>
    <input type="nameRoom" name="nameRoom" required><br />
    <label>Страна</label>
    <select name="country" required>
      <option selected disabled value="">Выберите из списка</option>
        <c:forEach var="country" items="${listCountries}">
             <option>${country}</option>
        </c:forEach>
     </select>
     <p><input type="submit" value="Создать"></p>
 </form>
 <table cellspacing="10" cellpadding="0">
  <tr>
   <td>
     <form action="/">
         <button>Главная страница</button>
     </form>
   </td>
   <td>
     <form action="show">
            <button>Показать все комнаты</button>
     </form>
   </td>
  </tr>
 </table>
</body>
</html>
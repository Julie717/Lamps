<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
</head>
<body>
<h2> Комната ${room.name} </h2>
<form method="post" action="room.jsp">
  <table>
    <tr>
      <td valign="top">
         <fieldset>
           <legend><strong>Лампочка</strong> </legend>
             <p>
               <label>
                   <INPUT TYPE="radio" name="switchLamp" value="On" id="check1" ${room.isSwitchOn() ? "checked":""}
                    />Включить
               </label>
             </p>
             <p>
               <label>
                   <INPUT TYPE="radio" name="switchLamp" value="Off" id="check2" ${room.isSwitchOn() ? "":"checked"}
                   />Выключить
               </label>
             </p>
          </fieldset>
      </td>
      <td>
        <p>
         <img id="kartinka" src=${room.isSwitchOn() ? "images/lampOn.jpg":"images/lampOff.jpg"} alt="Lamp OFF" width="50%" height="50%"/>
        </p>
      </td>
    </tr>
  </table>

</form>
<table cellspacing="10" cellpadding="0">
 <tr>
  <td>
    <form  action="/">
        <button>Главная страница</button>
     </form>
  </td>
  <td>
   <form action="/show">
        <button>Показать все комнаты</button>
    </form>
  </td>
  <td>
     <form action="/add">
          <button>Добавить комнату</button>
      </form>
  </td>
 </tr>
</table>

<input type="hidden" id="name" value=${room.name}>
<input type="hidden" id="country" value=${room.country}>
<script type="text/javascript" src="scripts/javascript.js"> </script>
</body>
</html>

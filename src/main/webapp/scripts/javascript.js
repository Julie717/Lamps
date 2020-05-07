$("#check1, #check2").change(function(){
   var switchOn="false";
              if ($('#check1').is(":checked")) {
                     document.getElementById('kartinka').src = "images/lampOn.jpg";
                     switchOn="true";
                  } else if ($('#check2').is(":checked")) {
                     document.getElementById('kartinka').src = "images/lampOff.jpg";
                     switchOn="false";
                  }
            $.ajax({
                        type: 'GET',
                        url: '/lamp',
                        data: "switchOn=" + switchOn +"&name="+$('#name').val()+"&country="+$('#country').val(),
                    })

        }
 )

function page (data){
 if (data==false){
    alert('Вы не можете войти в эту комнату, т.к. Вы из другой страны');
    event.returnValue = false;
    }
 }
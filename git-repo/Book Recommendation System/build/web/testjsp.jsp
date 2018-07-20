<%--
    Document   : testjsp
    Created on : Mar 20, 2016, 9:12:44 PM
    Author     : Anand Dubey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession sn=request.getSession();
    String res=sn.getAttribute("GetBook").toString();
%>
<html>
    <head
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Recommendation System</title>
        <link href="default1.css" rel="stylesheet" type="text/css" />
    </head>
        <script>
function startTime() {
    var today=new Date();
    var h=today.getHours();
    if(h>12)
      h=h-12;
    var m=today.getMinutes();
    var s=today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    document.getElementById('dtm').innerHTML = today.toDateString()+"   "+h+":"+m+":"+s;
    var t = setTimeout(function(){startTime()},500);
}

function checkTime(i) {
    if (i<10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}

function check()
    {   f=0;
        
        x = document.forms[0].myval1.value;
        y = document.forms[0].myval2.value;
        
        
        if(document.forms[0].c1.checked)
        {
                      
            if(x=="" || x>5|| y=="" || x==0 || y==0)
                {
                   alert("Enter Valid User Rating (1-5) and Quantity");
                    f1=0;
                    
                }
                else
                {
                   var fe1=document.getElementById("buyfm")  ;
                 fe1.action="UserPurchase.jsp";
                 fe1.submit(); 
                }
            
            
        }
                     
        else
            alert("Please Check the box");
       
               
              
            
        }
           
    
 function isNumber(evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
         {
            alert("enter the number") ;
         }

    }

</script>
    
    <body onload="startTime()">
    <form id="buyfm" name="buyfm">
<div id="header">
    <h1 style=" color: #254360">My e-Books</h1>
    <h2 style="color: darkred">Best Book Seller</h2>
    <!--<img src="image/logo2.jpg" width="325">-->
    <div id="dtm" style="text-align: right; font-family: Californian;  font-size: large;color: #254360"></div>
    <br>
    
        <div id="menu">
            <ul>
		               
                <li><a href="Home.jsp" >Home</a></li>
            <li><a href="Search.jsp">Search</a></li>        
          <li><a href="buy.jsp" class="active">Buy</a></li>
            <li><a href="recommend.jsp">Recommendation</a></li>
            <li><a href="index.jsp">Logout</a></li>
	</ul>
             
</div>
</div>
    <br><br>
    <div id="bd">

    <div id="content">
    <br/>
    <h2 style=" color: #254360">Buy Book </h2> <br/>
   <%
                    if(res.equals(""))
                    {%>
                      There is no book  
                    <%}
                    else
                    {
                        String r1[]=res.split("#");
                        
                    %>
                        
                        <div>
                        <table border="1">
                            <th style=" color: #254360"></th>
                            <th style=" color: #254360">Book Id</th>
                            <th style=" color: #254360">Book Name</th>
                            <th style=" color: #254360">Author</th>
                            <th style=" color: #254360">Price</th>
                            <th style=" color: #254360">User Rating</th>
                            <th style=" color: #254360">Quantity</th>
                            
                        
                            
                            <tr>
                                <td> <input type=checkbox name="c1"  id="c1" value=0>  </td>
                                <td><%= r1[0]%></td>
                                <td><%= r1[1]%></td>
                                <td><%= r1[2]%></td>
                                <td><%= r1[3]%></td>
                                 <td> <input type="text" name="myval1" id="myval1" value="" onkeypress="isNumber(event)"></td>
                                 <td> <input type="text" name="myval2" id="myval2" value="" onkeypress="isNumber(event)"></td>
                                
                            </tr>
                            
                            <input type="hidden" id="bid" name="bid" value="<%= r1[0]%>" >
                            <input type="hidden" id="bn" name="bn" value="<%= r1[1]%>" >
                            <input type="hidden" id="ba" name="ba" value="<%= r1[2]%>" >
                            <input type="hidden" id="bp" name="bp" value="<%= r1[3]%>" >
                        
    </table>
                        </div>
    <input type="button" value="Buy" onclick="check()">
                 <%   }
                %>
    
    </div>
    </div>
   
<div id="footer">
	<p>Copyright &copy; 2015 my e-books  All Rights Reserved </p>
</div>
    </form>
</body>
</html>

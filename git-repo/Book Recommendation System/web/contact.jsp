<%-- 
    Document   : contact
    Created on : Dec 15, 2014, 1:36:44 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
</script>
<body onload="startTime()">
<div id="header">
    <h1 style=" color: #254360">My e-Books</h1>
    <h2 style="color: darkred">Best Book Seller</h2>
    <!--<img src="image/logo2.jpg" width="325">-->
    <div id="dtm" style="text-align: right; font-family: Californian;  font-size: large;color: #254360"></div>
    <br>
    
        <div id="menu">
            <ul>
		               
                <li><a href="index.jsp">Home</a></li>
            <li><a href="about.jsp">About Us</a></li>        
            <li><a href="contact.jsp" class="active">Contact Us</a></li>
          <li><a href="#">Login</a>
            <ul>
              <li><a href="login.jsp">Login</a></li>
              <li><a href="register.jsp">Register</a>
                
                </ul>
             </li>
          
	</ul>
             
</div>
</div>
    <br><br>
    <div id="bd">

    <div id="content">
    <br/>
    <h2 style=" color: #254360">Welcome to my e-Books</h2> <br/>
		For inquires or comments regarding our website:<br>
                Mail:<br>
                My e-Books<br>
                Sales and Support<br>
                P.O. Box 7889<br>
                Mumbai.<br><br>

Email:<br>
feedback@myebooks.com<br>
<br/> <br/>
	</div>
    </div>
<div id="footer">
	<p>Copyright &copy; 2015 my e-books  All Rights Reserved </p>
</div>
        
</body>
</html>


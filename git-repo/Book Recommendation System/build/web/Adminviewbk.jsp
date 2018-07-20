<%-- 
    Document   : Adminviewbk
    Created on : Dec 29, 2014, 11:47:53 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*"%>
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
		               
                <li><a href="AdminHome.jsp">Home</a></li>
            <li><a href="Adminaddbk.jsp">Add Books</a></li>        
            <li><a href="Adminviewbk.jsp" class="active">View Books</a></li>
            <li><a href="Adminrecomm.jsp">Recommendation</a></li>
          <li><a href="index.jsp">LogOut</a></li>
          
          
	</ul>
             
</div>
</div>
    <br><br>
    <div id="bd">

    <div id="content">
    <br/>
    <h2 style=" color: #254360">Welcome to My e-Books</h2> <br/>
	
    <b>VIEW BOOK DETAILS</b>
    <br><br><br>
    <div class="scrollit">
    <table border="1">
        <th style=" color: #254360">Book Id</th>
        <th style=" color: #254360">Book Name</th>
        <th style=" color: #254360">Category Id</th>
        <th style=" color: #254360">Sub-Category Id</th>
        <th style=" color: #254360">Author</th>
        <th style=" color: #254360">Publisher</th>
        <th style=" color: #254360">Price</th>
        <th style=" color: #254360">Edition</th>
            
    <%
        BRS_Servlet.DBConnection db=new BRS_Servlet.DBConnection();
        Statement st=db.stt;
        ResultSet rs=st.executeQuery("select * from book");
        while(rs.next())
        {%>
        <tr>
            <td><%= rs.getString(1)%></td>
            <td><%= rs.getString(2)%></td>
            <td><%= rs.getString(3)%></td>
            <td><%= rs.getString(4)%></td>
            <td><%= rs.getString(5)%></td>
            <td><%= rs.getString(6)%></td>
            <td><%= rs.getString(7)%></td>
            <td><%= rs.getString(8)%></td>
        </tr>  
        <%}
    %>
    </table>
    </div>
<br/> <br/>
	</div>
    </div>
<div id="footer">
	<p>Copyright &copy; 2015 my e-books  All Rights Reserved </p>
</div>
        
</body>
</html>


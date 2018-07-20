<%-- 
    Document   : UserPurchase
    Created on : Jan 24, 2015, 12:58:39 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*, java.util.Date"%>
<!DOCTYPE html>
<%
    HttpSession sn=request.getSession();
    String username=sn.getAttribute("UserName").toString();
%>
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
		               
                <li><a href="Home.jsp">Home</a></li>
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
    <h2 style=" color: #254360">Purchase Info</h2> <br/>

    <%
        String s1[]=request.getParameterValues("c1");
        String s2[]=request.getParameterValues("bid");
    String s3[]=request.getParameterValues("bn");
    String s4[]=request.getParameterValues("ba");
    String s5[]=request.getParameterValues("bp");
   
    String s6[]=request.getParameterValues("myval1");
    String s7[]=request.getParameterValues("myval2");

    System.out.println("length : "+s1.length+" : "+s2.length+" : "+s3.length+" : "+s4.length+" : "+s5.length);
    int fno;
    String drs="";
    int amt=0;
    
    BRS_Servlet.DBConnection db=new BRS_Servlet.DBConnection();
    Statement st=db.stt;
    Date dt=new Date();
    int day=dt.getDate();
    int mon=dt.getMonth()+1;
    int yr=dt.getYear()+1900;
    
    String dts=day+"-"+mon+"-"+yr;
    
    for(int i=0;i<s6.length;i++)
    {
	if(i<s1.length)
	{
     	   fno=Integer.parseInt(s1[i]);

          amt=amt+(Integer.parseInt(s5[fno].trim())*Integer.parseInt(s7[fno].trim()));
           int t1=(Integer.parseInt(s5[fno].trim())*Integer.parseInt(s7[fno].trim()));
           drs=drs+s2[fno]+"#"+s3[fno]+"#"+s4[fno]+"#"+s5[fno]+"#"+s6[fno]+"#"+s7[fno]+"#"+t1+"@";
           st.executeUpdate("insert into purchase values('"+username+"','"+dts+"','"+s2[fno]+"','"+s6[fno]+"','"+s7[fno]+"','"+t1+"')");
             fno--;
         }
     }
    System.out.println("drs "+drs);
    String r1[]=drs.split("@");
    %>
    
    <table border="1">
        <th style=" color: #254360">Book Id</th>
        <th style=" color: #254360">Book Name</th>
        <th style=" color: #254360">Author</th>
        <th style=" color: #254360">Price</th>
        <th style=" color: #254360">User Rating</th>
        <th style=" color: #254360">Quantity</th>
        <th style=" color: #254360">Amount</th>
        <%
            for(int i=0;i<r1.length;i++)
            {
                String r2[]=r1[i].split("#");%>
                <tr>
                    <td><%= r2[0]%></td>
                    <td><%= r2[1]%></td>
                    <td><%= r2[2]%></td>
                    <td><%= r2[3]%></td>
                    <td><%= r2[4]%></td>
                    <td><%= r2[5]%></td>
                    <td><%= r2[6]%></td>
                </tr>
                
            <%}
        %>
        
    </table>
        <br><br>
        <center>Total Amount is <%= amt%></center>
    
<br/> <br/>
	</div>
    </div>
<div id="footer">
	<p>Copyright &copy; 2015 my e-books  All Rights Reserved </p>
</div>
        
</body>
</html>


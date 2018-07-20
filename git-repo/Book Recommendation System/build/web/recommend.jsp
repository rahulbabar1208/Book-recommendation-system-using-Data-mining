<%-- 
    Document   : recommend
    Created on : Jan 24, 2015, 10:33:31 AM
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
    <form action="./UserGetBook1">
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
          <li><a href="buy.jsp">Buy</a></li>
            <li><a href="recommend.jsp" class="active">Recommendation</a></li>
            <li><a href="index.jsp">Logout</a></li>
	</ul>
             
</div>
</div>
    <br><br>
    <div id="bd">

    <div id="content">
    <br/>
    <h2 style=" color: #254360">Recommended Book</h2> <br/>
		 <%
        BRS_Servlet.DBConnection db=new BRS_Servlet.DBConnection();
        Statement st=db.stt;
        
        %>
        
       
        
        
        
        
        
        
        
        <%
        
        
        st.executeUpdate("delete from sbook");
        ResultSet rs1=st.executeQuery("select dept from cuser");
        rs1.next();
        
        String s1=(String)rs1.getString(1);
        rs1.close();
        
        
        
        
        int k=1;
        int i;
        int j;
        for(i=0;i<k;i++)
        {
        rs1=st.executeQuery("select * from recommend");
        //ResultSet rst2=st.executeQuery("select * from cuser");
        
        for(j=0;j<k;j++)
        {
            rs1.next();
        }
                
        String s2=rs1.getString(1);
        rs1.close();
        

         rs1=st.executeQuery("select CateId from book where BookId='"+s2+"' ");
         rs1.next();
         String s3=rs1.getString(1);
         rs1.close();
         
         
         
         if(s1.equals(s3))
         {
             rs1=st.executeQuery("select * from book where BookId='"+s2+"'");
             rs1.next();
             String s4=rs1.getString(1);
             String s5=rs1.getString(2);
             String s6=rs1.getString(3);
             String s7=rs1.getString(4);
             String s8=rs1.getString(5);
             String s9=rs1.getString(6);
             String s10=rs1.getString(7);
             rs1.close();
             st.executeUpdate("insert into sbook values('"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"','"+s10+"')");
             
        
         }
         
         
         
         rs1=st.executeQuery("select * from recommend");
       
        
        for(j=0;j<k;j++)
        {
            rs1.next();
        }
         
         if(rs1.next())
         {
            k++ ;     
            rs1.close();
         }
          
        }
        
        
        rs1=st.executeQuery("select * from sbook");

    %>
    
    
    <% if(rs1.next())
    { 
    rs1=st.executeQuery("select * from sbook");
    %>
        
    <center>
    <table>
        <th style=" color: #254360"><center> Book Id&nbsp;&nbsp;</center></th>
        <th style=" color: #254360"><center> &nbsp;&nbsp;Book Name&nbsp;&nbsp;</center></th>
        <th style=" color: #254360"><center> &nbsp;&nbsp;Category&nbsp;&nbsp;</center></th>
        <th style=" color: #254360"><center> Sub Category&nbsp;&nbsp;</center></th>
        <th style=" color: #254360"><center> &nbsp;&nbsp;Author&nbsp;&nbsp;</center></th>
        <th style=" color: #254360"><center> &nbsp;&nbsp;Publisher&nbsp;&nbsp;</center></th>
        <th style=" color: #254360"><center> &nbsp;&nbsp;Price&nbsp;&nbsp;</center></th>
        <th style=" color: #254360"><center> Click To Buy</center></th>




    
        <%
                        while(rs1.next())
                        {
                            
                    %>
                            <tr>
                                <td><center><%= rs1.getString(1)%></center> </td>
                                <td><center><%= rs1.getString(2)%></center> </td>
                                <td><center><%= rs1.getString(3)%></center> </td>
                                <td><center><%= rs1.getString(4)%></center> </td>
                                <td><center><%= rs1.getString(5)%></center> </td>
                                <td><center><%= rs1.getString(6)%></center> </td>
                                <td><center><%= rs1.getString(7)%></center> </td>
                                <td><input type="submit" value="<%= rs1.getString(1)%>" name="id" style="height:30px; width:100px"></td>
                                
                            </tr>
                        <%}
                    %>
                    
                    
                            
                
    </table>

        </center> 
           
                   <% } %>
    
    <center>
    
        
                    
                    
                    
                
           <br>
                     </center>
    
<br/> <br/>
	</div>
    </div>
<div id="footer">
	<p>Copyright &copy; 2015 my e-books  All Rights Reserved </p>
</div>
    </form>     
</body>
</html>


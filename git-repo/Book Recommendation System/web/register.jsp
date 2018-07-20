<%-- 
    Document   : register
    Created on : Dec 15, 2014, 1:54:58 PM
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
     <script>
        function check()
        {
            var n1=document.getElementById("uname").value;
            var n2=document.getElementById("pass1").value;
            var n3=document.getElementById("pass2").value;
            var n4=document.getElementById("email").value;
            var n5=document.getElementById("mno").value;
            var ad=document.getElementById("adr").value;
            var dpt=document.getElementById("dpt").value;
          

            
            
            if(n1=="" || n2=="" || n3=="" || n4=="" || n5=="" || dpt=="" || ad=="")
            {
                alert("Enter all Details");                
            }
            else
            {
                if(n2==n3)
                {
                    if(isNaN(n5) || n5.length!=10)
                    {
                        alert("Enter valid mobile no ");                  
                    }
                    else
                    {
                        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                        if(re.test(n4))
                        {
                            var ff=document.getElementById("frm2");        
                            ff.action="./Register";
                            ff.submit();
                        }
                        else
                        {
                            alert("Enter valid email id");
                        }
                    }                          
                   
                }
                else
                {
                    alert("Password Not Match");
                }
                
             }
            
        }
        
       
            
        
        </script>
        <form id="frm2">
<div id="header">
    <h1 style=" color: #254360">My e-Books</h1>
    <h2 style="color: darkred">Best Book Seller</h2>
    <!--<img src="image/logo2.jpg" width="325">-->
    <div id="dtm" style="text-align: right; font-family: Californian;  font-size: large;color: #254360"></div>
    <br>
    
        <div id="menu">
            <ul>
		               
                <li><a href="index.jsp" >Home</a></li>
            <li><a href="about.jsp">About Us</a></li>        
          <li><a href="contact.jsp">Contact Us</a></li>
          <li><a href="#" class="active">Login</a>
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
    <h2 style=" color: #254360">Welcome to My e-Books</h2> <br/>
    <table>
        	 <tr>
                <td> UserName</td> 
                <td> <input type="text" id="uname" name="uname"></td> 
            </tr>
              <tr>
                <td> Password</td> 
                <td> <input type="password" id="pass1" name="pass1"></td> 
            </tr>
            <tr>
                <td> ReEnter Password</td> 
                <td> <input type="password" id="pass2" name="pass2"></td> 
            </tr>
            
            <tr>
                <td> Gender</td> 
                <td> <input type="radio" id="gen" name="gen"  checked="true" value="male">Male<br>
                <input type="radio" id="gen" name="gen" value="female">Female</td> 
            </tr>
            <tr>
                <td> Email</td> 
                <td> <input type="text" id="email" name="email"></td> 
            </tr>
            <tr>
                <td> Mobile No</td> 
                <td> <input type="text" id="mno" name="mno"></td> 
            </tr>
            
            <tr>
                <td> Department</td> 
                <td> <select id="dpt" name="dpt">
                        <option>Computer</option>
                        <option>Mechanical</option>
                    </select>
                    
            </tr>
            
            
            <tr>
                <td> Address</td> 
                <td> <input type="text" id="adr" name="adr"></td> 
            </tr>
            
             
            
            <tr>
                
               
                
                <td></td>
                <td> <input type="button" value="Register" onclick="check()"></td> 
            </tr>
            </table>
<br/> <br/>
	</div>
    </div>
<div id="footer">
	<p>Copyright &copy; 2015 my e-books  All Rights Reserved </p>
</div>
        </form>
</body>
</html>


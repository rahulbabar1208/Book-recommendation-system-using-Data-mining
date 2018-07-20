/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BRS_Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Statement;
import java.sql.ResultSet;
        
/**
 *
 * @author admin
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try 
        {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session=request.getSession();
             
            
            String name=request.getParameter("uname");
            String pass=request.getParameter("pass");
            if(name.equals("Admin") && pass.equals("Admin"))
            {
                response.sendRedirect("AdminHome.jsp");
            }
            else
            {
                DBConnection db=new DBConnection();
                Statement st=db.stt;
                st.executeUpdate("delete from cuser");

                ResultSet rs=st.executeQuery("select * from login where UserName='"+name+"' and Password='"+pass+"'");
                
                //ResultSet ns=st.executeQuery("select * from login where UserName='"+name+"'");
                
                //String s=rs.getString(6);
               
                
                if(rs.next())
                {
                    session.setAttribute("UserName", name); 
                    
                    st.executeUpdate("insert into cuser values('"+name+"','"+rs.getString(6)+"')");
                    response.sendRedirect("Home.jsp");
                    
                }
                else
                    response.sendRedirect("index.jsp");
            }
                    
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

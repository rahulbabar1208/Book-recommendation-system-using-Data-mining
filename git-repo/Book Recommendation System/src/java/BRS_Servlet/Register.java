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
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;
/**
 *
 * @author admin
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

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
           
            String name=request.getParameter("uname");
            String pass=request.getParameter("pass1");
            String gen=request.getParameter("gen");
            String email=request.getParameter("email");
            String mno=request.getParameter("mno");
            String address=request.getParameter("adr");
            String dpt=request.getParameter("dpt");


            DBConnection db=new DBConnection();
            Statement st=db.stt;
            ResultSet rs1=st.executeQuery("select * from login where UserName='"+name+"'");
            if(rs1.next())
            {
                response.sendRedirect("Register.jsp");
            }
            else
            {
                         HttpSession session=request.getSession();
                         session.setAttribute("UserName", name); 
           
                st.executeUpdate("insert into login values('"+name+"','"+pass+"','"+gen+"','"+email+"','"+mno+"','"+dpt+"','"+address+"')");
                 
                st.executeUpdate("delete from cuser");
                st.executeUpdate("insert into cuser values('"+name+"','"+dpt+"')");
                response.sendRedirect("Home.jsp");
                    
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

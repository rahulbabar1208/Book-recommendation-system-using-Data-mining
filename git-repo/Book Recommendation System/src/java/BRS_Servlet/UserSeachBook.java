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
@WebServlet(name = "UserSeachBook", urlPatterns = {"/UserSeachBook"})
public class UserSeachBook extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try
        {
            /* TODO output your page here. You may use following sample code. */
            String sr=request.getParameter("sr1");
            System.out.println("search "+sr);
            DBConnection db=new DBConnection();
            Statement st=db.stt;
            
            
            
            
            ResultSet rs=st.executeQuery("select * from book");
            String res="";
            while(rs.next())
            {
                String bid=rs.getString(1);
                String bname=rs.getString(2);
                String cate=rs.getString(3);
                String sub=rs.getString(4);
                String aut=rs.getString(5);
                String pub=rs.getString(6);
                String pr=rs.getString(7);
                String ed=rs.getString(8);
                
                String s1=bid+"#"+bname+"#"+cate+"#"+sub+"#"+aut+"#"+pub+"#"+pr+"#"+ed;
                String s2=s1.toLowerCase();
                if(s2.contains(sr.toLowerCase()))
                    res=res+s1+"@";
            }
            System.out.println(res);
            HttpSession sn=request.getSession();
            sn.setAttribute("SearchRes", res);
            response.sendRedirect("SearchResult.jsp");
            
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

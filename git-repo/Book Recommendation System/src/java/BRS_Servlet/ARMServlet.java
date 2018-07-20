/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BRS_Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author admin
 */
@WebServlet(name = "ARMServlet", urlPatterns = {"/ARMServlet"})
public class ARMServlet extends HttpServlet {

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
            
            ServletContext sc = this.getServletContext();
            System.out.println("sc "+sc.getContextPath());
            System.out.println("rl "+sc.getRealPath("/"));
                
            String sg=sc.getRealPath("/");
            System.out.println("sg "+sg);
            String path=sg.substring(0,sg.indexOf("build"));
            System.out.println("path "+path);
            
            
            ARM am=new ARM(path);
            String trans=am.getTrans();
            path=path+"in1.txt";
            System.out.println("path"+path);
            
            File fe=new File(path);
            System.out.println("FE"+fe);
            FileOutputStream fos=new FileOutputStream(fe);
            fos.write(trans.trim().getBytes());
            
            fos.close();
            
            System.out.println(fe.getAbsolutePath());
            
            am.findrule(fe.getAbsolutePath());
            response.sendRedirect("ARMResult.jsp");
            
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

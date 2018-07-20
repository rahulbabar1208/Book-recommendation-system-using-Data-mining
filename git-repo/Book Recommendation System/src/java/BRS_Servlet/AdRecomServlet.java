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
import java.util.ArrayList;
/**
 *
 * @author admin
 */
@WebServlet(name = "AdRecomServlet", urlPatterns = {"/AdRecomServlet"})
public class AdRecomServlet extends HttpServlet {

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
            DBConnection db=new DBConnection();
            Statement st=db.stt;
            
            st.executeUpdate("delete from recommend");
            
            ResultSet rs=st.executeQuery("select BID from arm");
            ArrayList at1=new ArrayList();
            
            while(rs.next())
            {
                String g1=rs.getString(1);
                if(!g1.contains(","))
                {
                    at1.add(g1);
                }
            }
            ArrayList at2=new ArrayList();
            for(int i=0;i<at1.size();i++)
            {
                String g2=at1.get(i).toString();
                ResultSet rs2=st.executeQuery("select * from userrating where BID='"+g2+"'");
                if(rs2.next())
                {
                    at2.add(rs2.getString(1)+"#"+rs2.getString(2));
                }
            }
            
            String dd[][]=new String[at2.size()][2];
            for(int i=0;i<at2.size();i++)
            {
                String g3[]=at2.get(i).toString().split("#");
                dd[i][0]=g3[0];  // bid
                dd[i][1]=g3[1];  // val
            }
            
            for(int i=0;i<dd.length;i++)
            {
                for(int j=i+1;j<dd.length;j++)
                {
                    if(Double.parseDouble(dd[i][1])<Double.parseDouble(dd[j][1]))
                    {
                        String t1=dd[i][0];
                        dd[i][0]=dd[j][0];
                        dd[j][0]=t1;
                        
                        String t2=dd[i][1];
                        dd[i][1]=dd[j][1];
                        dd[j][1]=t2;
                    }
                }
            }
            for(int i=0;i<dd.length;i++)
            {
                System.out.println(dd[i][0]+" : "+dd[i][1]);
                st.executeUpdate("insert into recommend values('"+dd[i][0]+"')");
            }
            response.sendRedirect("AdRecommResult.jsp");
            
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

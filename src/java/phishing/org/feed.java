/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phishing.org;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author WELCOME
 */
public class feed extends HttpServlet {

   PreparedStatement ps;
   Connection con;
   
   @Override
    public void init(){
        try{
            String qr = "insert into feedback (message) values (?)";
            Class.forName("com.mysql.jdbc.Driver");
           con= DriverManager.getConnection("jdbc:mysql://localhost:3306/adiquery","root","root");   
          ps= con.prepareStatement(qr);
           
           
        }catch(ClassNotFoundException | SQLException ex)
        {}
    }
@Override
    public void destroy(){
        try{
            con.close();
        }catch (Exception ex){}
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
            
            String msg = request.getParameter("message");
            
            String qr = "insert into feedback (message) values (?)";
            try {
                
            ps.setString(1,msg);
            
            ps.executeUpdate();
            PrintWriter exe = response.getWriter();
       //
            
            out.print("<table bgcolor= 'red' align='center' cellpadding='5' cellspacing='5'><tr><td></td><td><a href='feedback.jsp'>BACK</a></td><td></td></table>");
       
       
       exe.println(" Successfully sent");
            
        }catch (Exception ex){
            
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

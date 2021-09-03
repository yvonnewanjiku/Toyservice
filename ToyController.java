/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Toy.ToyClass;
import Toy_Interface.Toy_interf;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author #USER
 */
@WebServlet(name = "ToyController", urlPatterns = {"/ToyController"})
public class ToyController extends HttpServlet {
    String[] names,quantities;
    double[] costs;
    double total = 0.0;
    String delete = "delete";
    String uname = "";
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
        try (PrintWriter out = response.getWriter()) {
           try{
            //if save button in home.jsp is clicked
            if(request.getParameter("save") !=null){
                    //Connection to server and bind with the server ip and port
                    Registry reg = LocateRegistry.getRegistry("127.0.0.1", 5000);
                    //creat object of Toy_interface (i.e vinterface) and bind with the server name
                    Toy_interf vinterface = (Toy_interf)reg.lookup("Server");
                  
                    //get the parameters form home.jsp page 
                    String name = request.getParameter("vegi_name");                  
                    int price = Integer.parseInt(request.getParameter("vegi_price"));
                    uname = request.getParameter("uname");
                    
                    //Creat obj of ToyClass (i.e info)
                    ToyClass info = new ToyClass();
                    //Temporary store the parameters(veg_name,vegi_price) into the vegiclass object (info)
                    info.setToy_name(name);
                    info.setToy_price(price);
                    
                     //passing the ToyClass obj(info) into the addToy function from the interface(i.e infc)
                    vinterface.addToy(info); 
                    //dispaly message if addToy executes successfully.
                    response.sendRedirect("home.jsp?msg=insert");
                    //request.setAttribute("username", uname);                        
                    //request.getRequestDispatcher("home.jsp").forward(request, response);
                    
                    
                    
                //If display button in home.jsp page is clicked                 
                }else if(request.getParameter("display") != null){
                    //Connection to server and bind with the server ip and port
                    Registry reg = LocateRegistry.getRegistry("127.0.0.1", 5000);
                    //creat object of Toy_interface (i.e infc) and bind with the server name
                    Toy_interf infc = (Toy_interf)reg.lookup("Server");
                    
                    //get the returned list from Toy_implem class via Toy_interf onject (i.e infc) and store
                    //in inside the list object toy 
                    List<ToyClass> list = (List)infc.getToyInfo();
                    out.println("<table border='1'>");
                    out.println("<tr><th>Toy Name</th><th>Toy Price</th></tr>");
                    
                //loop through the list to get individual row(toy_name and toy_price)
                //display is a table format
               //+ "<input type='text' name='delname' value='"++"' hidden=''>" 
                list.stream().map((_item) -> {
                    out.println("<tr>");
                    return _item;
                }).map((vegi) -> {
                    out.println("<td>" + vegi.getToy_name() + "</td>");
                    return toy;
                }).map((vegi) -> {
                    out.println("<td>" + vegi.getToy_price() + "</td>");
                    return toy;
                }).map((vegi) -> {
                    out.println("<td>"
                            + "<form action='TOyController' method='POST'>"
                            + "<input type='text' name='delname' value='"+toy.getToy_name()+"' hidden=''>"                                                
                            + "<input type='submit' name='"+delete+"' value='Delete'></td>"
                            + "</form>");
                    return toy;
                }).forEachOrdered((_item) -> {
                    out.println("</tr>");
                });
                    out.println("</table>");
                    
                //If the calculate button is clicked                    
                }else if(request.getParameter("calculate") !=null){
                    //Connection to server and bind with the server ip and port
                    Registry reg = LocateRegistry.getRegistry("127.0.0.1", 5000);
                    //creat object of Toy_interface (i.e infc) and bind with the server name
                    Toy_interf infc = (Toy_interf)reg.lookup("Server");
                    
                    //get the parameters form home.jsp page 
                    names = request.getParameterValues("veginame[]");
                    quantities = request.getParameterValues("quantity[]");
                    
                    //create local variable final_price  
                    double final_price = 0.0;
                    
                    out.println("<table border='1'>");
                    out.println("<tr><th align='center'>Quick Calculator</th></tr>");
                    out.println("</table>");
                    for(int i=0;i<names.length;i++){
                        //Creat obj of ToyClass (i.e info)
                        ToyClass info = new ToyClass();
                        //Temporary store the parameters(toy_name,quantity) into the toyclass object (info) using setters
                        info.setToy_name(names[i]);
                        //final_price  stores the product(*) of quantity supplied and unit price of each item from the db                  
                        final_price = Integer.parseInt(quantities[i]) * infc.totalPrice(info);
                        //total store the sum of product of all items requested
                        total +=final_price;
                        //displaying the result in a table format
                       out.println("<table border='1'>");                        
                        out.println("<tr><th>Toy Name</th><th>Total Amount</th></tr>");
                        out.println("<tr>");
                        out.println("<td>" +info.getToy_name() + "</td>");                        
                        out.println("<td>" + final_price + "</td>");                            
                        out.println("</tr>");
                       out.println("</table>");
                    } 
                    out.println("<table border='1'>");
                    out.println("<tr>");
                    out.println("<td>Total Amount</td>");
                        out.println("<td>" + total+ "</td>");
                        out.println("</tr>");
                        out.println("</table>");
                        
                }
                else if(request.getParameter("print") !=null){
                    //Connection to server and bind with the server ip and port
                    Registry reg = LocateRegistry.getRegistry("127.0.0.1", 5000);
                    //creat object of Toy_interface (i.e infc) and bind with the server name
                    Toy_interf infc = (Toy_interf)reg.lookup("Server");
                    
                    //get the parameters form home.jsp page 
                    String username = request.getParameter("username");
                    String paid_amount = request.getParameter("paid_amount"); 
                    
                    //create local variable final_price 
                    double final_price = 0.0;                    
                    
                    out.println("<table border='1'>");
                    out.println("<tr><th align='center'>Toy Service Receipt</th></tr>");
                    out.println("</table>");
                    for(int i=0;i<names.length;i++){
                         //Creat obj of ToyClass (i.e info)
                        ToyClass info = new ToyClass();
                        //Temporary store the parameters(vegi_name,quantity) into the vegiclass object (info) using setters
                        info.setToy_name(names[i]);
                        //final_price  stores the product(*) of quantity supplied and unit price of each item from the db 
                        final_price = Integer.parseInt(quantities[i]) * infc.totalPrice(info);                                                
                        //displaying the result in a table format
                       out.println("<table border='1'>");                        
                        out.println("<tr><th>Toy Name</th><th>Total Amount</th></tr>");
                        out.println("<tr>");
                        out.println("<td>" +info.getToy_name() + "</td>");                        
                        out.println("<td>" + final_price + "</td>");                            
                        out.println("</tr>");
                       out.println("</table>");
                    } 
                    //crate a local variable change_due to store the difference between paid amount and total cost
                        double change_due = Double.parseDouble(paid_amount)-total;
                        //display results in table format
                        out.println("<table border='1'>");                        
                        out.println("<tr><th>Net Price</th><th>"+total+"</th></tr>");
                        out.println("<tr><th>Amount Given</th><th>"+paid_amount+"</th></tr>");
                        out.println("<tr><th>Change due</th><th>"+change_due+"</th></tr>");
                        out.println("<tr><th>Served By</th><th>"+username+"</th></tr>");
                        out.println("</table>");
                        //System.out.println("Here");
                        //reset total to 0 ready for new transaction.
                        total = 0.0;
                    
                    
                }else if(request.getParameter("update") != null){
                    //Connection to server and bind with the server ip and port
                    Registry reg = LocateRegistry.getRegistry("127.0.0.1", 5000);
                    //creat object of Toy_interface (i.e infc) and bind with the server name
                    Toy_interf infc = (Toy_interf)reg.lookup("Server");
                   
                    //get the parameters form home.jsp page 
                    String name = request.getParameter("toy_name");                  
                    int price = Integer.parseInt(request.getParameter("toy_price"));
                    
                     //Creat obj of ToyClass (i.e info)
                    ToyClass info = new ToyClass();
                     //Temporary store the parameters(vegi_name,vegi_price) into the toyclass object (info) using setters
                    info.setToy_name(name);
                    info.setToy_price(price);
                    
                     //passing the ToyClass obj(info) into the updateToy function from the interface(i.e infc)
                    infc.updateToy(info);
                    //dispaly message if updateToy executes successfully.
                    response.sendRedirect("home.jsp?msg=update");
//                    request.setAttribute("username", uname);                        
//                    request.getRequestDispatcher("home.jsp").forward(request, response);
//                    
                    

                }else if(request.getParameter("delete") != null){ 
                    //Connection to server and bind with the server ip and port
                    Registry reg = LocateRegistry.getRegistry("127.0.0.1", 5000);
                    //creat object of Toy_interface (i.e infc) and bind with the server name
                    Toy_interf infc = (Toy_interf)reg.lookup("Server");
                    
                    //get the parameters form home.jsp page 
                    String name = request.getParameter("delname");
                    
                     //Creat obj of ToyClass (i.e info)
                    ToyClass info = new ToyClass();
                     //Temporary store the parameters(vegi_name,) into the toyclass object (info) using setters
                    info.setToy_name(name);
                    
                     //passing the ToyClass obj(info) into the deleteToy function from the interface(i.e infc)
                    infc.deleteToy(info);
                    //dispaly message if deleteToy executes successfully.
                    response.sendRedirect("home.jsp?msg=delete");
//                    request.setAttribute("username", uname);                        
//                    request.getRequestDispatcher("home.jsp").forward(request, response);
                    
                }else if(request.getParameter(delete) != null){
                    //Connection to server and bind with the server ip and port
                    Registry reg = LocateRegistry.getRegistry("127.0.0.1", 5000);
                    //creat object of Toy_interface (i.e infc) and bind with the server name
                    Toy_interf infc = (Toy_interf)reg.lookup("Server");
                    
                    //get the parameters form home.jsp page 
                    String name = request.getParameter("delname");
                    
                     //Creat obj of ToyClass (i.e info)
                    ToyClass info = new ToyClass();
                     //Temporary store the parameters(toy_name,) into the toyclass object (info) using setters
                    info.setToy_name(name);
                    
                     //passing the ToyClass obj(info) into the deleteToy function from the interface(i.e infc)
                    infc.deleteToy(info);
                    //dispaly message if deleteToy executes successfully.
                    response.sendRedirect("home.jsp?msg=delete");
//                    request.setAttribute("username", uname);                        
//                    request.getRequestDispatcher("home.jsp").forward(request, response);
                }
                
               
               
           }catch(IOException | NumberFormatException | NotBoundException e){
           }
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
//        try {
//            Registry reg = LocateRegistry.getRegistry("127.0.0.1", 5000);
//            Toy_interf infc = (Toy_interf)reg.lookup("Server");
//            
//            List<ToyClass> list = (List)infc.getToyInfo();
//            request.setAttribute("toys", list);
//            request.getRequestDispatcher("home.jsp").forward(request, response);
//        } catch (RemoteException ex) {
//            Logger.getLogger(ToyController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NotBoundException ex) {
//            Logger.getLogger(ToyController.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
//        try {
//            Registry reg = LocateRegistry.getRegistry("127.0.0.1", 5000);
//            Toy_interf infc = (Toy_interf)reg.lookup("Server");
//            
//            List<ToyClass> list = (List)infc.getToyInfo();
//            request.setAttribute("toys", list);
//            request.getRequestDispatcher("home.jsp").forward(request, response);
//        } catch (RemoteException ex) {
//            Logger.getLogger(ToyController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NotBoundException ex) {
//            Logger.getLogger(ToyController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
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

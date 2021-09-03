<%-- 
    Document   : home
    Created on : Sep 1, 2020, 8:29:39 PM
    Author     : #USER
--%>

<%@page import="java.util.List"%>
<%@page import="Toy.ToyClass"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vegetable Service Engine</title>
        <script type="text/javascript">
            var counter = 1;
            var limit = 10;
            function addInput(divName){
                if(counter === limit){
                    alert("You have reached the limit of adding"+ counter+"input");
                }else{
                    var newdiv = document.createElement('div');
                    newdiv.innerHTML = "<tr><td><select name='toyname[]'>\n\
                    <option value='Doll'>Doll</option>\n\
                    <option value='Cookingkit'>Cookingkit</option>\n\
                    <option value='Tricycle'>Tricycle</option>\n\
                    <option value='Legos'>Legos</option>\n\
                    <option value='Train'>Train</option>\n\
                    <option value='Videogames'>Videogames</option>\n\
                    <option value='Books'>Books</option>\n\
                    <option value='Playcards'>Playcards</option>\n\
                    <option value='Toycars'>Toycars</option>\n\
                    </select></td><td><input type='text' name='quantity[]' placeholder='Quantity'></td>\n\
                     <td><input type='button' value='(+)' onclick='addInput('dynamic')'></td><tr>";
                    document.getElementById(divName).appendChild(newdiv);
                    counter++; 
                }
            }
        </script>
    </head>
    <body>
        <h1>Vegetable Service</h1>
        <h3 style="color: red">
             <%
            if(request.getParameter("msg") != null){
                if(request.getParameter("msg").equals("insert")){
                    out.println("Inserted Successfully");
                }
                else if(request.getParameter("msg").equals("update")){
                   out.println("Update Successfully"); 
                }
                else if(request.getParameter("msg").equals("delete")){
                    out.println("Deleted Successsfully");
                }
                else if(request.getParameter("msg").equals("mssg")){
                    out.println("Data already exist");
                }
            }
             %>
             
        </h3>
        <h3>
            Employee name: <%= request.getAttribute("username")%>
        </h3>
        <hr>
        <div>
            <div style="float: left;">
                <h4>Add New toy</h4>
                <form method="POST" action="toyController">
                    <table>
                        <tr><input type="text" name="uname" value=" <%= request.getAttribute("username")%>" hidden=""></tr>
                        <tr>
                            <td>Toy Name</td>
                            <td><input type="text" name="toy_name"></td>                    
                        </tr>                 
                        <tr>
                            <td>Toy Price</td>
                            <td><input type="text" name="toy_price"></td>                    
                        </tr> 
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                
                        <tr>                    
                            <td><input type="submit" name="save" value="Save"></td>                    
                            <td><input type="submit" name="update" value="Update"></td>
                            <td><input type="submit" name="display" value="Display"></td>
                        </tr>              
                
                    </table>
                </form>
                <br>
            </div>
            <div style="float: left;margin-left: 300px;">
                <h4>Calculation</h4>
                <form action="ToyController" method="POST">
                    <table border='1'>
                        
                        <tr>
                            <th style="padding-left: 0px; padding-right: 2.5px;"><label>Toy Name</label></th>
                            <th style="padding-left: 50px; padding-right: 2.5px;"><label>Quantity</label></th>
                            <th style="padding-left: 25px; padding-right: 2.5px;"><label>Action</label></th>
                        </tr>
                    </table>
                    <div id="dynamicInput">
                        <table>
                            <tr style="margin-bottom: 500px;">
                                <td>
                                    <select name="toyname[]">
                                        <option value="Doll">Kale</option>
                                        <option value="Cookingkit">Cabbage</option>
                                        <option value="Tricycle">Spinach</option>
                                        <option value="Legos">Cucumber</option>
                                        <option value="Train">Carrot</option>
                                        <option value="Videogames">Broccoli</option>
                                        <option value="Books">Asparagus</option>
                                        <option value="Playcards">Chokos</option>
                                        <option value="Toycars">Celery</option>
                                        <option value="Puzzles">Cauliflower</option>
                                        <option value="Actionfigures">Eggplant</option>
                                        <option value="Colorpaints">Leeks</option>
                                        <option value="Fidgets">Lettuce</option>
                                        <option value="Ballons">Melon</option>
                                        <option value="Waterguns">Onion</option>
                                    </select>
                                </td>
                                <td>
                                   <input type="text" name="quantity[]" placeholder="Quantity"> 
                                </td>
                                <td>
                                    <input type="button" value="(+)" onclick="addInput('dynamicInput')">
                                </td>
                            </tr>
                        </table>
                    </div><br>
                    <table>
                        <tr>
                           <td><input type="submit" name="calculate" value="Total Price"></td> 
                        </tr>
                        
                    </table><br>
                    <table>
                        <tr>
                            <td><label>Paid Amount: </label></td>
                            <td><input type="text" name="paid_amount" placeholder="Paid Amount"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="text" name="username" value="<%= request.getAttribute("username")%>" hidden=""></td>                            
                            <td><input type="submit" name="print" value="Print Receipt"></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div style="float: right;">
                <h4>Delete the Toys</h4>
                <form action="ToyController" method="POST">
                    <input type="text" name="delname" placeholder="Enter Name">
                    <input type="submit" name="delete" value="DELETE">
                </form> 
            </div>
            <div>
                               
            </div>
        </div>
                           
      

    </body>
</html>

A simple mobile distributed application - toy service engine.
Yvonne Karanja Wanjiku 77738
#Steps taken to produce the working code. 
#1. Tools used in project development Database -> MYSQL database Engine codebase. Java and java rmi libraries and java server programming (JSP)
#2. System setup procedure Creating a database called toyengine with tables users and vegis Connecting our database to java project with a java mysql connector driver.
#3 The code Created jsp files for the front end -index.jsp and home.jsp Developed the vegetable and user classes that define the objects properties to be transmitted or stored from the server to client User.java, ToyClass.java developed UserInterface.java and Toy_interf.java to declare the functions that are to be performed on the respective classes Created UserImplementation.java and Toy_implem.java classes creates a connection to the database created the servelets UserController.java and ToyController.java gets information from client and binds it to server. Created the server which declares the port to which client requests will be channeled through..

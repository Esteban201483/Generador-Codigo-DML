package Build;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SQLScriptGeneratorImp extends DmlScriptGenerator{
    String code;
    DataBaseConfiguration dataBaseConfiguration;

    public SQLScriptGeneratorImp(List<Entity> IR, DataBaseConfiguration dbc)
    {
        super(IR);
        dbc = dataBaseConfiguration;
    }


    public String generateCode() {
        System.out.println("Code Generation...");
        code = "";

        for(Entity e : entityList)
        {
            code += e.getCode();
        }

        printCode();

        return code;
    }

   void printCode()
   {
        System.out.println("Executing script in MySQL database");

        executeScript();

        System.out.println("Script executed");

   }


    /** Opens a connection with the database.
     * @return A connection instance.
     */
    private Connection openConnection()
    {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/ci1322_lab1";
            conn = DriverManager.getConnection(url, dataBaseConfiguration.getUserName(), dataBaseConfiguration.getPassword());
        }
        catch(Exception e)
        {
            System.out.println("Error: No se pudo realizar la conexión con la base de datos.");
        }

        return conn;
    }

    public void executeScript() {
        try
        {
            Connection conn = openConnection();

            if(conn != null)
            {
                Statement stmt = conn.createStatement();
                stmt.execute(code);

                conn.close();
            }
        }
        catch(SQLException e)
        {
            System.out.println("Errror: Script can't be executed.");
        }

    }



}

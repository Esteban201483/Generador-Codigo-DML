/**
 * Contains a Definition of a Table, to make IR and code generation easier!
 */

package Build;

import java.util.List;

/**
 * This class stores a table IR
 */
public class Table implements Entity {

    String name;
    List<Column> columnList;
    Column primaryKey;

    /**
     * Obtains the PrimaryKey of the table
     * @return the primaryKey, as instance of Column. Can be null.
     */
    public Column getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Sets the primaryKey for the table
     * @param primaryKey
     */
    public void setPrimaryKey(Column primaryKey) {
        this.primaryKey = primaryKey;
        columnList.add(primaryKey);
    }

    public List<Column> getColumnList() {
        return columnList;
    }


    /**
     * Sets the Column list of the table
     * @param columnList
     */
    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }


    /**
     * With this method an Entity can be identified as a table
     * @return "Table"
     */
    public String getType()
    {
        return "Table";
    }

    /**
     * Gets the table name.
     * @return the table's name. It can be empty, for validation purposes.
     */
    public String getName() {
        return name;
    }

    /**
     * Set a new name
     * @param name The new name of the table
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Generates a SQL code of the IR table. Calls its IR Columns get Code.
     * @return The SQL code of the table
     */
    public String getCode()
    {
        //Temporal: Returns IR (columns)
        System.out.println("------------------------- ");
        System.out.println("Table: " + name);

        for(Column c : columnList)
        {
            System.out.println(c.getName()+ "length: " + c.getLength());
        }


        System.out.println("Primary key: " + primaryKey.getName());

        String code = "";
        code += "CREATE TABLE " + name + "(\n";


        for(Column c : columnList)
        {
            String tmpType = "";

            if(c.getType().equals("int"))
            {
                tmpType += " INT";
            }
            else
            {

                if(c.getType().equals("class java.lang.String"))
                {
                    tmpType += " VARCHAR(" + c.getLength() + ")";
                }
                else if(c.getType().equals("float"))
                {
                    int precision = (c.getPrecision() == 0)? 16 : c.getPrecision();
                    tmpType += " FLOAT(" + precision + ")";
                }
                else if(c.getType().equals("double"))
                {
                    int precision = (c.getPrecision() == 0)? 16 : c.getPrecision();
                    tmpType += " DOUBLE(" + precision + ")";
                }
                else if(c.getType().equals("boolean"))
                {
                    int precision = (c.getPrecision() == 0)? 16 : c.getPrecision();
                    tmpType += " BOOLEAN";
                }

            }

            code += "\t" + c.getName() + tmpType + ",\n";
        }

        //Adds the PK field

        code += "\tPRIMARY KEY(" + primaryKey.getName() +")\n";

        code += ");\n\n";
        return code;
    }

}

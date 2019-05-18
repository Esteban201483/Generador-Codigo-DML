package Build;

import java.util.List;

public class Table implements Entity {


    String name;
    List<Column> columnList;

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }


    public String getType()
    {
        return "Table";
    }

    public String getCode()
    {
        //Temporal: Returns IR (columns)

        System.out.println("Table: " + name);
        for(Column c : columnList)
        {
            System.out.println(c.getName());
        }

        return "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

package Build;

public class Relation implements Entity {

    Entity table1;
    Entity table2;

    public Entity getTable1() {
        return table1;
    }

    public void setTable1(Entity table1) {
        this.table1 = table1;
    }

    public Entity getTable2() {
        return table2;
    }

    public void setTable2(Entity table2) {
        this.table2 = table2;
    }


    public String getType()
    {
        return "Relation";
    }

    public String getCode()
    {
        return "";
    }
}

package Build;

public class Relation implements Entity {

    Entity table1;
    Entity table2;
    String type;
    String mappedBy;

    public String getMappedBy() {
        return mappedBy;
    }

    public void setMappedBy(String mappedBy) {
        this.mappedBy = mappedBy;
    }

    public void setType(String type) {
        this.type = type;
    }

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
        return this.type;
    }

    public String getCode()
    {
        return "";
    }
}

package Build;

public class ForeignKeyColumn extends Column{

    private String referencedTable;
    private String referencedColumn;

    public ForeignKeyColumn(Column c)
    {
        super();
        this.name = c.getName();
        this.type = c.getType();
        this.unique = c.isUnique();
        this.scale = c.getScale();
        this.length = c.getLength();
        this.nullable = c.isNullable();
        this.lobType = c.getLobType();
        this.precision = c.getPrecision();
    }

    public String getReferencedTable() {
        return referencedTable;
    }

    public void setReferencedTable(String referencedTable) {
        this.referencedTable = referencedTable;
    }

    public String getReferencedColumn() {
        return referencedColumn;
    }

    public void setReferencedColumn(String referencedColumn) {
        this.referencedColumn = referencedColumn;
    }
}

package Build;

enum Lob
{
    Clob, Blob;
}

public class Column {

    private String name;
    private String type;
    private boolean unique;
    private String scale;
    private int length;


    private boolean nullable;
    Lob lobType;

    private int precision;

    public Column()
    {
        name = "";
    }

    /**
     * Creates an IR of the represented column
     * @return the IR code of the column
     */
    public String getCode()
    {
        String code = "";

        return code;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Lob getLobType() {
        return lobType;
    }

    public void setLobType(Lob lobType) {
        this.lobType = lobType;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }


    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

}

package Build;

import java.util.List;

public class DmlScriptGenerator {



    protected List<Entity> entityList;

    public DmlScriptGenerator(List<Entity> entityList)
    {
           this.entityList = entityList;
    }

    public DmlScriptGenerator()
    {

    }

    String generateCode()
    {
        String code = "";

        return code;
    }

}

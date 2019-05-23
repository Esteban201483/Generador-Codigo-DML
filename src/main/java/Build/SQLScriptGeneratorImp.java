package Build;

import java.util.List;

public class SQLScriptGeneratorImp extends DmlScriptGenerator{
    String code;

    public SQLScriptGeneratorImp(List<Entity> IR)
    {
        super(IR);
    }

    public String generateCode() {
        return "";
    }

   void printCode()
   {

   }


}

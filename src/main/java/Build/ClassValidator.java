package Build;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class ClassValidator {


    List<Class> projectClasses;


    public ClassValidator(List<Class> projectClasses)
    {
        this.projectClasses = projectClasses;
    }

    public List<Entity> startValidation()
    {
        List<Entity> entityList = new LinkedList<Entity>();



        //Scan all the classes
        for(Class<?> projectClass : projectClasses)
        {
            int primaryKeyCounter = 0;
            System.out.println("Scanning: " + projectClass.getName());

            Field[] fields =  projectClass.getDeclaredFields();

            //Obtains all the fields
            for(Field field : fields){
                //Obtains all the annotations
                for(Annotation a : field.getDeclaredAnnotations())
                {
                    System.out.println(a.toString());
                    System.out.println(a.annotationType().getName());

                    if(a.annotationType().getName() == "javax.persistence.Id")
                    {
                        ++primaryKeyCounter;
                    }
                }

                if(primaryKeyCounter != 1)
                {
                    if(primaryKeyCounter == 0)
                        System.out.println("Error: Class " + projectClass.getName() + "doesn't contain any PK");
                    else
                        System.out.println("Error: Class " + projectClass.getName() + "contains more than one PK");
                }


               // System.out.println("Annotation detected. Type: "+field.getType()+".  Name:  " + field.getName());
            }


        }

        return entityList;
    }


}

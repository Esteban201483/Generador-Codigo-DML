package Build;

import javax.xml.bind.ValidationException;
import java.io.IOException;
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

    public List<Entity> startValidation() throws ValidationException
    {
        List<Entity> entityList = new LinkedList<Entity>();
        int primaryKeyCounter;
        int columnCounter;


        //Scan all the classes
        for(Class<?> projectClass : projectClasses)
        {
            primaryKeyCounter = 0;
            columnCounter = 0;
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
                    else if(a.annotationType().getName() == "javax.persistence.Id")
                    {
                        columnCounter++;
                    }
                }


               // System.out.println("Annotation detected. Type: "+field.getType()+".  Name:  " + field.getName());
            }

            if(primaryKeyCounter != 1)
            {
                if(primaryKeyCounter == 0)
                    throw new ValidationException("Error: Class " + projectClass.getName() +  " doesn't contain any PK");
                else
                    throw new ValidationException("Error: Class " + projectClass.getName() + " contains more than one PK");
            }


        }

        return entityList;
    }


}

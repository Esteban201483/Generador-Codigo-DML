package Build;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.persistence.Column;
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
        List<Build.Column> columnList = new LinkedList<Build.Column>();
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
                for(Annotation annotation : field.getDeclaredAnnotations())
                {
                    if(annotation.annotationType().getSimpleName().equals("Id"))
                    {
                        ++primaryKeyCounter;
                    }
                    else if(annotation.annotationType().getSimpleName().equals("Column"))
                    {
                        Column columnAnnotation = (Column) annotation;

                        Build.Column newColumn = new Build.Column();
                        newColumn.setLength(columnAnnotation.length());
                        newColumn.setName(columnAnnotation.name());
                        newColumn.setScale("" + columnAnnotation.scale());
                        newColumn.setPrecision(columnAnnotation.precision());

                        //Falta el tipo!!!
                        columnList.add(newColumn);
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


            //If everything is OK then create the table IR
            Table newTable = new Table();
            newTable.setColumnList(columnList);

            javax.persistence.Table tableAnnotation =  projectClass.getAnnotation(javax.persistence.Table.class);
            if(tableAnnotation == null)
                throw new ValidationException("Error: Class " + projectClass.getName() + " doesn't have a table annotation");
            else if (tableAnnotation.name().equals(""))
                throw new ValidationException("Error: Class " + projectClass.getName() + " doesn't have a table name");

            newTable.setName(tableAnnotation.name());

            entityList.add(newTable);


        }

        for(Entity e : entityList)
        {
            if(e.getType().equals("Table"))
                e.getCode();
        }

        return entityList;
    }


}

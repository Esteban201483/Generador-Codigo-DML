package Build;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.persistence.*;
import javax.persistence.Column;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * Checks the project structure and build the IR
 */
public class ClassValidator {
    List<Class> projectClasses;
    List<Relation> relationList;
    boolean isRelation;
    String mappedBy;
    String secondRelation;

    /**
     * Constructor that receives the list with all the instances of classes with entity annotation
     * @param projectClasses
     */
    public ClassValidator(List<Class> projectClasses)
    {

        this.projectClasses = projectClasses;
    }

    /**
     * Validates the classes and generates the IR
     * @return the IR with all the tables and relations
     * @throws ValidationException To control some situation, like no PK or multiple PK for instance.
     */
    public List<Entity> startValidation() throws ValidationException
    {
        this.relationList= new LinkedList<Relation>();

        //Stores database objects
        List<Entity> entityList = new LinkedList<Entity>();
        List<Build.Column> columnList = new LinkedList<Build.Column>();

        //Controls some specific situations
        int primaryKeyCounter;
        int columnCounter;
        boolean columnRequired; //If a lob, enumerate or similar annotations appears, then a column annotation is mandatory
        boolean isPrimaryKey; //Allows to isolate the primary key from the other columns
        Build.Column primaryKey = null;

        //Scan all the classes
        for(Class<?> projectClass : projectClasses)
        {
            //Reset control values
            columnList = new LinkedList<Build.Column>();
            primaryKeyCounter = 0;
            columnCounter = 0;
            isPrimaryKey = false;
            primaryKey = null;
            System.out.println("Scanning: " + projectClass.getName());
            Field[] fields =  projectClass.getDeclaredFields();
            String columnName="";

            //Obtains all the fields
            for(Field field : fields){
                columnRequired = false;
                isRelation=false;
                mappedBy="";

                //Obtains all the annotations
                System.out.println("New Field!");
                Build.Column newColumn = new Build.Column();

                //Search all the annotations in the actual field
                for(Annotation annotation : field.getDeclaredAnnotations())
                {

                    if(annotation.annotationType().getSimpleName().equals("Id"))
                    {
                        System.out.println("PK Found!");
                        ++primaryKeyCounter;
                        columnRequired = true;
                        isPrimaryKey = true;
                    }
                    else if(annotation.annotationType().getSimpleName().equals("Column"))
                    {
                        Column columnAnnotation = (Column) annotation;
                        System.out.println("CD " + columnAnnotation.length());
                        newColumn.setLength(columnAnnotation.length());
                        newColumn.setName(columnAnnotation.name());
                        newColumn.setScale("" + columnAnnotation.scale());
                        newColumn.setPrecision(columnAnnotation.precision());
                        //Falta el tipo!!!
                        System.out.println("Column Found: " + columnAnnotation.name() + "!");
                        columnRequired = true;
                        columnName = columnAnnotation.name();
                    }
                    else if(annotation.annotationType().getSimpleName().equals("Lob"))
                    {
                        System.out.println("LOB Found in column : " + newColumn.getName() + "!");
                        columnRequired = true;
                    }
                    else if(annotation.annotationType().getSimpleName().equals("Enumerated"))
                    {
                        System.out.println("Enumerated Found in column : " + newColumn.getName() + "!");
                        columnRequired = true;
                    }
                    else if(annotation.annotationType().getSimpleName().equals("OneToOne"))
                    {
                        isRelation=true;
                        OneToOne  oneToOne = (OneToOne) annotation;
                        mappedBy=oneToOne.mappedBy();


                    }
                    else if(annotation.annotationType().getSimpleName().equals("OneToMany"))
                    {
                        isRelation=true;
                        OneToMany oneToMany = (OneToMany) annotation;
                        mappedBy=oneToMany.mappedBy();
                    }
                    else if(annotation.annotationType().getSimpleName().equals("ManyToOne"))
                    {
                        isRelation=true;
                        ManyToOne manyToOne = (ManyToOne) annotation;
                        //mappedBy=manyToOne.mappedBy();
                    }
                    else if(annotation.annotationType().getSimpleName().equals("ManyToMany"))
                    {
                        isRelation=true;
                        ManyToMany manyToMany = (ManyToMany) annotation;
                        mappedBy=manyToMany.mappedBy();
                    }
                    else if(annotation.annotationType().getSimpleName().equals("JoinColumn"))
                    {
                       JoinColumn joinColumn= (JoinColumn) annotation;
                       secondRelation= joinColumn.table();
                    }
                    else
                    {
                        throw new ValidationException("Error: Class " + projectClass.getName() +  " contains unknown " + annotation.annotationType().getSimpleName() + " annotation ");
                    }

                }//Annotation search end

                //Checks if some annotations requires the column annotation
                if(columnRequired) {
                    //No name = no column declared
                    if (newColumn.getName().equals(""))
                        throw new ValidationException("Error: Class " + projectClass.getName() + " doesn't contain a required column annotation");
                    else {

                        //Isolates the primaryKey from the other columns
                        if(!isPrimaryKey)
                            columnList.add(newColumn);
                        else {
                            primaryKey = newColumn;
                            isPrimaryKey = false;
                        }
                    }
                } //Ends column required if

            }

            //All the table must have one and only one primary key
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
            newTable.setPrimaryKey(primaryKey);

            //Table annotation is mandatory
            javax.persistence.Table tableAnnotation =  projectClass.getAnnotation(javax.persistence.Table.class);
            if(tableAnnotation == null)
                throw new ValidationException("Error: Class " + projectClass.getName() + " doesn't have a table annotation");
            else if (tableAnnotation.name().equals(""))
                throw new ValidationException("Error: Class " + projectClass.getName() + " doesn't have a table name");

            newTable.setName(tableAnnotation.name());
            entityList.add(newTable);
            if(isRelation)
            {
                Relation r= new Relation();
                r.setMappedBy(mappedBy);
                relationList.add(r);
                r.setTable1(newTable);
                r.setTable2name(secondRelation);
                r.setColumn1(columnName);
                }

        }

        //Temporal. Prints all the tables in the IR
        for(Entity e : entityList)
        {
            if(e.getType().equals("Table"))
                e.getCode();
        }

        return entityList;
    }


}

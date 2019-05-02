import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.persistence.Entity;

public class Prueba {



    public static void main(String args[])
    {
        System.out.println("Hola Mundo");
    }

    public void generarCodigo(String paquete)
    {
        //Encuentra e imprime todas las clases que tengan un entity
         encontrarClases(paquete);

    }

    public void encontrarClases(String paquete)
    {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        System.out.println(paquete);

        for(BeanDefinition beanDef: provider.findCandidateComponents(paquete))
        {

            try {
                Class<?> cl = Class.forName(beanDef.getBeanClassName());
                System.out.println("Clase encontrada: " + cl.getName());
            }
            catch(ClassNotFoundException e)
            {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }
}

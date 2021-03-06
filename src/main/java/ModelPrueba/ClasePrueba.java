package ModelPrueba;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

@Table(name = "Prueba")

public class ClasePrueba {


    List<Class> scanClasses(String projectPackage)
    {
        List<Class> projectClasses = new LinkedList<Class>();

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));

        for(BeanDefinition beanDef: provider.findCandidateComponents(projectPackage))
        {

            try {
                Class<?> cl = Class.forName(beanDef.getBeanClassName());
                System.out.println("Class Find: " + cl.getName());
                projectClasses.add(cl);
            }
            catch(ClassNotFoundException e)
            {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return projectClasses;
    }

}

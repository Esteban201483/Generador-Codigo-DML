/**
 * ClassScanner
 * Finds all the model class with the Entity Annotation
 */

package Build;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.persistence.Entity;
import java.util.LinkedList;
import java.util.List;

public class ClassScanner {

    /**
     * Search all the classes in the project package with the entity annotation
     * @param projectPackage The package where to search the model class
     * @return a List with all the classes with Entity annotation. It can be empty!
     */
    List<Class> scanClasses(String projectPackage)
    {
        List<Class> projectClasses = new LinkedList<Class>();

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));

        //Uses the providr filter to search all the clases with Entity annotation
        for(BeanDefinition beanDef: provider.findCandidateComponents(projectPackage))
        {
            try {
                Class<?> cl = Class.forName(beanDef.getBeanClassName());
                System.out.println("Class Detected: " + cl.getName());
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

package andersen.AndersenTasks.annotation;

import java.lang.reflect.Field;


public class NullableWarningProcessor {
   public static void fieldCheck(Object obj) {
       for (Field f : obj.getClass().getDeclaredFields()){
           if (f.isAnnotationPresent(NullableWarning.class)) {
               f.setAccessible(true);
               try {
                   if (f.get(obj) == null) {
                       System.out.println("Variable " + f.getName() + " is null in " + f.getClass() + "!");
                   }
               } catch (IllegalAccessException exception) {
                   System.out.println(exception.getMessage());
               }
           }
       }
   }
}

package andersen.AndersenTasks.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomHashSetTests {
    private CustomHashSet customHashSet;

    @BeforeEach
    public void init(){
        customHashSet = null;
        customHashSet = new CustomHashSet<>();
    }

    @Test
    public void testNotNull(){
        Assertions.assertNotNull(customHashSet);
    }

    @Test
    public void testCountElements(){
        for(int i = 0; i < 5; i++){
            customHashSet.put("String-"+i);
        }
        Assertions.assertEquals(5, customHashSet.countElements());
        Assertions.assertFalse(customHashSet.countElements() == 4);
    }

    @Test
    public void testAddElement(){
        for(int i = 0; i < 10; i++){
            customHashSet.put("String-"+i);
        }
        customHashSet.put("String-6");
        Assertions.assertEquals(10, customHashSet.countElements());
    }

    @Test
    public void testContains(){
        for(int i = 0; i < 10; i++){
            customHashSet.put("String-"+i);
        }
        Assertions.assertTrue(customHashSet.contains("String-3"));
        Assertions.assertFalse(customHashSet.contains("String-33"));
    }

    @Test
    public void testDelete(){
        for(int i = 0; i < 10; i++){
            customHashSet.put("String-"+i);
        }
        customHashSet.delete("String-2");
        Assertions.assertFalse(customHashSet.contains("String-2"));
        Assertions.assertEquals(9, customHashSet.countElements());
    }
}

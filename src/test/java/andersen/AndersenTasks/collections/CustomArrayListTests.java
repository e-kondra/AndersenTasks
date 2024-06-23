package andersen.AndersenTasks.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CustomArrayListTests {
    private CustomArrayList customArrayList;

    @BeforeEach
    public void init(){
        customArrayList = new CustomArrayList<>();
    }

    @Test
    public void testNotNull(){
        Assertions.assertNotNull(customArrayList);
    }

    @Test
    public void testNull(){
        Assertions.assertEquals(0, customArrayList.getSize());
    }
    @Test
    public void testPut(){
        for(int i = 0; i < 10; i++){
            customArrayList.put("String");
        }
        Assertions.assertEquals(10, customArrayList.getSize());
        Assertions.assertEquals("String", customArrayList.get(2));
    }

    @Test
    public void testGet(){
        for(int i = 0; i < 5; i++){
            customArrayList.put(Integer.valueOf(i));
        }
        for(int i = 0; i < 5; i++){
            Assertions.assertEquals(customArrayList.get(i).getClass(), Integer.class);
            Assertions.assertEquals(customArrayList.get(i), Integer.valueOf(i));
        }
    }

    @Test
    public void testDelete(){
        for(int i = 0; i < 6; i++){
            customArrayList.put(i);
        }
        customArrayList.delete(0);
        Assertions.assertEquals(5, customArrayList.getSize());
        Assertions.assertFalse( customArrayList.get(0).equals(0));
    }

}

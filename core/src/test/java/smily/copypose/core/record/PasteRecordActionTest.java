package smily.copypose.core.record;

import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@Testable
public class PasteRecordActionTest {

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test(){
        Person person = new Person();

        person.setName("Poo");
        person.storeToDatabase(1);
        assertEquals(person, DataPerson.data.get(1));
        assertEquals("Poo", DataPerson.data.get(1).name);

        person.setName("Lala");
        person.storeToDatabase(2);
        assertEquals(person, DataPerson.data.get(2));
        assertEquals("Lala", DataPerson.data.get(2).name);
    }

    @Mock
    RecordData recordData;

    @Test
    public void tester(){

    }
}

class Person{
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void storeToDatabase(int i){
        DataPerson.data.put(i, this);
    }
}

class DataPerson{
    static Map<Integer,Person> data = new HashMap<>();
}
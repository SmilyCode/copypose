package smily.copypose.core.record;

import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

@Testable
class RecordDatabaseTest {

    @Mock
    RecordPose recordPose;
    @Mock
    RecordPose recordPose2;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void canReplaceRecord() {
        String name = "Lala";
        RecordDatabase.storeRecord(name, recordPose2);
        RecordDatabase.replaceValue(name, recordPose);

        assertEquals(recordPose, RecordDatabase.getRecord(name));
    }

    @Test
    public void cannotReplaceRecord() {
        String name = "Lala";
        RecordDatabase.storeRecord(name, recordPose2);

        assertThrows(RuntimeException.class,()-> RecordDatabase.replaceValue("Haha", recordPose));
    }

}

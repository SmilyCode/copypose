package smily.copypose.core.record;

import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

@Testable
public class RecordPoseTest {
    @Mock
    RecordPose recordPose;
    @Mock
    Player player;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void canMakeObject(){
        String name = "Haha";
        recordPose = RecordPose.create(name, new RecordData(player));
        assertEquals(recordPose, RecordDatabase.getRecord(name));

        recordPose = RecordPose.create(null, new RecordData(player));
        assertEquals(recordPose, RecordDatabase.getRecord(null));
    }

    @Test
    public void cannotMakeObject(){
        String name = "Haha";
        recordPose = RecordPose.create(name, new RecordData(player));
        assertNull(RecordPose.create("Haha", new RecordData(player)));
    }

    @Test
    public void save() {
    }

    @Test
    public void stopRecord() {
    }

    @Test
    public void isPlaying() {
    }

    @Test
    public void getRecordData() {
    }
}
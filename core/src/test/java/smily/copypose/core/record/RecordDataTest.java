package smily.copypose.core.record;

import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import smily.copypose.data.PlayerMove;
import smily.copypose.util.TickConvert;

import static org.junit.Assert.*;

@Testable
@RunWith(MockitoJUnitRunner.class)
class RecordDataTest {
    RecordData recordData;
    @Mock
    Player player;
    @Mock
    PlayerMove playerMove;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void canCreateObject(){
        recordData = new RecordData(player);
        assertEquals(player, recordData.getPlayer());
        recordData = new RecordData(TickConvert.fromSecond(3), player);
        assertEquals(60, recordData.getDurationData());
    }

    @Test
    public void canRecord() {
        recordData = new RecordData(player);
        recordData.addTimestamp(1, playerMove);
        recordData.addTimestamp(2, playerMove);
        assertTrue(recordData.getTimestamp().containsKey(1));
        assertEquals(playerMove, recordData.getTimestamp().get(1));
        assertEquals(playerMove, recordData.getTimestamp().get(2));
    }

    @Test
    public void clearTimestamp() {
        recordData = new RecordData(player);
        recordData.addTimestamp(1, playerMove);
        recordData.addTimestamp(2, playerMove);
        assertTrue(recordData.getTimestamp().containsKey(1));
        assertEquals(playerMove, recordData.getTimestamp().get(1));
        recordData.clearTimestamp();
        assertTrue(recordData.getTimestamp().isEmpty());
        recordData.addTimestamp(1, playerMove);
        assertFalse(recordData.getTimestamp().isEmpty());
    }
}
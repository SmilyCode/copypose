package smily.copypose.util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PluginMessagerTest extends Mockito{
    @Mock
    Player player;
    @Mock
    CommandSender commandSender;
    @Mock
    PluginMessager pluginMessager;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void chatOrLog(){
    }

    @Test
    public void isPlayerInstance() {
    }
}
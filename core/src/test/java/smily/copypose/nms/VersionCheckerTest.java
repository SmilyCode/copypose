package smily.copypose.nms;

import org.bukkit.Server;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@Testable
public class VersionCheckerTest {
    VersionChecker versionChecker;
    @Mock
    Server server;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void isCorrectVersion(){
        versionChecker = new VersionChecker(server);

        when(server.getBukkitVersion()).thenReturn(BukkitVersionCompatible.V1_18_2.toString());
        versionChecker.check();

        assertTrue(versionChecker.isVersionCompatible());
    }

    @Test
    public void isNotCorrectVersion(){
        versionChecker = new VersionChecker(server);

        when(server.getBukkitVersion()).thenReturn("1.17.0-R0.1-SNAPSHOT");
        versionChecker.check();

        assertFalse(versionChecker.isVersionCompatible());
    }

    @Test
    public void canGetBukkitVersion(){
        versionChecker = new VersionChecker(server);

        when(server.getBukkitVersion()).thenReturn(BukkitVersionCompatible.V1_18_2.toString());
        versionChecker.check();

        assertEquals(BukkitVersionCompatible.V1_18_2, versionChecker.getBukkitVersion());
    }

    @Test
    public void cannotGetBukkitVersion(){
        versionChecker = new VersionChecker(server);

        when(server.getBukkitVersion()).thenReturn("1.17.0-R0.1-SNAPSHOT");
        versionChecker.check();

        assertNull(versionChecker.getBukkitVersion());
    }
}
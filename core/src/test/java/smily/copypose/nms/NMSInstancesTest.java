package smily.copypose.nms;

import junit.framework.TestCase;
import org.bukkit.entity.Player;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import smily.copypose.core.pastepose.IPastePose;
import smily.copypose.core.record.IPathRecorder;
import smily.copypose.core.record.RecordPose;

import static org.junit.Assert.*;

@Testable
public class NMSInstancesTest  {

    @Test
    public void canCreateInstances(){
        new NMSInstances(new PastePose(), new PathRecorder());

        IPathRecorder iPathRecorder = NMSInstances.getNewPathRecorder();
        assertNotSame(iPathRecorder, NMSInstances.getNewPathRecorder());
    }

    @Test
    public void canTestGetNewPastePose() {
    }

    public void canTestGetNewPathRecorder() {
    }

    class PastePose implements IPastePose{

        public PastePose(){}

        @Override
        public void pasteToPlayer(Player target, RecordPose recordPose) {

        }

        @Override
        public IPastePose clone() {
            return new PastePose();
        }
    }


    class PathRecorder implements IPathRecorder {

        @Override
        public void record(Player player, int duration) {

        }

        @Override
        public void stop() {

        }

        @Override
        public boolean isRecording() {
            return false;
        }

        @Override
        public IPathRecorder clone() {
            return new PathRecorder();
        }
    }
}
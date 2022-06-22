package smily.copypose.nms;

import smily.copypose.core.pastepose.IPastePose;
import smily.copypose.core.record.IPathRecorder;

import java.lang.reflect.InvocationTargetException;

//Access all of NMS Instances using this class
public class NMSInstances {
    public static IPastePose pastePose;
    public static IPathRecorder pathRecorder;

    //Have to be initiated on load or where ever just pointed to be at the start of the server
    public NMSInstances(IPastePose pastePose, IPathRecorder pathRecorder){
        NMSInstances.pastePose = pastePose;
        NMSInstances.pathRecorder = pathRecorder;
    }

    public static IPastePose getNewPastePose(){
        return pastePose.clone();
    }

    public static IPathRecorder getNewPathRecorder(){
        return pathRecorder.clone();
    }
}

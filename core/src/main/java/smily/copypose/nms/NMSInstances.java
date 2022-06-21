package smily.copypose.nms;

import smily.copypose.core.pastepose.IPastePose;

//Access all of NMS Instances using this class
public class NMSInstances {
    private static IPastePose pastePose;

    //Have to be initiated on load or where ever just pointed to be at the start of the server
    public NMSInstances(IPastePose pastePose){
        NMSInstances.pastePose = pastePose;
    }

    public static IPastePose getPastePose() {
        return pastePose;
    }
}

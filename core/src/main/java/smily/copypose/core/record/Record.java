package smily.copypose.core.record;

import java.io.File;

public interface Record{
    void record();

    void save(File file);

    void stopRecord();

    boolean isPlaying();
}

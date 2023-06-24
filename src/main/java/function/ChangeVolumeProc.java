package function;

import com.sun.jna.Callback;

public interface ChangeVolumeProc extends Callback {
    int invoke(byte[] ArchName, int Mode) throws Exception;
}

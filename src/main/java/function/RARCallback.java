package function;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface RARCallback extends Callback {
    int invoke(int msg, Pointer UserDate, Pointer P1, Pointer P2) throws Exception;
}

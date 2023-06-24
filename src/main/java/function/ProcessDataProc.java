package function;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;


public interface ProcessDataProc extends Callback {
    int invoke(Pointer Addr, int Size) throws Exception;
}

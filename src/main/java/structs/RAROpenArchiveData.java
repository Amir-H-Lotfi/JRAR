package structs;


import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.Structure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.nio.Buffer;
import java.nio.ByteBuffer;

@Structure.FieldOrder({
        "ArcName",
        "OpenMode",
        "OpenResult",
        "CmtBuf",
        "CmtBufSize",
        "CmtSize",
        "CmtState"
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RAROpenArchiveData extends Structure implements Serializable {
    public static class ByReference extends RAROpenArchiveData implements Structure.ByReference {
    }

    public static class ByValue extends RAROpenArchiveData implements Structure.ByValue {
    }

    public String ArcName;
    public int OpenMode;
    public int OpenResult;
    public Pointer CmtBuf;
    public int CmtBufSize;
    public int CmtSize;
    public int CmtState;
}

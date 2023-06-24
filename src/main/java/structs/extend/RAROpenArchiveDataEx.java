package structs.extend;


import com.sun.jna.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Structure.FieldOrder({
        "ArcName",
        "ArchNameW",
        "OpenMode",
        "OpenResult",
        "CmtBuf",
        "CmtBufSize",
        "CmtSize",
        "CmtState",
        "Flags",
        "Callback",
        "UserData",
        "OpFlags",
        "CmtBufW",
        "Reserved"
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RAROpenArchiveDataEx extends Structure {


    public static class ByReference extends RAROpenArchiveDataEx implements Structure.ByReference, Serializable {

    }

    public static class ByValue extends RAROpenArchiveDataEx implements Structure.ByValue, Serializable {

    }

    public String ArcName;
    public WString ArchNameW;
    public int OpenMode;
    public int OpenResult;
    public Pointer CmtBuf;
    public int CmtBufSize;
    public int CmtSize;
    public int CmtState;
    public int Flags;
    public Callback Callback;

    @Builder.Default
    public Pointer UserData = Pointer.NULL;
    public int OpFlags;
    @Builder.Default
    public Pointer CmtBufW = Pointer.NULL;

    @Builder.Default
    public int[] Reserved = new int[25];

}

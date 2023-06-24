package structs;


import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;


@Structure.FieldOrder({
        "ArcName",
        "FileName",
        "Flags",
        "PackSize",
        "UnpSize",
        "HostOs",
        "FileCRC",
        "FileTime",
        "UnpVer",
        "Method",
        "FileAttr",
        "CmtBuf",
        "CmtBufSize",
        "CmtSize",
        "CmtState"

})

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RARHeaderData extends Structure implements Serializable {

    public static class ByReference extends RARHeaderData implements Structure.ByReference, Serializable {
    }

    public static class ByValue extends RARHeaderData implements Structure.ByValue, Serializable {
    }


    @Builder.Default
    public byte[] ArcName = new byte[260];
    @Builder.Default
    public byte[] FileName = new byte[260];

    public int Flags;
    public int PackSize;
    public int UnpSize;
    public int HostOs;
    public int FileCRC;
    public int FileTime;
    public int UnpVer;
    public int Method;
    public int FileAttr;

    @Builder.Default
    public Pointer CmtBuf = Pointer.NULL;
    @Builder.Default
    public int CmtBufSize = 0;
    @Builder.Default
    public int CmtSize = 0;
    @Builder.Default
    public int CmtState = 0;

    @Override
    public String toString() {

        return "RARHeaderData{" +
                "ArcName=" + Arrays.toString(ArcName) +
                ", FileName=" + Arrays.toString(FileName) +
                ", Flags=" + Flags +
                ", PackSize=" + PackSize +
                ", UnpSize=" + UnpSize +
                ", HostOs=" + HostOs +
                ", FileCRC=" + FileCRC +
                ", FileTime=" + FileTime +
                ", UnpVer=" + UnpVer +
                ", Method=" + Method +
                ", FileAttr=" + FileAttr +
                ", CmtBuf=" + CmtBuf +
                ", CmtBufSize=" + CmtBufSize +
                ", CmtSize=" + CmtSize +
                ", CmtState=" + CmtState +
                '}';
    }
}

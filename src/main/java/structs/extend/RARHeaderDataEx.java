package structs.extend;


import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Structure.FieldOrder({
        "ArcName",
        "ArcNameW",
        "FileName",
        "FileNameW",
        "Flags",

        "PackSize",
        "PackSizeHigh",
        "UnpSize",
        "UnpSizeHigh",
        "HostOS",

        "FileCRC",
        "FileTime",
        "UnpVer",
        "Method",
        "FileAttr",

        "CmtBuf",
        "CmtBufSize",
        "CmtSize",
        "CmtState",
        "DictSize",

        "HashType",
        "Hash",
        "RedirType",
        "RedirName",
        "RedirNameSize",

        "DirTarget",
        "MtimeLow",
        "MtimeHigh",
        "CtimeLow",
        "CtimeHigh",

        "AtimeLow",
        "AtimeHigh",
        "Reserved"
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RARHeaderDataEx extends Structure implements Serializable {


    public static class ByReference extends RARHeaderDataEx implements Structure.ByReference {
    }

    public static class ByValue extends RARHeaderDataEx implements Structure.ByValue {
    }

    @Builder.Default
    public byte[] ArcName = new byte[1024];

    @Builder.Default
    public char[] ArcNameW = new char[1024];

    @Builder.Default
    public byte[] FileName = new byte[1024];

    @Builder.Default
    public char[] FileNameW = new char[1024];

    public int Flags;
    public int PackSize;
    public int PackSizeHigh;
    public int UnpSize;
    public int UnpSizeHigh;
    public int HostOS;

    public int FileCRC;
    public int FileTime;
    public int UnpVer;
    public int Method;
    public int FileAttr;

    public Pointer CmtBuf;
    public int CmtBufSize;
    public int CmtSize;
    public int CmtState;
    public int DictSize;

    public int HashType;
    @Builder.Default
    public byte[] Hash = new byte[32];
    public int RedirType;
    public Pointer RedirName;
    public int RedirNameSize;

    public int DirTarget;
    public int MtimeLow;
    public int MtimeHigh;
    public int CtimeLow;
    public int CtimeHigh;

    public int AtimeLow;
    public int AtimeHigh;
    @Builder.Default
    public int[] Reserved = new int[988];

}

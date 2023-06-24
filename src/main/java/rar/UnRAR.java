package rar;

import com.sun.jna.*;
import function.ChangeVolumeProc;
import function.ProcessDataProc;
import function.RARCallback;
import structs.RARHeaderData;
import structs.RAROpenArchiveData;
import structs.extend.RAROpenArchiveDataEx;
import structs.extend.RARHeaderDataEx;

import java.io.Serializable;

public interface UnRAR extends Library, Serializable {


    Pointer RAROpenArchive(RAROpenArchiveData ArchiveData);

    Pointer RAROpenArchiveEx(RAROpenArchiveDataEx ArchiveData);

    int RARCloseArchive(Pointer hArcData);

    int RARReadHeader(Pointer hArcData, RARHeaderData headerData);

    int RARReadHeaderEx(Pointer hArcData, RARHeaderDataEx headerData);

    int RARProcessFile(Pointer hArcData, int Operation, String DestPath, String DestName);

    int RARProcessFileW(Pointer hArcData, int Operation, WString DestPath, WString DestName);

    void RARSetCallback(Pointer hArcData, RARCallback Callback, Pointer UserData);

    void RARSetChangeVolProc(Pointer hArcData, ChangeVolumeProc ChangeVolProc);

    void RARSetChangeProcessDataProc(Pointer hArcData, ProcessDataProc ProcessDataProc);

    void RARSetPassword(Pointer hArcData, String Password);

    int RARGetDllVersion();
}

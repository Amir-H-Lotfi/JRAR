package constant;

import java.io.Serializable;

public interface Constants extends Serializable {

    interface CompressionMethod {
        int STORING = 0X30;
        int FASTEST_COMPRESSION = 0X31;
        int FAST_COMPRESSION = 0X32;
        int NORMAL_COMPRESSION = 0X33;
        int GOOD_COMPRESSION = 0X34;
        int BEST_COMPRESSION = 0X35;
    }

    interface OperationCode {

        int ERAR_SUCCESS = 0;
        int ERAR_END_ARCHIVE = 10;
        int ERAR_NO_MEMORY = 11;
        int ERAR_BAD_DATA = 12;
        int ERAR_BAD_ARCHIVE = 13;
        int ERAR_UNKNOWN_FORMAT = 14;
        int ERAR_EOPEN = 15;
        int ERAR_ECREATE = 16;
        int ERAR_ECLOSE = 17;
        int ERAR_EREAD = 18;
        int ERAR_EWRITE = 19;
        int ERAR_SMALL_BUF = 20;
        int ERAR_UNKNOWN = 21;
        int ERAR_MISSING_PASSWORD = 22;
        int ERAR_EREFERENCE = 23;
        int ERAR_BAD_PASSWORD = 24;

        int RAR_OM_LIST = 0;
        int RAR_OM_EXTRACT = 1;
        int RAR_OM_LIST_INCSPLIT = 2;

        int RAR_SKIP = 0;
        int RAR_TEST = 1;
        int RAR_EXTRACT = 2;

        int RAR_VOL_ASK = 0;
        int RAR_VOL_NOTIFY = 1;

        int RAR_DLL_VERSION = 8;

        int RAR_HASH_NONE = 0;
        int RAR_HASH_CRC32 = 1;
        int RAR_HASH_BLAKE2 = 2;


        int RHDF_SPLITBEFORE = 0x01;
        int RHDF_SPLITAFTER = 0x02;
        int RHDF_ENCRYPTED = 0x04;
        int RHDF_SOLID = 0x10;
        int RHDF_DIRECTORY = 0x20;

        int ROADF_VOLUME = 0x0001;
        int ROADF_COMMENT = 0x0002;
        int ROADF_LOCK = 0x0004;
        int ROADF_SOLID = 0x0008;
        int ROADF_NEWNUMBERING = 0x0010;
        int ROADF_SIGNED = 0x0020;
        int ROADF_RECOVERY = 0x0040;
        int ROADF_ENCHEADERS = 0x0080;
        int ROADF_FIRSTVOLUME = 0x0100;

        int ROADOF_KEEPBROKEN = 0x0001;
    }

    interface OperatingSystem {
        int MS_DOS = 0;
        int OS_2 = 1;
        int WINDOWS = 2;
        int UNIX = 3;
    }

    interface UnrarCallbackMessages {
        int UCM_CHANGEVOLUME = 0;
        int UCM_PROCESSDATA = 1;
        int UCM_NEEDPASSWORD = 2;
        int UCM_CHANGEVOLUMEW = 3;
        int UCM_PASSWORDW = 4;

    }
}

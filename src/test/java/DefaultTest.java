import com.sun.jna.Memory;
import constant.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rar.UnRAR;
import rar.UnRARLibrary;
import structs.RARHeaderData;
import structs.RAROpenArchiveData;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.Objects;

class DefaultTest {

    private final String fTest = "rar-files/test1.rar";
    private final String sTest = "rar-files/test2.rar";

    private final UnRAR library = UnRARLibrary.getInstance();
    private final String archName = Objects.requireNonNull(getClass().getResource(fTest)).getPath().substring(1);

    @Test
    void OpenFile_CloseFile() {
        var archive = RAROpenArchiveData
                .builder()
                .ArcName(archName)
                .OpenMode(Constants.OperationCode.RAR_OM_EXTRACT)
                .build();

        var file = library.RAROpenArchive(archive);

        Assertions.assertNotNull(file);

        library.RARCloseArchive(file);
    }

    @Test
    void CheckComment() {
        var mSize = 1024;
        var memory = new Memory(mSize);

        var archive = RAROpenArchiveData
                .builder()
                .ArcName(archName)
                .CmtBuf(memory)
                .CmtBufSize(mSize)
                .OpenMode(Constants.OperationCode.RAR_OM_EXTRACT)
                .build();

        var file = library.RAROpenArchive(archive);

        Assertions.assertNotNull(file);
        Assertions.assertEquals(archive.OpenResult, 0);
        Assertions.assertEquals(archive.CmtState, 1);

        library.RARCloseArchive(file);
    }

    @Test
    void Check_CanREAD_FileHeader() {

        var mSize = 1024;
        var memory = new Memory(mSize);

        var archive = RAROpenArchiveData
                .builder()
                .ArcName(archName)
                .CmtBuf(memory)
                .CmtBufSize(mSize)
                .OpenMode(Constants.OperationCode.RAR_OM_LIST)
                .build();

        var file = library.RAROpenArchive(archive);

        Assertions.assertNotNull(file);

        var header = RARHeaderData.ByReference.builder().build();
        int hCode = library.RARReadHeader(file, header);


        Assertions.assertEquals(hCode, 0);

        library.RARCloseArchive(file);
    }

    @Test
    void Extract_CalculateCRC32() throws IOException {
        var mSize = 1024;
        var memory = new Memory(mSize);

        var archive = RAROpenArchiveData
                .builder()
                .ArcName(archName)
                .OpenMode(Constants.OperationCode.RAR_OM_EXTRACT)
                .CmtBufSize(mSize)
                .CmtBuf(memory)
                .build();

        var file = library.RAROpenArchive(archive);

        Assertions.assertNotNull(file);

        var header = RARHeaderData.builder().build();
        var tempDir = Files.createTempDirectory("java.jrar.temp.").toAbsolutePath();

        while (library.RARReadHeader(file, header) == 0) {
            library.RARProcessFile(file, Constants.OperationCode.RAR_EXTRACT, tempDir.toString(), null);
        }

        try (var tdIterator = Files.walk(tempDir).sorted(Comparator.reverseOrder())) {
            tdIterator.forEach(item -> {
                try {
                    Files.deleteIfExists(item);
                } catch (IOException ignored) {
                }
            });
        } catch (Exception ignored) {
        }
        library.RARCloseArchive(file);
    }

    @Test
    void CheckVersion_MustBe8() {
        int expected = Constants.OperationCode.RAR_DLL_VERSION;
        int actual = library.RARGetDllVersion();
        Assertions.assertEquals(expected, actual);
    }

}
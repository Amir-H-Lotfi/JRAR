import com.sun.jna.Memory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;

import constant.Constants;
import rar.UnRAR;
import rar.UnRARLibrary;
import structs.RARHeaderData;
import structs.RAROpenArchiveData;

class RARTest {

    static UnRAR library;

    @BeforeAll
    static void initialize() throws Exception {
        library = UnRARLibrary.getInstance();
    }

    @ParameterizedTest
    @ValueSource(strings = {"test-files/test1.rar", "test-files/test2.rar", "test-files/test3.rar"})
    @DisplayName("just open and close files")
    void openFile_ThenCloseFile(String file) throws Exception {
        var source = getClass().getClassLoader().getResource(file).getPath().substring(1);
        var archive = RAROpenArchiveData.builder().ArcName(source).OpenMode(Constants.OperationCode.RAR_OM_LIST).build();

        var fileHandler = library.RAROpenArchive(archive);

        Assertions.assertNotNull(fileHandler);
        Assertions.assertEquals(archive.OpenResult, 0);

        var close = library.RARCloseArchive(fileHandler);

        Assertions.assertEquals(close, 0);

    }

    @ParameterizedTest
    @ValueSource(strings = {"test-files/test1.rar", "test-files/test2.rar", "test-files/test3.rar"})
    @DisplayName("read and check comments")
    void readComments(String file) throws Exception {
        var memorySize = 1024;
        var memory = new Memory(memorySize);

        var source = getClass().getClassLoader().getResource(file).getPath().substring(1);

        var archive = RAROpenArchiveData
                .builder()
                .ArcName(source)
                .CmtBuf(memory)
                .CmtBufSize(memorySize)
                .OpenMode(Constants.OperationCode.RAR_OM_LIST)
                .build();

        var fileHandler = library.RAROpenArchive(archive);

        Assertions.assertNotNull(fileHandler);

        Assertions.assertEquals(archive.CmtState, 1);

        library.RARCloseArchive(fileHandler);

        memory.clear();
        memory.close();
    }

    @ParameterizedTest
    @ValueSource(strings = {"test-files/test1.rar", "test-files/test2.rar", "test-files/test3.rar"})
    void readHeader(String file) {
        var source = getClass().getClassLoader().getResource(file).getPath().substring(1);

        var memorySize = 1024;
        var memory = new Memory(memorySize);

        var archive = RAROpenArchiveData
                .builder()
                .ArcName(source)
                .CmtBuf(memory)
                .CmtBufSize(memorySize)
                .OpenMode(Constants.OperationCode.RAR_OM_LIST)
                .build();

        var header = RARHeaderData
                .builder()
                .build();

        var fileHandler = library.RAROpenArchive(archive);

        int headerRead = library.RARReadHeader(fileHandler, header);

        Assertions.assertEquals(headerRead, 0);

        library.RARCloseArchive(fileHandler);
        memory.clear();
        memory.close();

    }

    @ParameterizedTest
    @ValueSource(strings = {"test-files/test1.rar", "test-files/test2.rar", "test-files/test3.rar"})
    void extract(String file) throws Exception {
        var source = getClass().getClassLoader().getResource(file).getPath().substring(1);

        var mSize = 1024;
        var memory = new Memory(mSize);

        var archive = RAROpenArchiveData
                .builder()
                .ArcName(source)
                .OpenMode(Constants.OperationCode.RAR_OM_EXTRACT)
                .CmtBufSize(mSize)
                .CmtBuf(memory)
                .build();

        var fileHandler = library.RAROpenArchive(archive);

        var header = RARHeaderData.builder().build();
        var tempDir = Files.createTempDirectory("java.jrar.temp.").toAbsolutePath();

        while (library.RARReadHeader(fileHandler, header) == 0) {
            int pCode = library.RARProcessFile(fileHandler, Constants.OperationCode.RAR_EXTRACT, tempDir.toString(), null);
            Assertions.assertEquals(pCode, 0);
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
        library.RARCloseArchive(fileHandler);
    }

    /**
     * This dll use polynomial initial value for calculating crc. we cannot use java.util.zip.CRC23C or java.util.zip.CRC32 Classes.
     *
     * @param file
     * @throws Exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"test-files/test3.rar"})
    void checkCRC32(String file) throws Exception {

        var source = getClass().getClassLoader().getResource(file).getPath().substring(1);

        var mSize = 1024;
        var memory = new Memory(mSize);

        var archive = RAROpenArchiveData
                .builder()
                .ArcName(source)
                .OpenMode(Constants.OperationCode.RAR_OM_EXTRACT)
                .CmtBufSize(mSize)
                .CmtBuf(memory)
                .build();

        var fileHandler = library.RAROpenArchive(archive);

        var header = RARHeaderData.builder().build();
        var tempDir = Files.createTempDirectory("java.jrar.temp.").toAbsolutePath();

        while (library.RARReadHeader(fileHandler, header) == 0) {
            int pCode = library.RARProcessFile(fileHandler, Constants.OperationCode.RAR_EXTRACT, tempDir.toString(), null);
            Assertions.assertEquals(header.FileCRC, 0x8818EA88);
            Assertions.assertEquals(pCode, 0);
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
        library.RARCloseArchive(fileHandler);

        memory.clear();
        memory.close();

    }

    @Test
    @Disabled
    void rarSetCallback() {

    }

    @Test
    @Disabled
    void rarSetChangeVolProc() {

    }


    @Test
    @Disabled
    void rarSetChangeProcessDataProc() {

    }


    @Test
    @Disabled
    void rarSeetPassword() {
    }

    @Test
    void checkVersionMustBeEight8() {
        Assertions.assertEquals(library.RARGetDllVersion(), 8);
    }

}
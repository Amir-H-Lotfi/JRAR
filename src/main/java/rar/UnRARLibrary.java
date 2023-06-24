package rar;

import com.sun.jna.Native;

public class UnRARLibrary {

    private final static UnRAR INSTANCE;

    static {
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows"))
            INSTANCE = Native.load("lib/UnRAR64.dll", UnRAR.class);
        else INSTANCE = null;
    }

    private UnRARLibrary() {

    }

    public static UnRAR getInstance() throws Exception {
        if (INSTANCE == null) {
            throw new Exception("Failed to load library");
        }
        return INSTANCE;
    }


}

package rar;

import com.sun.jna.Native;

public class UnRARLibrary {

    private final static UnRAR INSTANCE;

    static {
        INSTANCE = Native.load("dll/UnRAR64.dll", UnRAR.class);
    }

    private UnRARLibrary() {

    }

    public static UnRAR getInstance() {
        return INSTANCE;
    }


}

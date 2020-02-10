package com.test.directorysize;

import java.util.HashMap;

public class MapNSize {
    public MapNSize(HashMap<String, String> directoryNsize, long length) {
        this.directoryNsize = directoryNsize;
        this.length = length;
    }

    private HashMap<String,String> directoryNsize;
    private long length;

    public HashMap<String, String> getDirectoryNsize() {
        return directoryNsize;
    }

    public void setDirectoryNsize(HashMap<String, String> directoryNsize) {
        this.directoryNsize = directoryNsize;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
}

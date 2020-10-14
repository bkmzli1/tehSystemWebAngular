package example.awesomeproj.util;

import java.io.File;
import java.net.URI;

public class FileCreate extends File {

    public FileCreate(String pathname) {
        super(pathname);
        if (!this.mkdir()) { this.mkdirs();

        }
    }

    public FileCreate(String parent, String child) {
        super(parent, child);
    }

    public FileCreate(File parent, String child) {
        super(parent, child);
    }

    public FileCreate(URI uri) {
        super(uri);
    }
}

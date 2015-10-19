package testing;

import java.io.File;

public interface SessionStorage {
File read();
void store(File file);
}

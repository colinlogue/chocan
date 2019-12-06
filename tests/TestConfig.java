import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class TestConfig {

    // copies the sqlite db to a backup path before testing
    // then copies test db to replace source db
    public static void copy_db() {
        Path app_path = Paths.get(System.getProperty("user.dir"));
        Path db_path = Paths.get("db/chocan.sqlite3");
        Path backup_path = Paths.get("db/backup.sqlite3");
        Path test_data_path = Paths.get("db/test.sqlite3");
        Path source_path = app_path.resolve(db_path);
        Path dest_path = app_path.resolve(backup_path);
        Path test_path = app_path.resolve(test_data_path);
        try {
            // backup original
            Files.copy(source_path, dest_path, StandardCopyOption.REPLACE_EXISTING);
            // move test db to production
            Files.copy(test_path, source_path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // moves the production chocan db back into place
    public static void reset_db() {
        Path app_path = Paths.get(System.getProperty("user.dir"));
        Path db_path = Paths.get("db/chocan.sqlite3");
        Path backup_path = Paths.get("db/backup.sqlite3");
        Path source_path = app_path.resolve(db_path);
        Path dest_path = app_path.resolve(backup_path);
        try {
            Files.copy(dest_path, source_path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

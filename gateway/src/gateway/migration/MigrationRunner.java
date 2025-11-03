package gateway.migration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MigrationRunner {

    private final Connection connection;

    public MigrationRunner(Connection connection) {
        this.connection = connection;
    }

    public void run() {
        // مسیر واقعی فایل SQL داخل پروژه
        Path sqlPath = Path.of("migrations", "create_messages.sql");

        try {
            // خواندن کل فایل SQL
            String sql = Files.readString(sqlPath);

            try (Statement stmt = connection.createStatement()) {
                stmt.execute(sql);
                System.out.println("✅ Migration executed successfully: " + sqlPath);
            }

        } catch (IOException e) {
            System.err.println("❌ Could not read SQL file: " + sqlPath);
            e.printStackTrace();

        } catch (SQLException e) {
            System.err.println("❌ Database migration failed:");
            e.printStackTrace();
        }
    }
}

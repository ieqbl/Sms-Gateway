package gateway.migration;

import java.io.File;
import java.nio.file.Files;
import java.sql.*;
import java.util.Arrays;

public class MigrationRunner
{
    private final Connection connection;
    private final String migrationsPath = "migrations";

    public MigrationRunner(Connection connection)
    {
        this.connection = connection;
    }

    public void run() throws Exception
    {
        File folder = new File(migrationsPath);
        File[] sqlFiles = folder.listFiles((dir, name) -> name.endsWith(".sql"));
        if (sqlFiles == null || sqlFiles.length == 0)
        {
            System.out.println("No migration files found.");
            return;
        }

        Arrays.sort(sqlFiles);

        for (File file : sqlFiles)
        {
            String fileName = file.getName();
            if (isAlreadyApplied(fileName))
            {
                System.out.println("Already applied: " + fileName);
                continue;
            }

            System.out.println("Applying: " + fileName);
            String sql = Files.readString(file.toPath());

            try (Statement stmt = connection.createStatement())
            {
                stmt.execute(sql);
                markAsApplied(fileName);
            }
            catch (SQLException e)
            {
                System.out.println("Failed on: " + fileName);
                e.printStackTrace();
                break;
            }
        }
    }

    private boolean isAlreadyApplied(String fileName) throws SQLException
    {
        String check = "SELECT 1 FROM migrations WHERE filename = ?";
        try (PreparedStatement statement = connection.prepareStatement(check))
        {
            statement.setString(1, fileName);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        }
    }

    private void markAsApplied(String fileName) throws SQLException
    {
        String insert = "INSERT INTO migrations (filename) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(insert))
        {
            stmt.setString(1, fileName);
            stmt.executeUpdate();
        }
    }
}

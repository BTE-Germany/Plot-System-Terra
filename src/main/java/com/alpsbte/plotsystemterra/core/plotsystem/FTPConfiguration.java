package com.alpsbte.plotsystemterra.core.plotsystem;

import com.alpsbte.plotsystemterra.core.DatabaseConnection;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FTPConfiguration {
    private final int ID;

    private String schematicPath;
    private String address;
    private int port;
    private String username;
    private String password;

    public FTPConfiguration(int ID) throws SQLException {
        this.ID = ID;

        ResultSet rs = DatabaseConnection.createStatement("SELECT schematics_path, address, port, username, password FROM plotsystem_ftp_configurations WHERE id = ?")
                .setValue(this.ID).executeQuery();

        if (rs.next()) {
            this.schematicPath = rs.getString(1);
            this.address = rs.getString(2);
            this.port = rs.getInt(3);
            this.username = rs.getString(4);
            this.password = rs.getString(5);
        }
    }

    public int getID() {
        return ID;
    }

    public String getSchematicPath() {
        if (schematicPath != null) {
            schematicPath = !schematicPath.startsWith("/") ? File.separator + schematicPath : schematicPath;
            schematicPath = schematicPath.endsWith("/") ? schematicPath.substring(0, schematicPath.length() - 1) : schematicPath;
        }
        return schematicPath;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
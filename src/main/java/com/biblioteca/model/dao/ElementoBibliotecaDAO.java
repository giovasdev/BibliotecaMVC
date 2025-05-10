package com.biblioteca.model.dao;

import com.biblioteca.model.ElementoBiblioteca;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ElementoBibliotecaDAO<T extends ElementoBiblioteca> {
    protected Connection conexion;

    public ElementoBibliotecaDAO() throws SQLException {
        this.conexion = ConexionBD.getConnection();
    }

    protected int insertarElementoBase(T elemento) throws SQLException {
        String query = "INSERT INTO ElementoBiblioteca (titulo, autor, ano_publicacion, tipo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, elemento.getTitulo());
            stmt.setString(2, elemento.getAutor());
            stmt.setInt(3, elemento.getAnoPublicacion());
            stmt.setString(4, elemento.getTipo());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("La inserción falló, no se guardó ningún registro.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("La inserción falló, no se pudo obtener el ID generado.");
                }
            }
        }
    }

    protected void actualizarElementoBase(T elemento) throws SQLException {
        String query = "UPDATE ElementoBiblioteca SET titulo = ?, autor = ?, ano_publicacion = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, elemento.getTitulo());
            stmt.setString(2, elemento.getAutor());
            stmt.setInt(3, elemento.getAnoPublicacion());
            stmt.setInt(4, elemento.getId());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("La actualización falló, no se encontró el elemento con ID " + elemento.getId());
            }
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String query = "DELETE FROM ElementoBiblioteca WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        }
    }

    public void close() throws SQLException {
        ConexionBD.closeConnection();
    }

    public abstract T obtenerPorId(int id) throws SQLException;
    public abstract List<T> obtenerTodos() throws SQLException;
    public abstract boolean insertar(T elemento) throws SQLException;
    public abstract boolean actualizar(T elemento) throws SQLException;
}
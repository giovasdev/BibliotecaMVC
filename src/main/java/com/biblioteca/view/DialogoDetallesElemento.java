package com.biblioteca.view;

import com.biblioteca.model.ElementoBiblioteca;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Revista;
import com.biblioteca.model.DVD;
import javax.swing.*;
import java.awt.*;

public class DialogoDetallesElemento extends JDialog {
    private MainFrame parent;
    private ElementoBiblioteca elemento;

    public DialogoDetallesElemento(MainFrame parent, ElementoBiblioteca elemento) {
        super(parent, "Detalles del Elemento", true);
        this.parent = parent;
        this.elemento = elemento;

        setLayout(new BorderLayout());
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        JPanel panelDetalles = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        gbc.gridx = 0;
        gbc.gridy = row;
        panelDetalles.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        panelDetalles.add(new JLabel(elemento.getTitulo()), gbc);
        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        panelDetalles.add(new JLabel("Autor:"), gbc);
        gbc.gridx = 1;
        panelDetalles.add(new JLabel(elemento.getAutor()), gbc);
        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        panelDetalles.add(new JLabel("Año de publicación:"), gbc);
        gbc.gridx = 1;
        panelDetalles.add(new JLabel(String.valueOf(elemento.getAnoPublicacion())), gbc);
        row++;

        if (elemento instanceof Libro) {
            Libro libro = (Libro) elemento;
            gbc.gridx = 0;
            gbc.gridy = row;
            panelDetalles.add(new JLabel("ISBN:"), gbc);
            gbc.gridx = 1;
            panelDetalles.add(new JLabel(libro.getIsbn()), gbc);
            row++;

            gbc.gridx = 0;
            gbc.gridy = row;
            panelDetalles.add(new JLabel("Número de páginas:"), gbc);
            gbc.gridx = 1;
            panelDetalles.add(new JLabel(String.valueOf(libro.getNumeroPaginas())), gbc);
            row++;

            gbc.gridx = 0;
            gbc.gridy = row;
            panelDetalles.add(new JLabel("Género:"), gbc);
            gbc.gridx = 1;
            panelDetalles.add(new JLabel(libro.getGenero()), gbc);
            row++;

            gbc.gridx = 0;
            gbc.gridy = row;
            panelDetalles.add(new JLabel("Editorial:"), gbc);
            gbc.gridx = 1;
            panelDetalles.add(new JLabel(libro.getEditorial()), gbc);
            row++;
        } else if (elemento instanceof Revista) {
            Revista revista = (Revista) elemento;
            gbc.gridx = 0;
            gbc.gridy = row;
            panelDetalles.add(new JLabel("Número de edición:"), gbc);
            gbc.gridx = 1;
            panelDetalles.add(new JLabel(String.valueOf(revista.getNumeroEdicion())), gbc);
            row++;

            gbc.gridx = 0;
            gbc.gridy = row;
            panelDetalles.add(new JLabel("Categoría:"), gbc);
            gbc.gridx = 1;
            panelDetalles.add(new JLabel(revista.getCategoria()), gbc);
            row++;
        } else if (elemento instanceof DVD) {
            DVD dvd = (DVD) elemento;
            gbc.gridx = 0;
            gbc.gridy = row;
            panelDetalles.add(new JLabel("Duración (min):"), gbc);
            gbc.gridx = 1;
            panelDetalles.add(new JLabel(String.valueOf(dvd.getDuracion())), gbc);
            row++;

            gbc.gridx = 0;
            gbc.gridy = row;
            panelDetalles.add(new JLabel("Género:"), gbc);
            gbc.gridx = 1;
            panelDetalles.add(new JLabel(dvd.getGenero()), gbc);
            row++;
        }

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        panelBotones.add(btnCerrar);

        add(panelDetalles, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void configurarVentana() {
        setSize(400, elemento instanceof Libro ? 300 : 200);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void mostrar() {
        setVisible(true);
    }
}
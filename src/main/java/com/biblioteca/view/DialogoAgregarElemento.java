package com.biblioteca.view;

import com.biblioteca.model.ElementoBiblioteca;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Revista;
import com.biblioteca.model.DVD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DialogoAgregarElemento extends JDialog {
    private MainFrame parent;
    private String tipo;
    private ElementoBiblioteca elemento;
    private ElementoBiblioteca resultado;

    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAno;
    private JTextField txtIsbn;
    private JTextField txtPaginas;
    private JTextField txtGeneroLibro;
    private JTextField txtEditorial;
    private JTextField txtEdicion;
    private JTextField txtCategoria;
    private JTextField txtDuracion;
    private JTextField txtGeneroDVD;

    private JButton btnAceptar;
    private JButton btnCancelar;

    public DialogoAgregarElemento(MainFrame parent, String titulo, String tipo) {
        this(parent, titulo, tipo, null);
    }

    public DialogoAgregarElemento(MainFrame parent, String titulo, String tipo, ElementoBiblioteca elemento) {
        super(parent, titulo, true);
        this.parent = parent;
        this.tipo = tipo;
        this.elemento = elemento;

        setLayout(new BorderLayout());
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtTitulo = new JTextField(20);
        txtAutor = new JTextField(20);
        txtAno = new JTextField(20);
        txtIsbn = new JTextField(20);
        txtPaginas = new JTextField(20);
        txtGeneroLibro = new JTextField(20);
        txtEditorial = new JTextField(20);
        txtEdicion = new JTextField(20);
        txtCategoria = new JTextField(20);
        txtDuracion = new JTextField(20);
        txtGeneroDVD = new JTextField(20);

        int row = 0;

        // Campos comunes
        gbc.gridx = 0;
        gbc.gridy = row;
        panelFormulario.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtTitulo, gbc);
        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        panelFormulario.add(new JLabel("Autor:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtAutor, gbc);
        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        panelFormulario.add(new JLabel("Año de publicación:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtAno, gbc);
        row++;

        // Campos específicos según tipo
        if (tipo.equals("LIBRO")) {
            gbc.gridx = 0;
            gbc.gridy = row;
            panelFormulario.add(new JLabel("ISBN:"), gbc);
            gbc.gridx = 1;
            panelFormulario.add(txtIsbn, gbc);
            row++;

            gbc.gridx = 0;
            gbc.gridy = row;
            panelFormulario.add(new JLabel("Número de páginas:"), gbc);
            gbc.gridx = 1;
            panelFormulario.add(txtPaginas, gbc);
            row++;

            gbc.gridx = 0;
            gbc.gridy = row;
            panelFormulario.add(new JLabel("Género:"), gbc);
            gbc.gridx = 1;
            panelFormulario.add(txtGeneroLibro, gbc);
            row++;

            gbc.gridx = 0;
            gbc.gridy = row;
            panelFormulario.add(new JLabel("Editorial:"), gbc);
            gbc.gridx = 1;
            panelFormulario.add(txtEditorial, gbc);
            row++;
        } else if (tipo.equals("REVISTA")) {
            gbc.gridx = 0;
            gbc.gridy = row;
            panelFormulario.add(new JLabel("Número de edición:"), gbc);
            gbc.gridx = 1;
            panelFormulario.add(txtEdicion, gbc);
            row++;

            gbc.gridx = 0;
            gbc.gridy = row;
            panelFormulario.add(new JLabel("Categoría:"), gbc);
            gbc.gridx = 1;
            panelFormulario.add(txtCategoria, gbc);
            row++;
        } else if (tipo.equals("DVD")) {
            gbc.gridx = 0;
            gbc.gridy = row;
            panelFormulario.add(new JLabel("Duración (min):"), gbc);
            gbc.gridx = 1;
            panelFormulario.add(txtDuracion, gbc);
            row++;

            gbc.gridx = 0;
            gbc.gridy = row;
            panelFormulario.add(new JLabel("Género:"), gbc);
            gbc.gridx = 1;
            panelFormulario.add(txtGeneroDVD, gbc);
            row++;
        }

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        btnAceptar.addActionListener(e -> aceptar());
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Rellenar campos si se está editando
        if (elemento != null) {
            txtTitulo.setText(elemento.getTitulo());
            txtAutor.setText(elemento.getAutor());
            txtAno.setText(String.valueOf(elemento.getAnoPublicacion()));

            if (elemento instanceof Libro) {
                Libro libro = (Libro) elemento;
                txtIsbn.setText(libro.getIsbn());
                txtPaginas.setText(String.valueOf(libro.getNumeroPaginas()));
                txtGeneroLibro.setText(libro.getGenero());
                txtEditorial.setText(libro.getEditorial());
            } else if (elemento instanceof Revista) {
                Revista revista = (Revista) elemento;
                txtEdicion.setText(String.valueOf(revista.getNumeroEdicion()));
                txtCategoria.setText(revista.getCategoria());
            } else if (elemento instanceof DVD) {
                DVD dvd = (DVD) elemento;
                txtDuracion.setText(String.valueOf(dvd.getDuracion()));
                txtGeneroDVD.setText(dvd.getGenero());
            }
        }
    }

    private void configurarVentana() {
        setSize(400, tipo.equals("LIBRO") ? 300 : 200);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void aceptar() {
        try {
            String titulo = txtTitulo.getText().trim();
            String autor = txtAutor.getText().trim();
            int ano = Integer.parseInt(txtAno.getText().trim());

            if (titulo.isEmpty() || autor.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "El título y el autor son obligatorios",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (tipo.equals("LIBRO")) {
                String isbn = txtIsbn.getText().trim();
                int paginas = Integer.parseInt(txtPaginas.getText().trim());
                String genero = txtGeneroLibro.getText().trim();
                String editorial = txtEditorial.getText().trim();

                if (isbn.isEmpty() || paginas <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "ISBN y número de páginas son obligatorios",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                resultado = new Libro(
                        elemento != null ? elemento.getId() : -1,
                        titulo, autor, ano, isbn, paginas, genero, editorial
                );
            } else if (tipo.equals("REVISTA")) {
                int edicion = Integer.parseInt(txtEdicion.getText().trim());
                String categoria = txtCategoria.getText().trim();

                if (edicion <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "El número de edición debe ser mayor que 0",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                resultado = new Revista(
                        elemento != null ? elemento.getId() : -1,
                        titulo, autor, ano, edicion, categoria
                );
            } else if (tipo.equals("DVD")) {
                int duracion = Integer.parseInt(txtDuracion.getText().trim());
                String genero = txtGeneroDVD.getText().trim();

                if (duracion <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "La duración debe ser mayor que 0",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                resultado = new DVD(
                        elemento != null ? elemento.getId() : -1,
                        titulo, autor, ano, duracion, genero
                );
            }

            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese valores numéricos válidos",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ElementoBiblioteca mostrar() {
        setVisible(true);
        return resultado;
    }
}
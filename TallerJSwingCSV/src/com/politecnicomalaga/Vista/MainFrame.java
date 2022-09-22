package com.politecnicomalaga.Vista;

import com.politecnicomalaga.Controlador.Controlador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.border.Border;

import static javax.swing.JOptionPane.showMessageDialog;

public class MainFrame extends JFrame {

    // Atributos #######################################################################################################

    //Pixeles a usar en los Layouts como "hueco" entre componentes
    private static final byte GAP_SEPARACION = 10;

    // Cosas del Panel Base --------------------------------------------------------------------------------------------
    protected JPanel panelBase;

    // Cosas del Panel Menu --------------------------------------------------------------------------------------------
    protected JPanel panelMenu; //botonera
    protected JButton botonMenuFormulario;
    protected JButton botonMenuCobro;
    protected JButton botonMenuYaCobrados;
    protected JButton botonAltaProveedor;  //botón nuevo para la botonera
    protected JButton botonListaProveedores; // botón nuevo para la lista de proveedores

    // Cosas del Panel Contenido ---------------------------------------------------------------------------------------
    protected JPanel panelContenido;

    // Panel 1 - Cosas del Panel Formulario de Alta ------------------------------------------------------------------------------
    protected JPanel panelFormularioAlta;
    protected JScrollPane scrollPanelFormularioAlta;

    protected JLabel labelTipo;
    protected JComboBox comboTipo;
    protected JLabel labelTituloFormulario;
    protected JLabel labelMatricula;
    protected JTextField tfMatricula;
    protected JLabel labelDni;
    protected JTextField tfDni;
    protected JLabel labelPropietario;
    protected JTextField tfPropietario;
    protected JLabel labelModelo;
    protected JTextField tfModelo;
    protected JLabel labelDiagnostico;
    protected JTextArea taDiagnostico;
    protected JScrollPane scrollDiagnostico;
    protected JLabel labelSolucion;
    protected JTextArea taSolucion;
    protected JScrollPane scrollSolucion;
    protected JLabel labelHorasPrevistas;
    protected JTextField tfHorasPrevistas;
    protected JButton botonAlta;

    // Panel 2 - Cosas del Panel para cobrar trabajos ----------------------------------------------------------------------------
    protected JPanel panelCobroTrabajos;
    protected JButton botonCobroTrabajo;
    protected JLabel tCobroTrabajos;
    protected JLabel tSeleccione;
    protected JLabel tTotal;
    protected JList listaCobros;
    protected JScrollPane scrollCobroLista;

    // Panel 3 - Cosas del Panel para trabajos ya cobrados -----------------------------------------------------------------------
    protected JPanel panelTrabajosCobrados;
    protected JLabel labelTrabajosYaCobrados;
    protected JLabel labelTrabajosCobrados;
    protected JList  listaCobrados;
    protected JScrollPane scrollCobradosLista;

    // Panel 4 - panel AltaProveedor
    protected JPanel panelAltaProveedor;
    protected JLabel lNombreEmpresa;
    protected JTextField tfNombreEmpresa;
    protected JLabel lFechaAlta;
    protected JTextField tfFechaAlta;
    protected JLabel lDireccionPostal;
    protected JTextField tfDireccionPostal;
    protected JLabel lNumeroTelefono;
    protected JTextField tfNumeroTelefono;
    protected JLabel lEmail;
    protected JTextField tfEmail;
    protected JLabel lContactoComercial; //nombre y apellidos
    protected JTextField tfContactoComercial;
    protected JButton btnAceptar;

    // Panel 5 - panel para la lista de proveedores

    protected JPanel panelListaProveedores;
    protected JLabel lProveedoresDisponibles;
    protected JList  listaProveedores;
    protected JScrollPane scrollProveedoresLista;




    // Constructor #####################################################################################################
    public MainFrame(String title) {

        //Para que se muestre el título
        super(title);

        // Guardamos la ventana en una variable para usarla
        Component ventanaDelPrograma = getContentPane();

        // BORDES ######################################################################################################

        // Usando un borde vacío podremos simular una zona de separación entre componentes (padding)
        Border padding = BorderFactory.createEmptyBorder(GAP_SEPARACION, GAP_SEPARACION, GAP_SEPARACION, GAP_SEPARACION);

        // CREACION DEL FRAME ##########################################################################################

        this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





        // CREACION DE PANELES Y LAYOUTS ###############################################################################

        // Panel Base (Usa un layout de tipo BorderLayout) -------------------------------------------------------------

        panelBase = new JPanel(new BorderLayout());

        // Panel Menu (Usa un layout de tipo FlowLayout) ---------------------------------------------------------------

        panelMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP_SEPARACION, GAP_SEPARACION));

        // Panel Contenido (Usa un layout de tipo GridBagLayout) -------------------------------------------------------

        panelContenido = new JPanel();

        /*
            Usando GridBagLayout podemos poner los paneles centrados de una forma más cómoda.

            Lo instanciamos aparte porque tendremos que usarlo más adelante en el listener que controla el tamaño
            de la ventana.
         */

        GridBagLayout gbl = new GridBagLayout();
        panelContenido.setLayout(gbl);

        // Panel Formulario (Usa un layout de tipo BoxLayout) ----------------------------------------------------------

        panelFormularioAlta = new JPanel();
        panelFormularioAlta.setLayout(new BoxLayout(panelFormularioAlta, BoxLayout.Y_AXIS));
        scrollPanelFormularioAlta = new JScrollPane(panelFormularioAlta);  // <- Ponemos un scroll al panel formulario para mejorar el responsive

        // Panel Cobros (Usa un layout de tipo BoxLayout) --------------------------------------------------------------

        panelCobroTrabajos = new JPanel();
        panelCobroTrabajos.setLayout(new BoxLayout(panelCobroTrabajos,BoxLayout.Y_AXIS));

        // Panel Trabajos Cobrados (Usa un layout de tipo BoxLayout) ---------------------------------------------------

        panelTrabajosCobrados = new JPanel();
        panelTrabajosCobrados.setLayout(new BoxLayout(panelTrabajosCobrados,BoxLayout.Y_AXIS));

        // Panel Alta Proveedor
        panelAltaProveedor = new JPanel();
        panelAltaProveedor.setLayout(new BoxLayout(panelAltaProveedor,BoxLayout.Y_AXIS));

        // Panel Lista Proveedores
        panelListaProveedores = new JPanel();
        panelListaProveedores.setLayout(new BoxLayout(panelListaProveedores,BoxLayout.Y_AXIS));


        // CREACION DE LOS COMPONENTES #################################################################################


        // Componentes del panelMenu -----------------------------------------------------------------------------------

        botonMenuFormulario = new JButton("Alta Trabajo");
        botonMenuCobro = new JButton("Cobro Trabajo de Taller");
        botonMenuYaCobrados = new JButton("Trabajos ya cobrados");
        botonAltaProveedor = new JButton("Alta Proveedor"); // creación botón
        botonListaProveedores = new JButton("Lista Proveedores"); // creación botón

        // Componentes del panelFormularioAlta -------------------------------------------------------------------------

        labelTituloFormulario = new JLabel("ALTA DE NUEVOS VEHICULOS");
        labelTipo = new JLabel("Tipo de Vehiculo:");
        comboTipo = new JComboBox(Controlador.getSingleton().getTiposVehiculo());
        labelMatricula = new JLabel("Matrícula:");
        tfMatricula = new JTextField("matricula");
        labelDni = new JLabel("DNI Propietario:");
        tfDni = new JTextField("12345678K");
        labelPropietario = new JLabel("Propietario:");
        tfPropietario = new JTextField("Persona");
        labelModelo = new JLabel("Modelo:");
        tfModelo = new JTextField("X1A");
        labelDiagnostico = new JLabel("Diagnóstico:");
        taDiagnostico = new JTextArea("No arranca");
        scrollDiagnostico = new JScrollPane(taDiagnostico); // <- Con este scroll evitamos el bug de que se muevan las etiquetas al escribir en el textarea
        labelSolucion = new JLabel("Solución:");
        taSolucion = new JTextArea("Arrancar");
        scrollSolucion = new JScrollPane(taSolucion);
        labelHorasPrevistas = new JLabel("Horas previstas:");
        tfHorasPrevistas = new JTextField();
        botonAlta = new JButton("Dar de Alta");

        // Componentes del panelCobroTrabajos --------------------------------------------------------------------------

        botonCobroTrabajo = new JButton("Cobrar");
        tCobroTrabajos = new JLabel("Cobro de trabajos");
        tSeleccione = new JLabel("Seleccione vehículo y trabajo realizado:");
        tTotal = new JLabel("Total trabajos por cobrar : 0");
        listaCobros = new JList();
        scrollCobroLista = new JScrollPane(listaCobros);

        // Componentes del panelTrabajosCobrados -----------------------------------------------------------------------

        labelTrabajosYaCobrados = new JLabel("Trabajos ya cobrados");
        labelTrabajosCobrados = new JLabel("Total trabajos realizados : 0");
        listaCobrados = new JList();
        scrollCobradosLista = new JScrollPane(listaCobrados);

        // Componentes del panel Alta Proveedor ------------------------------------------------------------------------
        lNombreEmpresa = new JLabel("Nombre de empresa:");
        tfNombreEmpresa = new JTextField();
        lFechaAlta = new JLabel("Fecha alta:");
        tfFechaAlta = new JTextField();
        lDireccionPostal = new JLabel("Direccion Postal:");
        tfDireccionPostal = new JTextField();
        lNumeroTelefono = new JLabel("Número de teléfono:");
        tfNumeroTelefono = new JTextField();
        lEmail = new JLabel("Email:");
        tfEmail = new JTextField();
        lContactoComercial = new JLabel("Contacto comercial:");
        tfContactoComercial = new JTextField();
        btnAceptar = new JButton("Aceptar");

        // Componentes del panelListaProveedores -----------------------------------------------------------------------

        lProveedoresDisponibles = new JLabel("Total proveedores disponibles : " /* + ControladorTaller.getSingleton().getProveedoresDisponibles().size()*/); //añadimos el contador
        listaProveedores = new JList(/*ControladorTaller.getSingleton().getProveedoresDisponibles.toArray()*/);
        scrollProveedoresLista = new JScrollPane(listaProveedores);


        // AÑADIR PANELES ##############################################################################################

        // Añadimos el panel base a la ventana (la ventana es el this)

        this.add(panelBase);

        // Añadimos los paneles del menu y contenido al panel base y los colocamos en su sitio

        panelBase.add(panelMenu, BorderLayout.NORTH);  // <- Añadimos el panel al norte (la parte de arriba)
        panelBase.add(panelContenido, BorderLayout.CENTER);  // <- Añadimos el panel al centro (en este caso es la parte de abajo)

        // La configuración de los paneles (GridBagConstraints) las asignaremos en el listener que controla el tamaño de la ventana.

        panelContenido.add(scrollPanelFormularioAlta);
        panelContenido.add(panelCobroTrabajos);
        panelContenido.add(panelTrabajosCobrados);
        panelContenido.add(panelAltaProveedor); // panel AltaProveedor
        panelContenido.add(panelListaProveedores);





        // AÑADIR COMPONENTES ##########################################################################################

        // Añadimos los componentes a los panelMenu --------------------------------------------------------------------

        panelMenu.add(botonMenuFormulario);
        panelMenu.add(botonMenuCobro);
        panelMenu.add(botonMenuYaCobrados);
        panelMenu.add(botonAltaProveedor); //boton Alta Proveedor
        panelMenu.add(botonListaProveedores);

        // Añadimos los componentes a los panelFormularioAlta ----------------------------------------------------------

        panelFormularioAlta.add(labelTituloFormulario);
        panelFormularioAlta.add(Box.createRigidArea(new Dimension(0, 30))); // <- Para que se vea mejor, es un separador
        panelFormularioAlta.add(labelTipo);
        panelFormularioAlta.add(comboTipo);
        panelFormularioAlta.add(labelMatricula);
        panelFormularioAlta.add(tfMatricula);
        panelFormularioAlta.add(labelDni);
        panelFormularioAlta.add(tfDni);
        panelFormularioAlta.add(labelPropietario);
        panelFormularioAlta.add(tfPropietario);
        panelFormularioAlta.add(labelModelo);
        panelFormularioAlta.add(tfModelo);
        panelFormularioAlta.add(labelDiagnostico);
        panelFormularioAlta.add(scrollDiagnostico);
        panelFormularioAlta.add(labelSolucion);
        panelFormularioAlta.add(scrollSolucion);
        panelFormularioAlta.add(labelHorasPrevistas);
        panelFormularioAlta.add(tfHorasPrevistas);
        panelFormularioAlta.add(Box.createRigidArea(new Dimension(0, 30)));
        panelFormularioAlta.add(botonAlta);

        //Añadimos los componentes al panelCobroTrabajos ---------------------------------------------------------------

        panelCobroTrabajos.add(tCobroTrabajos);
        panelCobroTrabajos.add(Box.createRigidArea(new Dimension(0, 20)));
        panelCobroTrabajos.add(tSeleccione);
        panelCobroTrabajos.add(Box.createRigidArea(new Dimension(0, 20)));
        panelCobroTrabajos.add(tTotal);
        panelCobroTrabajos.add(scrollCobroLista);
        panelCobroTrabajos.add(botonCobroTrabajo);

        //Añadimos los componentes al panelTrabajosCobrados ------------------------------------------------------------

        panelTrabajosCobrados.add(labelTrabajosYaCobrados);
        panelTrabajosCobrados.add(Box.createRigidArea(new Dimension(0, 20)));
        panelTrabajosCobrados.add(labelTrabajosCobrados);
        panelTrabajosCobrados.add(scrollCobradosLista);

        //Añadimos los componentes al panelAltaProveedor

        panelAltaProveedor.add(lNombreEmpresa);
        panelAltaProveedor.add(tfNombreEmpresa);
        panelAltaProveedor.add(lFechaAlta);
        panelAltaProveedor.add(tfFechaAlta);
        panelAltaProveedor.add(lDireccionPostal);
        panelAltaProveedor.add(tfDireccionPostal);
        panelAltaProveedor.add(lNumeroTelefono);
        panelAltaProveedor.add(tfNumeroTelefono);
        panelAltaProveedor.add(lEmail);
        panelAltaProveedor.add(tfEmail);
        panelAltaProveedor.add(lContactoComercial);
        panelAltaProveedor.add(tfContactoComercial);
        panelAltaProveedor.add(btnAceptar);

        // Añadimos los componentes del panelListaProveedores

        panelListaProveedores.add(lProveedoresDisponibles);
        //panelListaProveedores.add(listaProveedores);
        panelListaProveedores.add(scrollProveedoresLista);







        // CONFIGURACIONES DE LOS COMPONENTES ##########################################################################

        // Coloreamos los paneles para diferenciarlos

        panelBase.setBackground(new java.awt.Color(0, 0, 0));
        panelMenu.setBackground(new java.awt.Color(140, 197, 220));
        panelContenido.setBackground(new java.awt.Color(222, 221, 221));
        panelFormularioAlta.setBackground(new java.awt.Color(140, 197, 220));
        panelCobroTrabajos.setBackground(new java.awt.Color(140, 197, 220));
        panelTrabajosCobrados.setBackground(new java.awt.Color(140, 197, 220));
        panelAltaProveedor.setBackground(new java.awt.Color(140, 197, 220));
        panelListaProveedores.setBackground(new java.awt.Color(140, 197, 220));

        // Ponemos visible el panelFormularioAlta y ocultamos el resto, para que este sea el que se muestre por defecto

        panelFormularioAlta.setVisible(true);
        scrollPanelFormularioAlta.setVisible(true);  // <- ¡OJO! También tenemos que controlar la visibilidad del scroll

        panelCobroTrabajos.setVisible(false);
        panelTrabajosCobrados.setVisible(false);
        panelAltaProveedor.setVisible(false);
        panelListaProveedores.setVisible(false);

        // Con esto le metemos un padding a los paneles para que los componentes no queden muy pegados al borde

        panelFormularioAlta.setBorder(padding);
        panelCobroTrabajos.setBorder(padding);
        panelTrabajosCobrados.setBorder(padding);
        panelAltaProveedor.setBorder(padding);
        panelListaProveedores.setBorder(padding);

        // Para que los componentes no se coloquen donde no deberían tenemos que forzar que TODOS los componentes tengan una posición fija

        labelTituloFormulario.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelTipo.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboTipo.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelMatricula.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfMatricula.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDni.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfDni.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelPropietario.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfPropietario.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelModelo.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfModelo.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDiagnostico.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollDiagnostico.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelSolucion.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollSolucion.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelHorasPrevistas.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfHorasPrevistas.setAlignmentX(Component.LEFT_ALIGNMENT);
        botonAlta.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Para los componentes del panel de cobro también los tenemos que poner a la izquierda

        botonCobroTrabajo.setAlignmentX(Component.LEFT_ALIGNMENT);
        tCobroTrabajos.setAlignmentX(Component.LEFT_ALIGNMENT);
        tSeleccione.setAlignmentX(Component.LEFT_ALIGNMENT);
        tTotal.setAlignmentX(Component.LEFT_ALIGNMENT);
        listaCobros.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollCobroLista.setAlignmentX(Component.LEFT_ALIGNMENT);

        //Para los componentes del panel de cobrados, tenemos que poner los labels a la izquierda

        labelTrabajosCobrados.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelTrabajosYaCobrados.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollCobradosLista.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Para los componentes del panelAltaProvvedor, los situamos a la izquierda

        lNombreEmpresa.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfNombreEmpresa.setAlignmentX(Component.LEFT_ALIGNMENT);
        lFechaAlta.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfFechaAlta.setAlignmentX(Component.LEFT_ALIGNMENT);
        lDireccionPostal.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfDireccionPostal.setAlignmentX(Component.LEFT_ALIGNMENT);
        lNumeroTelefono.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfNumeroTelefono.setAlignmentX(Component.LEFT_ALIGNMENT);
        lEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        lContactoComercial.setAlignmentX(Component.LEFT_ALIGNMENT);
        tfContactoComercial.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnAceptar.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Para los componentes del panelListaProveedores
        lProveedoresDisponibles.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollProveedoresLista.setAlignmentX(Component.LEFT_ALIGNMENT);



        // LISTENERS ###################################################################################################

        // Listener para el botón del menu que te lleva a panelFormularioAlta ------------------------------------------

        botonMenuFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelFormularioAlta.setVisible(true);
                scrollPanelFormularioAlta.setVisible(true);

                panelCobroTrabajos.setVisible(false);
                panelTrabajosCobrados.setVisible(false);
                panelAltaProveedor.setVisible(false);
                panelListaProveedores.setVisible(false);
            }
        });

        // Listener para el botón del menu que te lleva a panelCobroTrabajos -------------------------------------------

        botonMenuCobro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelFormularioAlta.setVisible(false);
                scrollPanelFormularioAlta.setVisible(false);

                panelCobroTrabajos.setVisible(true);
                panelTrabajosCobrados.setVisible(false);
                panelAltaProveedor.setVisible(false);
                panelListaProveedores.setVisible(false);
                Controlador.getSingleton().cargarListaCobrar(listaCobros, tTotal);
            }
        });

        // Listener para el boton del menu que te lleva al panelYaCobrados ---------------------------------------------

        botonMenuYaCobrados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelFormularioAlta.setVisible(false);
                scrollPanelFormularioAlta.setVisible(false);

                panelCobroTrabajos.setVisible(false);
                panelTrabajosCobrados.setVisible(true);
                panelAltaProveedor.setVisible(false);
                panelListaProveedores.setVisible(false);
                Controlador.getSingleton().cargarListaCobrados(listaCobrados, labelTrabajosCobrados);
                // Rellena JList, modifica Label
            }
        });

        // Listener para el boton de alta del panelFormularioAlta ------------------------------------------------------

        botonAlta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprobarAltaInfoYAceptar();
            }
        });

        // Listener para el botón de cobrar del panelCobroTrabajos -----------------------------------------------------

        botonCobroTrabajo.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                cobrarTrabajo();
            }
        });

        //Listener para el boton del menu que nos lanza al panelAltaProveedores

        botonAltaProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelFormularioAlta.setVisible(false);
                scrollPanelFormularioAlta.setVisible(false);

                panelCobroTrabajos.setVisible(false);
                panelTrabajosCobrados.setVisible(false);
                panelAltaProveedor.setVisible(true);
                panelListaProveedores.setVisible(false);
            }
        });

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprobarAltaInfoYAceptarProveedor();
            }
        });


        botonListaProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelFormularioAlta.setVisible(false);
                scrollPanelFormularioAlta.setVisible(false);

                panelCobroTrabajos.setVisible(false);
                panelTrabajosCobrados.setVisible(false);
                panelAltaProveedor.setVisible(false);
                panelListaProveedores.setVisible(true);

                Controlador.getSingleton().cargarListaProveedores(listaProveedores, lProveedoresDisponibles);

            }
        });

        this.pack();

        /* -------------------------------------------------------------------------------------------------------------

            Este último listener se encargará de "escuchar" cuando la ventana cambia de tamaño, para así poder ajustar
            el tamaño de los insets (los márgenes que tienen los paneles de dentro de panelContenido) y hacerlo más
            responsive.
         */

        ventanaDelPrograma.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                // Vamos a necesitar los datos actualizados de la ventana, para ello lo cogemos de lo que escucha el listener
                Component datosVentana = (Component) e.getSource();

                // Sacamos el ancho y alto de la ventana
                int anchoVentana = datosVentana.getWidth();
                int altoVentana = datosVentana.getHeight();

                // Adaptamos el tamaño de los TextAreas para que no sean tan grandes al redimensionar verticalmente la ventana
                taDiagnostico.setRows((int) (altoVentana * 1.3 / 100));
                taSolucion.setRows((int) (altoVentana * 1.3 / 100));

                // Es necesario pasar ciertas configuraciones a GridBagLayout para que funcione

                GridBagConstraints gbcPanelFormulario = new GridBagConstraints();
                GridBagConstraints gbcPanelCobros = new GridBagConstraints();
                GridBagConstraints gbcPanelYaCobrados = new GridBagConstraints();
                GridBagConstraints gbcPanelAltaProveedor = new GridBagConstraints();
                GridBagConstraints gbcPanelListaProveedores = new GridBagConstraints();

                // Ahora tenemos que modificar las configuraciones variables para adaptar los paneles

                // CONFIGURACIONES DEL PANEL FORMULARIO ----------------------------------------------------------------

                // Con fill extendemos el panel, y es necesario usar weight distinto de 0 porque si no no se ajusta

                gbcPanelFormulario.fill = GridBagConstraints.BOTH;   // <- BOTH = Horizontal y vertical
                gbcPanelFormulario.weightx = 1;
                gbcPanelFormulario.weighty = 1;

                // Con insets ponemos un margen externo a los paneles

                /*
                    Para el panel formulario usaremos el 5% del ancho de la ventana para cada uno de los margenenes
                    laterales y el 5% del alto de la ventana para los margenes superior e inferior.

                    Estos porcentajes son los que más se acercan a como estaban antes a pantalla completa.

                    Margenes laterales -> (anchoVentana * 5) / 100 -> 5% de ancho de la ventana
                    Margenes superior e inferior -> (altoVentana * 5) / 100 -> 5% de alto de la ventana
                */

                gbcPanelFormulario.insets = new Insets((altoVentana * 5) / 100, (anchoVentana * 5) / 100, (altoVentana * 5) / 100, (anchoVentana * 5) / 100);

                // CONFIGURACIONES DEL PANEL COBROS --------------------------------------------------------------------

                // Con fill extendemos el panel, y es necesario usar weight distinto de 0 porque si no no se ajusta

                gbcPanelCobros.fill = GridBagConstraints.BOTH;   // <- BOTH = Horizontal y vertical
                gbcPanelCobros.weightx = 1;
                gbcPanelCobros.weighty = 1;

                // Con insets ponemos un margen externo a los paneles

                /*
                    Para el panel formulario usaremos el 21% del ancho de la ventana para cada uno de los margenenes
                    laterales y el 20% del alto de la ventana para los margenes superior e inferior.

                    Estos porcentajes son los que más se acercan a como estaban antes a pantalla completa.

                    Margenes laterales -> (anchoVentana * 21) / 100 -> 21% de ancho de la ventana
                    Margenes superior e inferior -> (altoVentana * 20) / 100 -> 20% de alto de la ventana
                */
                gbcPanelCobros.insets = new Insets((altoVentana * 20) / 100, (anchoVentana * 21) / 100, (altoVentana * 20) / 100,(anchoVentana * 21) / 100);


                // CONFIGURACIONES DEL PANEL YA COBRADOS ---------------------------------------------------------------

                gbcPanelYaCobrados.fill = GridBagConstraints.BOTH;
                gbcPanelYaCobrados.weightx = 1;
                gbcPanelYaCobrados.weighty = 1;

                //Usaremos los mismo margenes que en el PanelCobros
                gbcPanelYaCobrados.insets = new Insets((altoVentana * 20) / 100, (anchoVentana * 21) / 100, (altoVentana * 20) / 100,(anchoVentana * 21) / 100);


                //CONFIGURACIONES DEL PANEL DE ALTA PROVEEDOR

                gbcPanelAltaProveedor.fill = GridBagConstraints.BOTH;   // <- BOTH = Horizontal y vertical
                gbcPanelAltaProveedor.weightx = 1;
                gbcPanelAltaProveedor.weighty = 1;



                gbcPanelAltaProveedor.insets = new Insets((altoVentana * 5) / 100, (anchoVentana * 5) / 100, (altoVentana * 5) / 100, (anchoVentana * 5) / 100);


                //CONFIGURACIONES DEL PANEL LISTA PROVEEDORES

                gbcPanelListaProveedores.fill = GridBagConstraints.BOTH;
                gbcPanelListaProveedores.weightx = 1;
                gbcPanelListaProveedores.weighty = 1;

                //Usaremos los mismo margenes que en el PanelCobros
                gbcPanelListaProveedores.insets = new Insets((altoVentana * 20) / 100, (anchoVentana * 21) / 100, (altoVentana * 20) / 100,(anchoVentana * 21) / 100);


                // Asignamos las configuraciones al panel correspondiente
                gbl.setConstraints(scrollPanelFormularioAlta, gbcPanelFormulario);
                gbl.setConstraints(panelCobroTrabajos, gbcPanelCobros);
                gbl.setConstraints(panelTrabajosCobrados, gbcPanelYaCobrados);
                gbl.setConstraints(panelAltaProveedor, gbcPanelAltaProveedor);
                gbl.setConstraints(panelListaProveedores, gbcPanelListaProveedores);

                // Refrescamos el panelContenido para que se vean los cambios
                panelContenido.repaint();
                panelContenido.revalidate();
            }
        });

    }

    // Métodos Extra ###################################################################################################
    private void comprobarAltaInfoYAceptar() {
        String sCSV,sFecha,sMatricula,sModelo,sPropietario,sDNIPropietario,sDiagnostico,sResolucion,sHorasTrabajoPrevistas,tipo;

        //inicializamos tipo para que se pueda usar en el CSV
        tipo = "";

        //Comprobamos que el usuario ha introducido la información adecuada.
        //Por programar...

        //Recogemos los datos del formulario
        int indice = comboTipo.getSelectedIndex();

        sMatricula = tfMatricula.getText();
        sModelo = tfModelo.getText();
        sPropietario = tfPropietario.getText();
        sDNIPropietario = tfDni.getText();
        sDiagnostico = taDiagnostico.getText();
        sResolucion = taSolucion.getText();
        sHorasTrabajoPrevistas = tfHorasPrevistas.getText();

        //¿?¿?Todos los datos están ok¿?¿?¿?

        //Pedimos al controlador que realice el alta.

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        sFecha = formatter.format(Calendar.getInstance().getTime());

        switch (indice){
            case 0-> tipo = "Coche";
            case 1-> tipo = "Moto";
            case 2-> tipo = "Furgón";
            case 3-> tipo = "Camión";
        }

        sCSV =  tipo + ';' +
                sMatricula + ';' +
                sModelo + ';' +
                sPropietario + ';' +
                sDNIPropietario+ ";" +
                sFecha + ";" + //La fecha vacía para que el constructor instancie la actual
                sDiagnostico + ';' +
                sResolucion + ';' +
                sHorasTrabajoPrevistas + ";0";


        Controlador.getSingleton().altaTrabajo(sCSV);
        showMessageDialog(panelFormularioAlta,"Vehículo añadido");
        tfMatricula.setText("");
        tfModelo.setText("");
        tfPropietario.setText("");
        tfDni.setText("");
        taDiagnostico.setText("");
        taSolucion.setText("");
        tfHorasPrevistas.setText("");

    }

    //Dar de ALta a un proveedor
    private void comprobarAltaInfoYAceptarProveedor(){
        String sCSV = tfNombreEmpresa.getText() +";"
                    + tfFechaAlta.getText()+";"
                    + tfDireccionPostal.getText()+";"
                    +tfNumeroTelefono.getText()+";"
                    +tfEmail.getText()+";"
                    + tfContactoComercial.getText();

        Controlador.getSingleton().altaProveedor(sCSV);

        // cuando le damos al botón aceptar se settean los campos
        showMessageDialog(panelAltaProveedor, "Proveedor añadido");
        tfNombreEmpresa.setText("");
        tfFechaAlta.setText("");
        tfDireccionPostal.setText("");
        tfNumeroTelefono.setText("");
        tfEmail.setText("");
        tfContactoComercial.setText("");
    }


    private void cobrarTrabajo() {
        int iSelected = this.listaCobros.getSelectedIndex();

        //Pasamos el elemento de la Lista seleccionado a String
        String item = (String)listaCobros.getModel().getElementAt(iSelected);

        //Falta coger horas reales con un dialogo flotante
        String sHorasReales = "0";

        //Pedimos al controlador que realice el "cobro" (elimina el elemento de la lista).
        Controlador.getSingleton().cobrarTrabajo(item, sHorasReales);
        Controlador.getSingleton().cargarListaCobrar(listaCobros, labelTrabajosYaCobrados); //Actualiza la lista
    }
}

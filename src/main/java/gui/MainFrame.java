package gui;


import controller.Controller;

import javax.swing.*;

    public class MainFrame extends JFrame {

        private Controller controller;

        public MainFrame(Controller controller) {

            this.controller = controller;

            setTitle("Sistema Gestione Tesi");
            setSize(800, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
        }
    }


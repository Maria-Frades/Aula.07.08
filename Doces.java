package javaapplication11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Doces extends JFrame implements ActionListener {
    private JPanel[] docesPanel;
    private JTextField[] quantidadeCampos;
    private JLabel[] docesLabel;
    private JLabel[] precoLabel;
    private final JButton Button;
    private double[] precos = {3.50, 2.75, 1.00};

    public Doces() {
        setTitle("Loja de doces da Rafa");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // 4 linhas, 2 colunas, espaçamento de 10 pixels
        add(mainPanel, BorderLayout.CENTER);

        docesPanel = new JPanel[3];
        quantidadeCampos = new JTextField[3];
        docesLabel = new JLabel[3];
        precoLabel = new JLabel[3];

        String[] candyImagePaths = {"biscoito.jpeg", "Coracao.jpeg", "morango.jpeg"};
        for (int i = 0; i < 3; i++) {
            docesPanel[i] = new JPanel(new BorderLayout());
            try {
                BufferedImage originalImage = ImageIO.read(getClass().getResource(candyImagePaths[i]));
                BufferedImage resizedImage = resizeImage(originalImage, 100, 100); // Tamanho desejado
                ImageIcon candyIcon = new ImageIcon(resizedImage);
                docesLabel[i] = new JLabel(candyIcon);
                docesPanel[i].add(docesLabel[i], BorderLayout.CENTER);

                precoLabel[i] = new JLabel("R$" + precos[i]);
                precoLabel[i].setHorizontalAlignment(JLabel.RIGHT); // Alinhamento à direita
                docesPanel[i].add(precoLabel[i], BorderLayout.LINE_END);

                mainPanel.add(docesPanel[i]);

                quantidadeCampos[i] = new JTextField(5);
                mainPanel.add(quantidadeCampos[i]);

            } catch (IOException e) {
            }
        }

        Button = new JButton("Pedir");
        Button.addActionListener(this);
        add(Button, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();
        return resizedImage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Button) {
            double total = 0;
            for (int i = 0; i < 3; i++) {
                int quantity = Integer.parseInt(quantidadeCampos[i].getText());
                total += precos[i] * quantity;
            }
            JOptionPane.showMessageDialog(this, "Total da compra: R$" + total);
        }
    }
    
    public static void main(String[] args) {
        Doces doces = new Doces();
    }

}
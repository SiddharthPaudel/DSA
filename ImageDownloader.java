import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageDownloader extends JFrame {

    private JTextField urlField;
    private JButton downloadButton;
    private JPanel imagePanel;
    private ExecutorService executorService;
    private List<ImageDownloadTask> downloadTasks;

    public ImageDownloader() {
        setTitle("Image Downloader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        urlField = new JTextField();
        downloadButton = new JButton("Download");
        imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        executorService = Executors.newFixedThreadPool(5); // Thread pool with 5 threads
        downloadTasks = new ArrayList<>();

        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = urlField.getText();
                if (!url.isEmpty()) {
                    downloadImages(url);
                } else {
                    JOptionPane.showMessageDialog(ImageDownloader.this,
                            "Please enter a valid URL.");
                }
            }
        });

        add(urlField, BorderLayout.NORTH);
        add(downloadButton, BorderLayout.SOUTH);
        add(new JScrollPane(imagePanel), BorderLayout.CENTER);
    }

    private void downloadImages(String url) {
        ImageDownloadTask task = new ImageDownloadTask(url);
        downloadTasks.add(task);
        executorService.submit(task);
    }

    private class ImageDownloadTask implements Runnable {
        private String url;

        public ImageDownloadTask(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            try {
                URL imageUrl = new URL(url);
                InputStream inputStream = imageUrl.openStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();

                byte[] imageBytes = outputStream.toByteArray();
                ImageIcon imageIcon = new ImageIcon(imageBytes);
                JLabel imageLabel = new JLabel(imageIcon);
                imagePanel.add(imageLabel);
                imagePanel.revalidate();
                imagePanel.repaint();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(ImageDownloader.this,
                        "Error downloading image from URL: " + url);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ImageDownloader().setVisible(true);
            }
        });
    }
}


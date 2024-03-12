import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeScreen extends JPanel {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    
    private JFrame frame;
    private int playButtonX;
    private int playButtonY;
    private int playButtonWidth;
    private int playButtonHeight;

    private Image dirtImage;

    public HomeScreen(JFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        //ImageIcon dirtIcon = new ImageIcon("src/res/images/dirt-background.PNG");
        ImageIcon dirtIcon = new ImageIcon(getClass().getResource("/res/images/dirt-background.PNG"));
        dirtImage = dirtIcon.getImage();
        setBackground(Color.BLACK);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                checkClick(e.getX(), e.getY());
            }
        });
        
    }
    private void checkClick(int x, int y) {
        if (x >= playButtonX && x <= (playButtonX + playButtonWidth) &&
            y >= playButtonY && y <= (playButtonY + playButtonHeight)) {
            startGame();
        }
    }
     private void startGame() {
        frame.remove(this); // remove home screen
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.pack();
        gamePanel.requestFocusInWindow();
        frame.validate();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(dirtImage, 0, 0, this); // dirt background
        g.drawImage(dirtImage, dirtImage.getWidth(this) - 10, 0, this); 
        g.drawImage(dirtImage, 0, dirtImage.getHeight(this) - 10, this);
        g.drawImage(dirtImage, dirtImage.getWidth(this) - 10, dirtImage.getHeight(this) - 10, this);
        drawPlayButton(g);
    }
    private void drawPlayButton(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Ink Free", Font.BOLD, 150));
        FontMetrics metricsPlay = g.getFontMetrics();
        playButtonX = (SCREEN_WIDTH - metricsPlay.stringWidth("Play")) / 2; // center on x axis
        playButtonY = (SCREEN_HEIGHT / 2); // center on y axis
        playButtonWidth = metricsPlay.stringWidth("Play"); // width of Play text
        playButtonHeight = metricsPlay.getHeight(); // height of play text
        g.drawString("Play", playButtonX, playButtonY); 
        playButtonY = playButtonY - metricsPlay.getAscent(); // make Y coord top of text not middle for clicking
    }
}

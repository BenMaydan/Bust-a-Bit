import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Display extends JComponent {
    // Final variables
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private Rectangle stockGraphPanelRect = new Rectangle(100, 100, (WIDTH-100)/2, 500);

    // Buttons
    private JButton betButton;

    // Components
    private JPanel stockGraphPanel = new JPanel();

    // Controller
    private Control control;


    public Display(JFrame f) {
        f.add(this);
        drawStockGraphPanel();
    }
    public void setControl(Control control) {
        this.control = control;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        drawText(g2);
        drawStockGraph(g2);
        drawButtons(g2);
    }

    private void drawText(Graphics2D g2) {

    }

    private void drawStockGraphPanel() {
        stockGraphPanel.setBounds(stockGraphPanelRect);
        stockGraphPanel.setBackground(Color.WHITE);
        stockGraphPanel.setOpaque(false);
        stockGraphPanel.setVisible(true);
        add(stockGraphPanel);
    }

    private void drawStockGraph(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(stockGraphPanelRect.x, stockGraphPanelRect.y, stockGraphPanelRect.width, stockGraphPanelRect.height);
        int xMax = 62;
        int drawXIncrement = (int)Math.floor((double)stockGraphPanelRect.width/(xMax-1));
        int yMax = (int)control.getPrice(Math.min(control.getDay()+xMax, control.getDayCrashed())) + 10;
        int yIncrement = (int) (Math.abs(control.getPrice(control.getDay()) - control.getPrice(Math.min(control.getDay()+xMax, control.getDayCrashed()))) / stockGraphPanelRect.height);
        int yDrawTimes = 100;
        int drawYIncrement = stockGraphPanelRect.height/yDrawTimes;

        int x = stockGraphPanelRect.x+drawXIncrement/2;
        int y = stockGraphPanelRect.y;
        int h = stockGraphPanelRect.height;
        g2.setColor(Color.BLACK);
        for (int i = 0; i < xMax; i++) {
            g2.drawLine(x, y+h+5, x, y+h);
            x += drawXIncrement;
        }
        x = stockGraphPanelRect.x;
        for (int i = 0; i < yDrawTimes; i++) {
            g2.drawLine(x-5, y, x-1, y);
            y += drawYIncrement;
        }


        // Draw points
        int rad = 5;
        int start = 0;
        if (control.getDay() > xMax)
            start = control.getDay()-xMax;
        int i = 0;
        for (int day = start; day < control.getDay(); day++) {
            x = stockGraphPanelRect.x + i*drawXIncrement;
            y = stockGraphPanelRect.y + stockGraphPanelRect.height - (int)control.getPrice(day)/drawYIncrement;
            g2.setColor(Color.black);
            g2.fillOval(x+rad/2, y-3*rad/2, rad, rad);
            if (i > 0) {
                int x0 = stockGraphPanelRect.x + (i-1)*drawXIncrement;
                int y0 = stockGraphPanelRect.y + stockGraphPanelRect.height - (int)control.getPrice(day-1)/drawYIncrement;
                if (control.getPrice(day-1) <= control.getPrice(day))
                    g2.setColor(Color.green.darker());
                else
                    g2.setColor(Color.red);
                g2.drawLine(x0+rad, y0-rad, x+rad, y-rad);
            }
            i++;
        }

        g2.drawOval(500+5, 500+5, 10, 10);
        g2.drawLine(510, 510, 600, 505);
    }

    private void drawButtons(Graphics2D g2) {

    }
}

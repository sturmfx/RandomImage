
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Drawer
{
    private static Random r;
    public static void draw(String type, int amount, int min_width, int max_width, int min_height, int max_height, int min_x, int max_x, int min_y, int max_y)
    {
        Data.images.add(clone(Data.images.get(Data.i)));
        Data.i++;

        r = new Random();

        Graphics2D g2d = (Graphics2D) Data.images.get(Data.i).getGraphics();

        switch(type)
        {
            case "CIRCLE FILL":
                g2d.setColor(Data.colors[r.nextInt(Data.colors.length)]);
                for(int i = 0; i < amount; i++)
                {
                    int width1 = min_width + r.nextInt(max_width - min_width);
                    int x1 = min_x - width1 / 2 + r.nextInt(max_x - min_x + width1 / 2);
                    int y1 = min_y - width1 / 2 + r.nextInt(max_y - min_y + width1 / 2);
                    g2d.fillOval(x1, y1, width1, width1);
                }
                break;

            case "CIRCLE":
                g2d.setColor(Data.colors[r.nextInt(Data.colors.length)]);
                for(int i = 0; i < amount; i++)
                {
                    int width1 = min_width + r.nextInt(max_width - min_width);
                    int x1 = min_x - width1 / 2 + r.nextInt(max_x - min_x + width1 / 2);
                    int y1 = min_y - width1 / 2 + r.nextInt(max_y - min_y + width1 / 2);
                    g2d.setStroke(new BasicStroke(Data.stroke_width));
                    g2d.drawOval(x1, y1, width1, width1);
                }
                break;

            case "OVAL FILL":
                g2d.setColor(Data.colors[r.nextInt(Data.colors.length)]);
                for(int i = 0; i < amount; i++)
                {
                    int width1 = min_width + r.nextInt(max_width - min_width);
                    int height1 = min_height + r.nextInt(max_height - min_height);
                    int x1 = min_x - width1 / 2 + r.nextInt(max_x - min_x + width1 / 2);
                    int y1 = min_y - height1 / 2 + r.nextInt(max_y - min_y + height1 / 2);
                    g2d.fillOval(x1, y1, width1, height1);
                }
                break;
            case "OVAL":
                g2d.setColor(Data.colors[r.nextInt(Data.colors.length)]);
                for(int i = 0; i < amount; i++)
                {
                    int width1 = min_width + r.nextInt(max_width - min_width);
                    int height1 = min_height + r.nextInt(max_height - min_height);
                    int x1 = min_x - width1 / 2 + r.nextInt(max_x - min_x + width1 / 2);
                    int y1 = min_y - height1 / 2 + r.nextInt(max_y - min_y + height1 / 2);
                    g2d.setStroke(new BasicStroke(Data.stroke_width));
                    g2d.drawOval(x1, y1, width1, height1);
                }
                break;
            case "SQUARE FILL":
                g2d.setColor(Data.colors[r.nextInt(Data.colors.length)]);
                for(int i = 0; i < amount; i++)
                {
                    int width1 = min_width + r.nextInt(max_width - min_width);
                    int x1 = min_x - width1 / 2 + r.nextInt(max_x - min_x + width1 / 2);
                    int y1 = min_y - width1 / 2 + r.nextInt(max_y - min_y + width1 / 2);
                    g2d.fillRect(x1, y1, width1, width1);
                }
                break;
            case "SQUARE":
                g2d.setColor(Data.colors[r.nextInt(Data.colors.length)]);
                for(int i = 0; i < amount; i++)
                {
                    int width1 = min_width + r.nextInt(max_width - min_width);
                    int x1 = min_x - width1 / 2 + r.nextInt(max_x - min_x + width1 / 2);
                    int y1 = min_y - width1 / 2 + r.nextInt(max_y - min_y + width1 / 2);
                    g2d.setStroke(new BasicStroke(Data.stroke_width));
                    g2d.drawRect(x1, y1, width1, width1);
                }
                break;
            case "RECTANGLE FILL":
                g2d.setColor(Data.colors[r.nextInt(Data.colors.length)]);
                for(int i = 0; i < amount; i++)
                {
                    int width1 = min_width + r.nextInt(max_width - min_width);
                    int height1 = min_height + r.nextInt(max_height - min_height);
                    int x1 = min_x - width1 / 2 + r.nextInt(max_x - min_x + width1 / 2);
                    int y1 = min_y - height1 / 2 + r.nextInt(max_y - min_y + height1 / 2);
                    g2d.fillRect(x1, y1, width1, height1);
                }
                break;
            case "RECTANGLE":
                g2d.setColor(Data.colors[r.nextInt(Data.colors.length)]);
                for(int i = 0; i < amount; i++)
                {
                    int width1 = min_width + r.nextInt(max_width - min_width);
                    int height1 = min_height + r.nextInt(max_height - min_height);
                    int x1 = min_x - width1 / 2 + r.nextInt(max_x - min_x + width1 / 2);
                    int y1 = min_y - height1 / 2 + r.nextInt(max_y - min_y + height1 / 2);
                    g2d.setStroke(new BasicStroke(Data.stroke_width));
                    g2d.drawRect(x1, y1, width1, height1);
                }
                break;
        }
    }

    public static final BufferedImage clone(BufferedImage image)
    {
        BufferedImage clone = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g2d = clone.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return clone;
    }
}

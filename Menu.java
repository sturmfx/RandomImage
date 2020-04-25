
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Menu extends JFrame implements ItemListener
{
    boolean isCreated = false;
    VBox content = new VBox();

    JFrame image = new JFrame();
    Canvas canvas = new Canvas();

    MenuItem width = new MenuItem("IMAGE WIDTH: ");
    MenuItem height = new MenuItem("IMAGE HEIGHT: ");

    MenuItem amount = new MenuItem("AMOUNT OF ITEMS: ");

    MenuItem min_width = new MenuItem("ITEM MIN WIDTH: ");
    MenuItem max_width = new MenuItem("ITEM MAX WIDTH: ");

    MenuItem min_height = new MenuItem("ITEM MIN HEIGHT: ");
    MenuItem max_height = new MenuItem("ITEM MAX HEIGHT: ");

    MenuItem min_x = new MenuItem("ITEM MIN X: ");
    MenuItem max_x = new MenuItem("ITEM MAX X: ");

    MenuItem min_y = new MenuItem("ITEM MIN Y: ");
    MenuItem max_y = new MenuItem("ITEM MAX Y: ");

    JButton add = new JButton("ADD ITEMS");

    JButton undo = new JButton("UNDO ITEMS");

    JButton reset = new JButton("INIT");

    JButton save = new JButton("SAVE");


    JComboBox<String> type;
    String[] types = new String[]{"CIRCLE", "CIRCLE FILL", "OVAL", "OVAL FILL", "SQUARE", "SQUARE FILL", "RECTANGLE", "RECTANGLE FILL", "LINE", "POLYGONE", "POLYGONE FILL"};

    public Menu()
    {
        initUI();
        initCA();
    }

    public void initUI()
    {
        add(content);

        type = new JComboBox<>(types);
        type.addItemListener(this);

        content.add(width);
        content.add(height);

        content.add(reset);

        content.add(type);

        content.add(amount);

        content.add(min_width);
        content.add(max_width);

        content.add(min_height);
        content.add(max_height);

        content.add(min_x);
        content.add(max_x);

        content.add(min_y);
        content.add(max_y);

        content.add(add);
        content.add(undo);
        content.add(save);

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initCA()
    {
        reset.addActionListener(e ->
        {
            int x;
            int y;
            if(isCreated)
            {
                isCreated = false;
                image.dispose();
                Data.i = 0;
                Data.last = 0;
                Data.images.clear();
                reset.setText("INIT");
            }
            else
            {
                isCreated = true;
                reset.setText("RESET");
                if(width.isInt() && height.isInt())
                {
                    if(width.getValue() <= Data.width_limit && width.getValue() <= Data.height_limit)
                    {
                        x = width.getValue();
                        y = height.getValue();
                    }
                    else
                    {
                        x = Data.default_width;
                        y = Data.default_height;
                    }
                }
                else
                {
                    x = Data.default_width;
                    y = Data.default_height;
                }
                BufferedImage bi = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
                Graphics2D ig2 = bi.createGraphics();
                ig2.setBackground(Data.background);
                ig2.clearRect(0, 0, x, y);
                Data.images.add(bi);

                image = new JFrame();
                canvas = new Canvas();
                canvas.setPreferredSize(new Dimension(x, y));
                image.add(canvas);
                image.pack();
                image.setVisible(true);
                image.setResizable(false);
                image.repaint();
            }
        });

        add.addActionListener(e ->
        {
            String type_v;

            int amount_v;

            int min_width_v;
            int max_width_v;

            int min_height_v;
            int max_height_v;

            int min_x_v;
            int max_x_v;

            int min_y_v;
            int max_y_v;

            if(isCreated)
            {
                type_v = (String) type.getSelectedItem();

                amount_v = getValue(amount, Data.default_amount, Data.amount_limit);

                min_width_v = getValue(min_width, Data.default_min_width, Data.min_width_limit);
                max_width_v = getValue(max_width, Data.default_max_width, Data.max_width_limit);

                min_height_v = getValue(min_height, Data.default_min_height, Data.min_height_limit);
                max_height_v = getValue(max_height, Data.default_max_height, Data.max_height_limit);

                min_x_v = getValue(min_x, Data.default_min_x, Data.min_x_limit);
                max_x_v = getValue(max_x, Data.default_max_x, Data.max_x_limit);

                min_y_v = getValue(min_y, Data.default_min_y, Data.min_y_limit);
                max_y_v = getValue(max_y, Data.default_max_y, Data.max_y_limit);

                Drawer.draw(type_v, amount_v, min_width_v, max_width_v, min_height_v, max_height_v, min_x_v, max_x_v, min_y_v, max_y_v);
                image.repaint();
            }
        });

        save.addActionListener(e ->
        {
            Random r = new Random();
            final String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\image" + r.nextInt(1000) + ".png";
            System.out.println("SAVED TO: " + path);

            try
            {
                final RenderedImage im = Data.images.get(Data.i);
                ImageIO.write(im, "png", new File(path));
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        });

        undo.addActionListener(e ->
        {
            if(Data.i > 0)
            {
                Data.i--;
                Data.images.remove(Data.images.size() - 1);
                image.repaint();
            }
        });
    }

    public static int getValue(MenuItem mi, int def, int limit)
    {
        int result = 0;

        if(mi.isInt())
        {
            if(mi.getValue() <= limit && mi.getValue() >= 1)
            {
                result = mi.getValue();
                return result;
            }
            else
            {
                return def;
            }
        }
        else
        {
            return def;
        }
    }


    @Override
    public void itemStateChanged(ItemEvent e)
    {
        if(e.getStateChange() == ItemEvent.SELECTED)
        {
            setTitle(e.getItem().toString());
        }
    }

    public static void main(String[] args)
    {
       Menu menu = new Menu();
    }
}

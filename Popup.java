import javax.swing.JFrame;
import javax.swing.JLabel;
/*
 *  Temporary pop-up dialog box with no frame.
 *
 *  @authors Heather N. Larsen
 *  @version    1.0    2018/09/19:13:58
 */
public class Popup {
    protected JFrame frame = new JFrame("Pop-up");
    /**
     * Default constructor for creating the pop-up.
     * @param message the pop-up message
     * @param x coordinate of popup
     * @param y coordinate of popup
     * @param s seconds to elapse before closing dialog
     */
    protected Popup(String message, int x, int y, int s) throws InterruptedException {
        JLabel msg   = new JLabel(message);
        
        msg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        frame.getContentPane().add(msg);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(x, y, 250, 50);
        frame.setVisible(true);
        frame.toFront();
    }/***********************************POP.UP*********************************/
}/***********************************POP.UP_CLASS*******************************/

import java.io.IOException;
import javax.swing.JFrame;
/*
 *  Sign-up container for the credentials collecting panel.
 *  @author Heather N. Larsen
 *  @version    1.0    2018/09/08:19:44
 */
public class SignUpContainer {
    public SignUpContainer() throws IOException {
        JFrame frame = new JFrame("Sign Up");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        frame.getContentPane().add(new SignUpGUI());
        
        frame.pack();
        frame.setVisible(true);
    }
}

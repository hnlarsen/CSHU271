
import java.io.IOException;
import javax.swing.JFrame;

/*
 *  Container for username retrieval.
 *  @author Heather N. Larsen
 *  @version    1.0    2018/09/15:20:08
 */
public class RetrieveUsernameContainer {
    JFrame frame;
    /**
     *  Default constructor.
     */
    public RetrieveUsernameContainer() throws IOException {
        frame = new JFrame("Retrieve Username");

        frame.getContentPane().add(new RetrieveUsernameGUI());
        
        frame.pack();
        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }/**********************RETRIEVE.USERNAME.CONTTAINER************************/
}/***********************RETRIEVE.USERNAME.CONTAINER_CLASS**********************/

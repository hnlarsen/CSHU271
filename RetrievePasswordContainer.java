import java.io.IOException;
import javax.swing.JFrame;

/*
 *  Container for password retrieval.
 *  @author Heather N. Larsen
 *  @version    1.0    2018/09/15:20:08
 */
public class RetrievePasswordContainer {
    JFrame frame;
    RetrievePasswordGUI rp;
    SecurityQuestionGUI sa;
    /**
     *  Default constructor.
     */
    public RetrievePasswordContainer() throws IOException {
        frame = new JFrame("Retrieve Password");
        rp = new RetrievePasswordGUI();
        sa = new SecurityQuestionGUI();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        frame.setVisible(true);
    }/********************RETRIEVE.PASSWORD.CONTAINER***************************/
    /**
     *  Pulls up the prompt for the user to enter the account username or email.
     */
    protected void gatherCredential() throws IOException {
        frame.getContentPane().add(rp);
        frame.pack();
    }/************************GATHER.CREDENTIAL*********************************/
    /**
     *  Pulls up the prompt for the user to enter the account security answer.
     */
    protected void gatherSecurityAnswer() {
        frame.getContentPane().add(sa);
        frame.pack();
    }/************************GATHER.SECURITY.ANSWER*********************************/
}/***********************RETRIEVE.PASSWORD.CONTAINER_CLASS**********************/

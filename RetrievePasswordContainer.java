import java.io.FileNotFoundException;
import java.io.IOException;
import javax.naming.NameNotFoundException;
import javax.swing.JFrame;

/*
 *  Container for password retrieval.
 *  @author Heather N. Larsen
 *  @version    1.0    2018/09/15:20:08
 */
public class RetrievePasswordContainer {
    private JFrame frame;
    private RetrievePasswordGUI rp;
    private SecurityQuestionGUI sa;
    private PassResetGUI pr;
    /**
     *  Default constructor.
     */
    public RetrievePasswordContainer() throws IOException {
        frame = new JFrame("Retrieve Password");
        rp = new RetrievePasswordGUI();

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
     *  @param val username/email
     */
    protected void gatherSecurityAnswer(String val) throws IOException, FileNotFoundException, NameNotFoundException {
        sa = new SecurityQuestionGUI(val);
        frame.getContentPane().add(sa);
        frame.pack();
    }/************************GATHER.SECURITY.ANSWER****************************/
    /**
     *  Pulls up the prompt for the user to enter the account security answer.
     *  @param val username/email
     */
    protected void passReset(String val) throws IOException, FileNotFoundException, NameNotFoundException {
        pr = new PassResetGUI(val);
        frame.getContentPane().add(pr);
        frame.pack();
    }/************************GATHER.SECURITY.ANSWER****************************/
}/***********************RETRIEVE.PASSWORD.CONTAINER_CLASS**********************/

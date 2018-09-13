package sprint1;
import javax.swing.JFrame;

/**
 * @author chrismiller558
 *
 */
public class LoginPanel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			JFrame frame = new JFrame ("Login");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			/*Add frame*/
			frame.getContentPane().add(new LoginPanelBoard());
			
			frame.pack();
			frame.setVisible(true);
}
}

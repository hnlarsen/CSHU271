import java.io.IOException;
import javax.naming.NameNotFoundException;
/*
 *  System for retrieving the username or password of a user.
 *  @authors Heather N. Larsen
 *  @version    1.0    2018/09/13:17:45
 */
public class RetrievalSystem {
    String sQ       = "What was your childhod nickname?";
    String sQ2      = "In what city did you meet your spouse/significant other?";
    String sQ3      = "What is the name of your favorite childhood friend?";
    String sQ4      = "What street did you live on in the third grade?";
    String sQ5      = "What is your oldest sibling's birthday month and year?";
    String sQ6      = "What is the middle name of your youngest child?";
    String sQ7      = "What is your oldest sibling's middle name?";
    String sQ8      = "What school did you attend for sixth grade?";
    String sQ9      = "What was your childhood phone number including area code?";
    String sQ10     = "What is your oldest cousin's first and last name?";
    String sQ11     = "What was the name of your first stuffed animal?";
    String sQ12     = "In what city or town did your mother and father meet?";
    String sQ13     = "Where were you when you had your first kiss?";
    String sQ14     = "What is the first name of the boy or girl that you first kissed?";
    String sQ15     = "What was the last name of your third grade teacher?";
    String sQ16     = "In what city does your nearest sibling live?";
    String sQ17     = "What is your youngest sibling's birthday month and year?";
    String sQ18     = "What is your maternal grandmother's maiden name?";
    String sQ19     = "In what city or town was your first job?";
    String sQ20     = "What is the name of the place your wedding reception was held?";
    String sQ21     = "What is the name of a college you applied to but didn't attend?";
    String sQ22     = "Where were you when you first about 9/11?";   
    
    UserDatabase db;    //USER-DATABASE
    String secQ0;       //first registered security question
    String secQ1;       //second registered security question
    /**
     *  Default constructor.
     */
    protected RetrievalSystem() throws IOException {
        db = new UserDatabase();
    }
    /**
     *  Retrieves the username based on the associated e-mail address.
     *  @param email email address registered to username
     *  @throws NameNotFoundException email not in database
     *  @return username
     */
    protected String retrieveUser(String email) throws NameNotFoundException, IOException {
        if(!db.emailExists(email)) {
            throw new NameNotFoundException("The email specified is not registered.");
        }
        
        String[] credentials = db.tempMarker.split("\t");
        String username      = credentials[0];
        
        return username;
    }/*****************************FIND.USER************************************/
    /**
     *  Retrieves the account information based on the username or email.
     *  @param val username/email
     *  @throws NameNotFoundException no such account in database
     */
    private void retrieveAccount(String val) throws IOException, NameNotFoundException {
        boolean found = false;
        if(db.userExists(val))       { found = true; }
        else if(db.emailExists(val)) { found = true; }
        
        if(!found) {
            throw new NameNotFoundException("There is no such account associated with these credentials.");
        }
        
        String[] credentials = db.tempMarker.split("\t");
        
        secQ0 = credentials[3];
        secQ1 = credentials[4];
    }/*******************************RETRIEVE.ACCOUNT***************************/
    /**
     *  Replaces the password recorded in the database with a new one.
     *  @param pass new password
     */
    private void replacePassword(String pass) {
        String[] credentials = db.tempMarker.split("\t");
        String password      = credentials[1];

        String newPass  = Integer.toString(pass.hashCode());
        
        db.tempMarker.replace(password, newPass);
    }/*****************************REPLACE.PASSWORD*****************************/
}/***************************RETRIEVAL.SYSTEM_CLASS*****************************/

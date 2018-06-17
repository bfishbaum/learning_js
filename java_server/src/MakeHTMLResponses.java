import javax.swing.text.html.HTMLDocument;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

public class MakeHTMLResponses {
    public static String makeMessagesPage(Account account) throws IOException {
        ByteArrayOutputStream BOS = new ByteArrayOutputStream();
        BOS.write("<div><ol>".getBytes());
        for(Map.Entry<String, ArrayList<Account.Message>> entry : account.messages.entrySet()) {
            ArrayList<Account.Message> messages = entry.getValue();
            for (Account.Message m : messages) {
                BOS.write(m.fromAccount.getBytes());
                BOS.write(" : ".getBytes());
                BOS.write(m.content.getBytes());
                BOS.write(("<br>").getBytes());
            }
            BOS.write("</ol></div>".getBytes());
        }
        BOS.flush();
        return BOS.toString();
    }
}

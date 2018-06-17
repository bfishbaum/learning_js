import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

public class Account implements Serializable {
    String name;
    long passwordHash;
    HashMap<String, ArrayList<Message>> messages;

    public static class Message {
        String content;
        String fromAccount;
        Instant timeSent;

        public Message(String content, String fromAccount, Instant timeSent) {
            this.content = content;
            this.fromAccount = fromAccount;
            this.timeSent = timeSent;
        }
    }

    public static Account getAccount(String _username, long _passwordHash){
        String acctFilename = String.format("%s_%d", _username, _passwordHash);
        File acctFile = new File(acctFilename);
        if(acctFile.exists()){
            Account account = ReadFromFile(acctFile);
            return account;
        }
        return null;
    }

    public Account(String name, String password){
        // TODO check if it already exists
        this.name = name;
        this.passwordHash = PasswordManager.hash(password);
        messages = new HashMap<String, ArrayList<Message>>();
    }

    public void WriteToFile(File file){
        try{
            FileOutputStream FOS = new FileOutputStream(file);
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(this);
            OOS.close();
            FOS.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static Account ReadFromFile(File file) {
        try {
            FileInputStream FIS = new FileInputStream(file);
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            Account m = (Account) OIS.readObject();
            OIS.close();
            FIS.close();
            return m;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Account TestAccount1(){
        Account toReturn = new Account("user1", "pass");
        toReturn.messages.put("user2", new ArrayList<Message>());
        toReturn.messages.get("user2").add(new Message("Hey", "user2", Instant.now()));
        toReturn.messages.get("user2").add(new Message("I'm here!", "user2", Instant.now()));
        return toReturn;
    }

}

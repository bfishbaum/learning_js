import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;

public class Parser {
    public static HashMap<String, String> parseParameterString(String paramString){
        HashMap<String, String> toReturn = new HashMap<String, String>();
        String[] params = paramString.split("&");
        for(String p : params){
            String[] parts = p.split("=");
            toReturn.put(parts[0], parts[1]);
        }
        return toReturn;
    }

    public static HashMap<String, String> parseParameterString(InputStream in) {
        try {
            String value = new String();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte buf[] = new byte[4096];
            for (int n = in.read(buf); n > 0; n = in.read(buf)) {
                out.write(buf, 0, n);
            }
            value = new String(out.toByteArray(), Charset.defaultCharset());
            return parseParameterString(value);
        } catch (IOException e) {
            return null;
        }
    }
}

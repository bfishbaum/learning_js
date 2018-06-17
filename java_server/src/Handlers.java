import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.*;

public class Handlers {
    public static void printHeaders(Headers headers){
        int x = headers.size();
        if(x == 0){
            System.out.println("Empty Headers");
        }

        for(Map.Entry<String, List<String>> header : headers.entrySet()) {
            String key = header.getKey();
            List<String> value = header.getValue();
            System.out.println(String.format("%s %s", key, value.toString()));
        }
        System.out.println();
    }

    public static void printInputStream(InputStream in) throws IOException{
        String value = new String();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte buf[] = new byte[4096];
        for (int n = in.read(buf); n > 0; n = in.read(buf)) {
            out.write(buf, 0, n);
        }
        value = new String(out.toByteArray(), Charset.defaultCharset());
        System.out.println(value);
    }

    public static class HandleNewAccount implements HttpHandler {
        public void handle(HttpExchange t){
            InputStream is = t.getRequestBody();
            HashMap<String, String> parameters = Parser.parseParameterString(is);
            String username = parameters.get("username");
            long passhash = PasswordManager.hash(parameters.get("password"));
        }
    }

    public static class AccountHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            OutputStream os = t.getResponseBody();
            String htmlResponse = MakeHTMLResponses.makeMessagesPage(Account.TestAccount1());
            t.sendResponseHeaders(200, htmlResponse.length());
            os.write(htmlResponse.getBytes());
            os.close();
        }
    }

    public static class FormHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            File htmlFile = new File("../jsp/form1.jsp");
            int htmlLength = (int) htmlFile.length();
            byte[] htmlResponse = new byte[htmlLength];
            FileInputStream fs = new FileInputStream(htmlFile);
            fs.read(htmlResponse);

            t.sendResponseHeaders(200, htmlLength);
            OutputStream os = t.getResponseBody();
            os.write(htmlResponse);
            os.close();
        }
    }

    public static class InitialHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            File htmlFile = new File("../html/form1.html");
            int htmlLength = (int) htmlFile.length();
            byte[] htmlResponse = new byte[htmlLength];
            FileInputStream fs = new FileInputStream(htmlFile);
            fs.read(htmlResponse);

            t.sendResponseHeaders(200, htmlLength);
            OutputStream os = t.getResponseBody();
            os.write(htmlResponse);
            os.close();
        }
    }

//
//    public static class MyHandler implements HttpHandler{
//        private int getValue(InputStream is) throws IOException{
//            byte[] b = new byte[1024];
//            int value = 0;
//            int l = 0;
//            while((l = is.read(b)) != -1) {
//                value += l;
//            }
//            return value;
//        }
//
//        public void handle(HttpExchange t) throws IOException {
//            InputStream is = t.getRequestBody();
//            Headers headers = t.getRequestHeaders();
//            String response;
//            if(headers.containsKey("username")){
//                response = String.format("Account %s", headers.get("username"));
//            } else {
//                int value = getValue(is);
//                response = String.format("penis %d", value);
//            }
//            t.sendResponseHeaders(200, response.length());
//            OutputStream os = t.getResponseBody();
//            os.write(response.getBytes());
//            os.close();
//        }
//    }

}

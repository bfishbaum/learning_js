import java.io.*;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;


class Server {

	public static void main(String[] args) throws IOException {
		HttpServerProvider var2 = HttpServerProvider.provider();
		HttpServer server = var2.createHttpServer(new InetSocketAddress(8000), 0);
	    server.createContext("/", new Handlers.InitialHandler());
		server.createContext("/account", new Handlers.AccountHandler());
	    server.setExecutor(null);
	    server.start();
		return;
	}

}

all: src/Server.class src/Account.class src/PasswordManager.class \
 		src/Handlers.class src/Parser.class src/MakeHTMLResponses.class \

%.class: %.java
	javac $< -Xlint:unchecked

clean:
	rm -f src/*.class

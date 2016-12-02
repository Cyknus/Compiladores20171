#/bin/bash
compile:
	byacc -dv -Jsemantic=Nodo Parser.y && jflex Flexer.flex
	
	javac Parser.java && javac Flexer.java

test:
	java Parser ${file}

clean:
	rm *.class
	rm *~
	rm *.output
	rm Parser.java
	rm ParserTokens.java
	rm ParserVal.java
	rm Flexer.java
compile:
	byacc -J -dv Parser.y && jflex Flexer.flex
	javac *.java
	java Parser ${file}

clean:
	rm *.class
	rm *~
	rm *.output
	rm Parser.java
	rm ParserTokens.java
	rm ParserVal.java
	rm Flexer.java
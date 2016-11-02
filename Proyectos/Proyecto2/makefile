parser:
	byacc -dv -J  Parser.y && jflex Flexer.flex
	javac Flexer.java && javac Parser.java Flexer.java
	java Parser ${file}
test:
	java Parser ${file}
clean:
	rm Parser.java Flexer.java y.output ParserTokens.java *.class
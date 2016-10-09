parser:
	byacc -dv -J  Arith.y && jflex Flexer.flex
	javac Flexer.java && javac Parser.java Flexer.java
	java Parser ${file}
test:
	java Parser ${file}

left:
	byacc -J -dv ListI.y && jflex List.flex
	javac Parser.java && javac List.java
	java Parser po.py

rigth:
	byacc -J -dv List.y && jflex List.flex
	javac Parser.java && javac List.java
	java Parser po.py

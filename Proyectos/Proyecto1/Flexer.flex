%%
%public
%class Flexer
%standalone

NEWLINE = [\n$]
INDENT=[ ]



%%

{NEWLINE}		      	{ System.out.println("NEWLINE");}
^{INDENT}+ 				{ if (yylength() > 0) 
						{ System.out.println("INDENT("+yylength()+")");}
						else { System.out.println("DEDENT");}  }
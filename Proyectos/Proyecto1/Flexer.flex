%%
%public
%class Flexer
%standalone

NEWLINE = [\n$]
INDENT=[\ \t]



%%

{NEWLINE}		      	{ System.out.println("NEWLINE");}
^{INDENT}+              { System.out.println("INDENT("+yylength()+")");}

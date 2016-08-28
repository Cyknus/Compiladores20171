%%
%public
%class Flexer
%standalone

LETRAA	=	a
LETRAB	=	b
LETRAC	=	c

EXPRESION = {LETRAA}{LETRAB} | ({LETRAA}{LETRAB})*{LETRAC}

%%

{EXPRESION}		      	{ System.out.println("CADENA("+yytext()+")");}				

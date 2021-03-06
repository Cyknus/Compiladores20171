public interface Visitante{

	public void visita(Nodo nodo);
	public void visita(NodoRaiz raiz);
	public void visita(NodoMas mas);
	public void visita(NodoMenos menos);
	public void visita(NodoPor por);
	public void visita(NodoPotencia potencia);
	public void visita(NodoDiv div);
	public void visita(NodoDivEntera divEntera);
	public void visita(NodoModulo mod);
	public void visita(NodoEq eq);
	public void visita(NodoMenorQ menorq);
	public void visita(NodoMayorQ mayorq);
	public void visita(NodoMenorEq menoreq);
	public void visita(NodoMayorEq mayoreq);
	public void visita(NodoMasEq maseq);
	public void visita(NodoMenosEq menoseq);
	public void visita(NodoEqEq eqeq);
	public void visita(NodoDif dif);
	public void visita(NodoOr or);
	public void visita(NodoAnd or);
	public void visita(NodoIf iff);
	public void visita(NodoIfAux ifaux);
	public void visita(NodoSuite suit);
	public void visita(NodoNot not);
	public void visita(NodoComa coma);
	public void visita(NodoPuntComa pyc);
	public void visita(NodoElif elif);
	public void visita(NodoElse elsee);
	public void visita(NodoWhile whilee);
	public void visita(NodoIn in);
	public void visita(NodoNotIn notin);
	public void visita(NodoDosPuntos dospun);
	public void visita(NodoNewLine newline);
	public void visita(HojaIdentificador id);
	public void visita(HojaEntera entera);
	public void visita(HojaCadena cadena);
	public void visita(HojaReal real);
	public void visita(HojaBoleano booleano);

}
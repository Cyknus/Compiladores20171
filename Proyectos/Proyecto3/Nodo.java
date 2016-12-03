/** Clase Nodo, donde se define el comportamiento para los nodos **/
import java.util.*;

public class Nodo{

  String tipo;
	String valor;
	Nodo izq;
	Nodo der;
	ArrayList<Nodo> hijos;

	public String getValor(){
		return valor;
	}

	public void setValor(String valor){
		this.valor = valor;
	}

  public void setHijoo(Nodo nodo){
    for (Nodo n: nodo.hijos) {
      this.hijos.add(n);
    }
  }

	public Nodo getHijoIzq(){
		 return izq;
  }

  public void setHijoIzq(Nodo izq){
		this.izq = izq;
  }

  public Nodo getHijoDer(){
		return der;
  }

  public void setHijoDer(Nodo der){
		this.der = der;
  }

  public void setHijo(Nodo hijo){
    	this.hijos.add(hijo);
  }

  public void setTipo(String tipo){
    this.tipo = tipo;
  }

  public String getTipo(){
    return tipo;
  }

  public void acepta(Visitante v){
  	v.visita(this);
  }


}


/** Clases para Nodo Raiz**/

class NodoRaiz extends Nodo{

  public NodoRaiz(String valor){
    this.valor = valor;
    this.hijos = new ArrayList<Nodo>();
  }

   public ArrayList<Nodo> getHijos(){
    return hijos;
  }

}

/******************************************************************************************************/

/** Clases para cada Nodo especifico**/

/**Clase para Nodo MAS**/
class NodoMas extends Nodo{
public NodoMas(String valor){
    this.valor = valor;
  }

  public NodoMas(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo MENOS**/
class NodoMenos extends Nodo{
public NodoMenos(String valor){
    this.valor = valor;
  }

  public NodoMenos(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}

/**Clase para Nodo POR**/
class NodoPor extends Nodo{
public NodoPor(String valor){
    this.valor = valor;
  }

  public NodoPor(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}

/**Clase para Nodo POTENCIA**/
class NodoPotencia extends Nodo{
public NodoPotencia(String valor){
    this.valor = valor;
  }

  public NodoPotencia(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo DIVISION**/
class NodoDiv extends Nodo{
public NodoDiv(String valor){
    this.valor = valor;
  }

  public NodoDiv(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo DIVISION ENTERA**/
class NodoDivEntera extends Nodo{
public NodoDivEntera(String valor){
    this.valor = valor;
  }

  public NodoDivEntera(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo MODULO**/
class NodoModulo extends Nodo{
public NodoModulo(String valor){
    this.valor = valor;
  }

  public NodoModulo(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}

/**Clase para Nodo IGUAL**/
class NodoEq extends Nodo{
  public NodoEq(String valor){
    this.valor = valor;
  }

  public NodoEq(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo MENORQ**/
class NodoMenorQ extends Nodo{
public NodoMenorQ(String valor){
    this.valor = valor;
  }

  public NodoMenorQ(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo MAYOR QUE**/
class NodoMayorQ extends Nodo{
public NodoMayorQ(String valor){
    this.valor = valor;
  }

  public NodoMayorQ(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}

/**Clase para Nodo MENOR IGUAL**/
class NodoMenorEq extends Nodo{
public NodoMenorEq(String valor){
    this.valor = valor;
  }

  public NodoMenorEq(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo MAYOR IGUAL**/
class NodoMayorEq extends Nodo{
public NodoMayorEq(String valor){
    this.valor = valor;
  }

  public NodoMayorEq(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo MAS IGUAL**/
class NodoMasEq extends Nodo{
public NodoMasEq(String valor){
    this.valor = valor;
  }

  public NodoMasEq(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo MENOS IGUAL**/
class NodoMenosEq extends Nodo{
public NodoMenosEq(String valor){
    this.valor = valor;
  }

  public NodoMenosEq(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo IGUAL IGUAL**/
class NodoEqEq extends Nodo{
public NodoEqEq(String valor){
    this.valor = valor;
  }

  public NodoEqEq(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo DIFERENTE**/
class NodoDif extends Nodo{
public NodoDif(String valor){
    this.valor = valor;
  }

  public NodoDif(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo OR**/
class NodoOr extends Nodo{
public NodoOr(String valor){
    this.valor = valor;
  }

  public NodoOr(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo AND**/
class NodoAnd extends Nodo{
public NodoAnd(String valor){
    this.valor = valor;
  }

  public NodoAnd(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo IF**/
class NodoIf extends Nodo{
public NodoIf(String valor){
    this.valor = valor;
  }

  public NodoIf(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo IFAUX**/
class NodoIfAux extends Nodo{
  public NodoIfAux(String valor){
    this.valor = valor;
    this.hijos = new ArrayList<Nodo>();
  }

  public void setHijo(Nodo hijo){
    this.hijos.add(hijo);
  }

  public void setHijosdeHijo(Nodo hijo){
    for (Nodo n: hijo.hijos) {
      this.hijos.add(n);
    }
  }

}

/**Clase para Nodo Suite**/
class NodoSuite extends Nodo{
  public NodoSuite(String valor){
    this.valor = valor;
    this.hijos = new ArrayList<Nodo>();
  }

  public void setHijo(Nodo hijo){
    this.hijos.add(hijo);
  }

  public void setHijosdeHijo(Nodo hijo){
    for (Nodo n: hijo.hijos) {
      this.hijos.add(n);
    }
  }

}



/**Clase para Nodo NOT**/
class NodoNot extends Nodo{
public NodoNot(String valor){
    this.valor = valor;
  }

  public NodoNot(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}

/**Clase para Nodo COMA**/
class NodoComa extends Nodo{
public NodoComa(String valor){
    this.valor = valor;
  }

  public NodoComa(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }
}

/**Clase para Nodo PUNTOYCOMA**/
class NodoPuntComa extends Nodo{
public NodoPuntComa(String valor){
    this.valor = valor;
  }

  public NodoPuntComa(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }
}


/**Clase para Nodo ELIF**/
class NodoElif extends Nodo{
public NodoElif(String valor){
    this.valor = valor;
  }

  public NodoElif(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo ELSE**/
class NodoElse extends Nodo{
public NodoElse(String valor){
    this.valor = valor;
  }

  public NodoElse(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo WHILE**/
class NodoWhile extends Nodo{
  public NodoWhile(String valor){
    this.valor = valor;
  }

  public NodoWhile(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo IN**/
class NodoIn extends Nodo{
public NodoIn(String valor){
    this.valor = valor;
  }

  public NodoIn(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo NOTIN**/
class NodoNotIn extends Nodo{
public NodoNotIn(String valor){
    this.valor = valor;
  }

  public NodoNotIn(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }
}



/**Clase para Nodo DOSPUNTOS**/
class NodoDosPuntos extends Nodo{

  public NodoDosPuntos(String valor){
    this.valor = valor;
  }

  public NodoDosPuntos(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}



/**Clase para Nodo NEWLINE**/
class NodoNewLine extends Nodo{

  public NodoNewLine(String valor){
    this.valor = valor;
  }

  public NodoNewLine(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/*************************************************************/
/** Clase Hoja**/
class Hoja extends Nodo{

}


/** Clases para cada Hoja en especifico **/

/**Clase para Hoja Identificador**/
class HojaIdentificador extends Hoja{

    public HojaIdentificador(String valor){
    this.valor = valor;
  }

  public HojaIdentificador(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }  
}

/**Clase para Nodo Entera**/
class HojaEntera extends Hoja{

  public HojaEntera(String valor){
    this.valor = valor;
  }

  public HojaEntera(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}


/**Clase para Nodo Cadena**/
class HojaCadena extends Hoja{
public HojaCadena(String valor){
    this.valor = valor;
  }

  public HojaCadena(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}

/**Clase para Hoja Real**/
class HojaReal extends Hoja{
	
  public HojaReal(String valor){
    this.valor = valor;
  }

  public HojaReal(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }
}

/**Clase para Hoja Booleano**/
class HojaBoleano extends Hoja{
	public HojaBoleano(String valor){
    this.valor = valor;
  }

  public HojaBoleano(String valor, Nodo izq, Nodo der) {
    this.valor = valor;
    this.izq = izq;
    this.der = der;
  }

}



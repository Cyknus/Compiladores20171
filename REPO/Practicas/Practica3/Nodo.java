public class Nodo{

	Object valor;
	Nodo izq;
	Nodo der;

	public Object getValor(){
		return valor;
	}


	public void setValor(Object valor){
		this.valor = valor
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

}

class NodoMas extends Nodo{

}

class NodoMenos extends Nodo{

}

class NodoPor extends Nodo{

}

class NodoPotencia extends Nodo{

}

class NodoDiv extends Nodo{

}

class NodoDivEntera extends Nodo{

}

class NodoModulo extends Nodo{


}

class NodoEq extends Nodo{

}

class NodoMenorQ extends Nodo{

}

class NodoMayorQ extends Nodo{

}

class NodoMenorEq extends Nodo{

}

class NodoMayorEq extends Nodo{

}

class NodoMasEq extends Nodo{

}

class NodoMenosEq extends Nodo{

}

class NodoEqEq extends Nodo{

}

class NodoDif extends Nodo{

}

class NodoOr extends Nodo{

}

class NodoAnd extends Nodo{

}



class Hoja extends Nodo{
}

class HojaIdentificador extends Hoja{

    HojaIdentificador(String identificador){
		valor = (Object)identificador;
    }
	
	public String getValue(){
		return (String) this.valor;
    }    
}

class HojaEntera extends Hoja{

    HojaEntera(Integer val){
		valor = (Object) val;
    }

    public Integer getValue(){
		return (Integer) this.valor;
    }    
}

class HojaCadena extends Hoja{
	HojaCadena(String cadena){
		valor = (Object)cadena;
	}

	public String getValue(){
		return (String) this.valor;
    }
}

class HojaReal extends Hoja{
	HojaReal(Double val){
		valor = (Object) val;
	}

	public Double getValue(){
		return (Double) this.valor;
    }
}

class HojaBoleano extends Hoja{
	HojaBoleano(Boolean bol){
		valor = (Object) bol;
	}

	public Boolean getValue(){
		return (Boolean) this.valor;
    }

}

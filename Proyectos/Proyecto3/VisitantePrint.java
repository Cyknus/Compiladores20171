/**Clase que implementa la interfaz Visitante con las acciones que se desean realizar cuando se esté
   visitando cada elemento concreto, es decir;
   -  Verificar si se usaron identificadores, si se usaron verificar que se hayan declarado
   -  Verificar tipos de las operaciones
   - En caso de no cumplir alguna de las dos anteriores mandamos error
   **/

public class VisitantePrint implements Visitante{
	Tipos tipo;
	Simbolos simbolo;

	public VisitantePrint(){
		tipo = new Tipos();
		simbolo = new Simbolos();
	}

	public void visita(Nodo nodo){
		System.out.print("Nodo Genérico ");
	}

	public void visita(NodoRaiz raiz){
		for(Nodo n : raiz.getHijos()){
			n.acepta(this);
		}
	}

	public void visita(NodoMas nodo){
		System.out.print("Nodo Mas ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoMenos nodo){
		System.out.print("Nodo Menos ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoPor nodo){
		System.out.print("Nodo Por ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoPotencia nodo){
		System.out.print("Nodo Potencia ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoDiv nodo){
		System.out.print("Nodo División ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoDivEntera nodo){
		System.out.print("Nodo DivisionEntera ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoModulo nodo){
		System.out.print("Nodo Modulo ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoEq nodo){
		System.out.print("Nodo Igual ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoMenorQ nodo){
		System.out.print("Nodo MenorQue ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoMayorQ nodo){
		System.out.print("Nodo MayorQue ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoMenorEq nodo){
		System.out.print("Nodo MenorIgual ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoMayorEq nodo){
		System.out.print("Nodo MenorIgual ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoMasEq nodo){
		System.out.print("Nodo MasIgual ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador) {
			nodo.getHijoIzq().acepta(this);
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        		if(izq == null){
          			System.out.println("ERROR: iidentificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          			System.exit(1);
        		}
				if(nodo.getHijoDer() instanceof HojaIdentificador){
					String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        			if(der == null){
          				System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          				System.exit(1);
        			}
				}else if(nodo.getHijoDer() instanceof HojaEntera || nodo.getHijoDer() instanceof HojaReal || nodo.getHijoDer() instanceof HojaBoleano){
					System.out.println("Correcto(+=)");
				}else{
					System.out.println("ERROR(+=)");
				}
		}else{
			System.out.println("ERROR(+=)");
			System.exit(1);
		}
	}

	public void visita(NodoMenosEq nodo){
		System.out.print("Nodo menosIgual ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador) {
			nodo.getHijoIzq().acepta(this);
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        		if(izq == null){
          			System.out.println("ERROR: iidentificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          			System.exit(1);
        		}
				if(nodo.getHijoDer() instanceof HojaIdentificador){
					String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        			if(der == null){
          				System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          				System.exit(1);
        			}
				}else if(nodo.getHijoDer() instanceof HojaEntera || nodo.getHijoDer() instanceof HojaReal || nodo.getHijoDer() instanceof HojaBoleano){
					System.out.println("Correcto(-=)");
				}else{
					System.out.println("ERROR(-=)");
				}
		}else{
			System.out.println("ERROR(-=)");
			System.exit(1);
		}

	}

	public void visita(NodoEqEq nodo){
		System.out.print("Nodo IgualIgual ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoDif nodo){
		System.out.print("Nodo Diferente ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoOr nodo){
		System.out.print("Nodo Or ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoAnd nodo){
		System.out.print("Nodo And ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if(nodo.getHijoIzq() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+ nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	if(nodo.getHijoDer() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Entero"));
        	}else if(nodo.getHijoDer() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Cadena"));
        	}else if(nodo.getHijoDer() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Real"));
        	}else if(nodo.getHijoDer() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion(izq,nodo.getValor(),"Booleano"));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if(nodo.getHijoDer() instanceof HojaIdentificador){
			String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoDer().getValor()+" no fue declarado");
          		System.exit(1);
        	}if(nodo.getHijoIzq() instanceof HojaEntera){
        		nodo.setTipo(tipo.operacion("Entero",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaCadena){
        		nodo.setTipo(tipo.operacion("Cadena",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaReal){
        		nodo.setTipo(tipo.operacion("Real",nodo.getValor(),der));
        	}else if(nodo.getHijoIzq() instanceof HojaBoleano){
        		nodo.setTipo(tipo.operacion("Booleano",nodo.getValor(),der));
        	}else{
        		System.out.println("ERROR de Tipado");
          		System.exit(1);
        	}
		}else if (nodo.getHijoIzq() instanceof HojaIdentificador && nodo.getHijoDer() instanceof HojaIdentificador){
			String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	String der = simbolo.lookUp(nodo.getHijoDer().getValor());
        	if(der == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
        	nodo.setTipo(tipo.operacion(izq,nodo.getValor(), der));
		}else{
			nodo.setTipo(tipo.operacion(nodo.getHijoIzq().getTipo(),nodo.getValor(),nodo.getHijoDer().getTipo()));
		}
	}

	public void visita(NodoIf nodo){
		System.out.print("Nodo If ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if (nodo.getHijoIzq() instanceof HojaIdentificador) {
        	String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
      	}
	}

	public void visita(NodoIfAux nodo){
		System.out.print("Nodo IfAux ");
		nodo.acepta(this);
	}

	public void visita(NodoSuite nodo){
		System.out.print("Nodo IfAux ");
		nodo.acepta(this);
	}

	public void visita(NodoNot nodo){
		System.out.print("Nodo Not");
	}

	public void visita(NodoComa nodo){
		System.out.print("Nodo Coma");
	}

	public void visita(NodoPuntComa nodo){
		System.out.print("Nodo PuntoYcoma");
	}

	public void visita(NodoElif nodo){
		System.out.print("Nodo Elif ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if (nodo.getHijoIzq() instanceof HojaIdentificador) {
        	String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
      	}
	}

	public void visita(NodoElse nodo){
		System.out.print("Nodo Else ");
		nodo.getHijoDer().acepta(this);
	}

	public void visita(NodoWhile nodo){
		System.out.print("Nodo while ");
		if(nodo.getHijoIzq() != null && nodo.getHijoDer() != null){
			nodo.getHijoIzq().acepta(this);
			nodo.getHijoDer().acepta(this);
		}
		if (nodo.getHijoIzq() instanceof HojaIdentificador) {
        	String izq = simbolo.lookUp(nodo.getHijoIzq().getValor());
        	if(izq == null){
          		System.out.println("ERROR: identificador "+nodo.getHijoIzq().getValor()+" no fue declarado");
          		System.exit(1);
        	}
      	}
	}

	public void visita(NodoIn nodo){
		System.out.print("Nodo In");
	}

	public void visita(NodoNotIn nodo){
		System.out.print("Nodo NotIn");
	}

	public void visita(NodoDosPuntos nodo){
		System.out.print("Nodo DosPuntos");
	}

	public void visita(NodoNewLine nodo){
		System.out.print("Nodo newline");
	}

	public void visita(HojaIdentificador id){
		System.out.print("Hoja Identificador");
	}

	public void visita(HojaEntera entera){
		System.out.print("Hoja Entera");
	}

	public void visita(HojaCadena cadena){
		System.out.print("Hoja Cadena");
	}

	public void visita(HojaReal real){
		System.out.print("Hoja Real");
	}

	public void visita(HojaBoleano booleano){
		System.out.print("Hoja Booleana");
	}


}
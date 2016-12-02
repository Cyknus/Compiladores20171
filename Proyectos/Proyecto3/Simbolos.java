import java.util.Hashtable;

/**Tabla de simbolos**/
public class Simbolos{
	Hashtable<String, String> h;

	public Simbolos(){
		h = new Hashtable<String, String>();
	}

	/**Regresa el valor asociado a name en la tabla de símbolos o null en caso de que no haya sido encontrado en la tabla**/
	public String lookUp(String name){
		if(h.containsKey(name)){
			return h.get(name);
		}else{
			return null;
		}
	}

	/**Guarda info en h(name)*/
	public void insert(String name, String info){
		h.put(name,info);
	}

}
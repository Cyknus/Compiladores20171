public class Nodo{

	public String getNodo(){
		return "";
	}

}

class Hoja extends Nodo{
	
	public String getHoja(){
		return "Soy una hoja";
	}

}

class HojaEntera extends Hoja{
	String entero;

	public HojaEntera(String valor){
		this.entero = valor;
	}

	public String getEntero(){
		return entero;
	} 
}
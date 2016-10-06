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

	int entero;
	public HojaEntera(){
		int entero;
	}

	public void getEntero(int a){
		this.entero = a;
	} 
}
import java.util.ArrayList;

public class Tipos{

  /**Representación tablas
  			 Booleano Entero Real Cadena
  	Booleano
  	Entero
  	Real
  	Cadena
  
  	Para facilitar su uso le daremos valores a cada tipo:

  	Booleano: 0
  	Entero: 1
  	Real: 2
  	Cadena: 3
  	Error: -1
  **/

  /**Operador suma**/
  private final int[][] suma = new int[][]{
    {0, 1, 2, 3},
    {1, 1, 2, 3},
    {2, 2, 2, 3},
    {3, 3, 3, 3}
  };

  /**Operador resta**/
  private final int[][] resta = new int[][]{
    {0, 1, 2, 3},
    {1, 1, 2, 3},
    {2, 2, 2, 3},
    {3, 3, 3, 3}
  };

  /**Operador multiplicacion**/
  private final int[][] mult = new int[][]{
    {1, 1, 2, 3},
    {1, 1, 2, 3},
    {2, 2, 2,-1},
    {3, 3,-1,-1}
  };

  /**Operador potencia**/
  private final int[][] potencia = new int[][]{
    {1, 1, 2,-1},
    {1, 1, 2,-1},
    {2, 2, 2,-1},
    {-1,-1,-1,-1}
  };

  /**Operador division**/
  private final int[][] div = new int[][]{
    {1, 1, 2, -1},
    {1, 1, 2, -1},
    {2, 2, 2, -1},
    {-1,-1,-1,-1}
  };

  /**Operador división entera**/
  private final int[][] divEntera = new int[][]{
    {1, 1, 2,-1},
    {1, 1, 2,-1},
    {2, 2, 2,-1},
    {-1,-1,-1,-1}
  };

  /**Operador modulo**/
  private final int[][] mod = new int[][]{
    {1, 1, 2,-1},
    {1, 1, 2,-1},
    {2, 2, 2,-1},
    {-1,-1,-1,-1}
  };

  /**Operador and**/
  private final int[][] and = new int[][]{
    {0, 1, 2, 3},
    {0, 1, 2, 3},
    {0, 1, 2, 3},
    {0, 1, 2, 3}
  };

  /**Operador or**/
  private final int[][] or = new int[][]{
    {0, 0, 0, 0},
    {1, 1, 1, 1},
    {2, 2, 2, 2},
    {3, 3, 3, 3}
  };

  /**Cualquier operador de comparación**/
  private final int[][] comparacion = new int[][]{
    {0, 0, 0, 0},
    {0, 0, 0, 0},
    {0, 0, 0, 0},
    {0, 0, 0, 0}
  };

	ArrayList<String> tipos;
 /**Constructor que incializa una lista con los tipos para trabajar con sus indices como se dijo anteriormente**/
  public Tipos() {
    tipos = new ArrayList<String>();
    tipos.add("Booleano");
    tipos.add("Entero");
    tipos.add("Real");
    tipos.add("Cadena");
  }

  /**Regresa tipo de operación**/
  public String operacion (String izq, String operador, String der) {
    String tipOperacion = "";
    if (tipos.contains(izq) && tipos.contains(der)){
      int tipo = 0;
      switch (operador) {
        case "+": 
        	tipo = suma[tipos.indexOf(izq)][tipos.indexOf(der)];
           	break;
        case "-": 
        	tipo = resta[tipos.indexOf(izq)][tipos.indexOf(der)];
            break;
        case "*": 
        	tipo = mult[tipos.indexOf(izq)][tipos.indexOf(der)];
            break;
        case "**": 
        	tipo = potencia[tipos.indexOf(izq)][tipos.indexOf(der)];
            break;
        case "/": 
        	tipo = div[tipos.indexOf(izq)][tipos.indexOf(der)];
            break;
        case "//": 
        	tipo = divEntera[tipos.indexOf(izq)][tipos.indexOf(der)];
            break;
        case "%": 
        	tipo = mod[tipos.indexOf(izq)][tipos.indexOf(der)];
            break;
        case "and": 
        	tipo = and[tipos.indexOf(izq)][tipos.indexOf(der)];
            break;
        case "or": 
        	tipo = or[tipos.indexOf(izq)][tipos.indexOf(der)];
            break;
        case "<": 
        	tipo = comparacion[tipos.indexOf(izq)][tipos.indexOf(der)];
            break;
        case ">": 
        	tipo = comparacion[tipos.indexOf(izq)][tipos.indexOf(der)];
            break;
        case "<=": 
        	tipo = comparacion[tipos.indexOf(izq)][tipos.indexOf(der)];
            break;
        case ">=": 
        	tipo = comparacion[tipos.indexOf(izq)][tipos.indexOf(der)];
            break;
        case "==": 
        	tipo = comparacion[tipos.indexOf(izq)][tipos.indexOf(der)];
            break;
        case "!=": 
        	tipo = comparacion[tipos.indexOf(izq)][tipos.indexOf(der)];
            break;
        case "<>": 
        	tipo = comparacion[tipos.indexOf(izq)][tipos.indexOf(der)];
            break;
        default: 
        	System.out.println(" ");
        	break;
      }
      if(tipo >= 0){
        tipOperacion = tipos.get(tipo);
      }else{
        System.out.println("Error de Tipado");
        System.exit(1);
      }
    }

    return tipOperacion;
  }

}
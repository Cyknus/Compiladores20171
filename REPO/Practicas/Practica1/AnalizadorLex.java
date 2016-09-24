import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;
import javafx.util.Pair;

/**
 * Clase para analizador léxico que reconoce cadenas formadas por las 
 * expresiones regulares: ab + (ab)* c
 *
 * @author Tlacuahes
 */

public class AnalizadorLex {

    
    /**
    * Regresa una lista de tokens provenientes de la cadena de entrada. Si
    * la cadena no forma parte de las expresiones regulares se manda un ERROR diciendo
    * no puede ser procesada más la cadena. 
    * @param estados estados que contiene el autómata.
    * @param alfabeto alfabeto que acepta el autómata.
    * @param inicial estado inicial del autómata.
    * @param finales estados finales que contiene el autómata.
    * @param funcion funcion de transición del autómata.
    * @param entrada cadena que puede o no ser aceptada por el autómata.
    */


    public static ArrayList<String> tokenize(String[] estados, String[] alfabeto, String inicial, String[] finales, HashMap<String,String> funcion, String entrada){
        
        HashMap<String,Boolean> failed_previously = new HashMap<>();
        Stack<String> pila = new Stack<>();
        ArrayList<String> tokens = new ArrayList<>();
                
        String q;
        String buttom = "Bu";
        char[] cadenaEntrada;
        String subcadena;
        String tupla;
        int z = 0;
        cadenaEntrada = entrada.toCharArray();
        
        for (String estado : estados) {
            for(int i=1; i<= entrada.length()+1;i++){
                failed_previously.put(estado+","+String.valueOf(i),false);
           }
        }

        int j = 1;
        boolean flag = true;
        
        while(flag){
            q = inicial;
            pila.push(buttom+","+String.valueOf(j));

            /*Scan for tokens*/
            while((j<= entrada.length()) && (funcion.containsKey("("+q+","+cadenaEntrada[j-1]+")")) && (!(failed_previously.get(q+","+String.valueOf(j))))){
                if (Arrays.asList(finales).contains(q))
                    pila.clear();
            
                pila.push(q+","+String.valueOf(j));
                q = funcion.get("("+q+","+cadenaEntrada[j-1]+")");
                j= j+1;
                
            }
            
            
            /*Backtrack to the most recent final state*/
            while(!(Arrays.asList(finales).contains(q))){
                try {
                failed_previously.replace(q+","+String.valueOf(j), true);
                tupla = (pila.pop());
                q= tupla.substring(0,2);
                j= Integer.valueOf(tupla.substring(3));
                                        
                if(q.equals(buttom)){
                    flag = false;
                    tokens.add("ERROR: NO ES POSIBLE TOKENIZAR");
                    return tokens;
                   }else{
                    flag = true;
                    }
                
                } catch (EmptyStackException exc) {
		}
            }
            
            subcadena = entrada.substring(z,j-1);
            tokens.add(subcadena);
            z=j-1;

            /*See if we are done*/
            if (j> entrada.length())
                flag = false; //Sucess

        }
        return tokens;
    }
    
    /**
     * Método main.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HashMap<String,String> funciones = new HashMap<>();
        funciones.put("(q0,a)","q1");
        funciones.put("(q1,b)","q2");
        funciones.put("(q2,c)","q5");
        funciones.put("(q2,a)","q3");
        funciones.put("(q3,b)","q4");
        funciones.put("(q4,a)","q3");
        funciones.put("(q4,c)","q5");
        String[] estados = {"q0","q1","q2","q3","q4","q5"};
        String[] alfabeto = {"a","b","c"};
        String[] finales = {"q2","q5"};
        ArrayList<String> tokens = tokenize(estados, alfabeto,"q0",finales , funciones,"ababababababababababababababc");
    
        System.out.println("Tokens:"+"\n");
        for(String token : tokens){
            System.out.println("Cadena("+token+")\n");
        }
    }
    
}

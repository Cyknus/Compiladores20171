/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author tlacuahes
 */
public class AnalizadorLex {

    
    public static ArrayList<String> tokenize(String[] estados, String[] alfabeto, String inicial, String[] finales, HashMap<String,String> funcion, String entrada){
        
        HashMap<String,Boolean> failed_previously = new HashMap<>();
        Stack<String> pila = new Stack<>();
        ArrayList<String> tokens = new ArrayList<>();
                
        String q;
        String buttom = "Buttom";
        char[] cadenaEntrada;
        String subcadena;
        int z = 0;
        
        cadenaEntrada = entrada.toCharArray();
        
        for (String estado : estados) {
            for(int i=1; i<= entrada.length();i++){
                failed_previously.put(estado+","+String.valueOf(i),false);
           }
        }
        
        int j = 1;
        boolean flag = true;
        
        while(flag){
            q = inicial;
            pila.push(buttom);
            
            /*Scan for tokens*/
            while((j<= entrada.length()) && (funcion.containsKey("("+q+","+cadenaEntrada[j-1]+")")) && (!(failed_previously.get(q+","+String.valueOf(j))))){
                pila.push(q);
                q = funcion.get("("+q+","+cadenaEntrada[j-1]+")");
                j= j+1;
            }
            
            
            /*Backtrack to the most recent final state*/
            while(!(Arrays.asList(finales).contains(q))){
                try {
                failed_previously.replace(q+","+String.valueOf(j), true);
                q = pila.pop();
                j= j-1;
                
                if(q.equals(buttom))
                    flag = false;
                    break;//"Error: tokenización no es posible"
                
                } catch (EmptyStackException exc) {
		}
            }
            subcadena = entrada.substring(z,j-1);
            tokens.add(subcadena);
            z=j-2;
            //print(j) se reporta el token! guardarlos en una lista... para devolverla al final
            if (j> entrada.length())
                flag = false; //Exito!
            
            pila.clear();
        }
        return tokens;
    }
    
    /**
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
        ArrayList<String> tokens = tokenize(estados, alfabeto,"q0",finales , funciones,"abababc");
         
        for(String token : tokens){
            System.out.println(token+"\n");
        }
    }
    
}

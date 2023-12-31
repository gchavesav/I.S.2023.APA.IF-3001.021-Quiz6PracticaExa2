package util;
import domain.Graph.EdgeWeight;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Profesor Lic. Gilberth Chaves Avila
 */
public class Utility {

    private static Random random;    // pseudo-random number generator
    private static long seed;        // pseudo-random number generator seed

    // static initializer
    static {
        // this is how the seed was set in Java 1.4
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    public static int random(){
        return 1+(int) Math.floor(Math.random()*99);
    }

    public static int random(int bound){
        //return 1+random.nextInt(bound);
        return 1+(int) Math.floor(Math.random()*bound);
    }

    public static int random(int lowBound, int highBound){
        int value=0;
        do{
            value = lowBound+(int) Math.floor(Math.random()*highBound);
        }while(!isBetween(value, lowBound, highBound));
        return value;
    }

    public static boolean isBetween(int value, int low, int high) {
        return low <= value && value <= high;
    }

    public static String format(double value){
        return new DecimalFormat("###,###,###,###.##")
                .format(value);
    }

    public static String perFormat(double value){
        //#,##0.00 '%'
        return new DecimalFormat("#,##0.00'%'")
                .format(value);
    }

    public static String dateFormat(Date value){
        return new SimpleDateFormat("dd/MM/yyyy")
                .format(value);
    }

    /**
     * a < b return -1
     * a > b return 1
     * a == b return 0
     * @param a
     * @param b
     * @return
     **/
    public static int compare(Object a, Object b) {
        switch(instanceOf(a, b)){
            case "Integer":
                Integer int1 = (Integer)a; Integer int2 = (Integer)b;
                return int1 < int2? -1 :
                        int1 > int2? 1 : 0; //0==equal
            case "String":
                String str1 = (String)a; String str2 = (String)b;
                return str1.compareToIgnoreCase(str2)<0? -1 :
                        str1.compareToIgnoreCase(str2)>0? 1 : 0;
            case "Character":
                Character ch1 = (Character)a; Character ch2 = (Character)b;
                return ch1.compareTo(ch2)<0? -1 :
                        ch1.compareTo(ch2)>0? 1 : 0;
            case "EdgeWeight":
                EdgeWeight ew1 = (EdgeWeight)a; EdgeWeight ew2 = (EdgeWeight)b;
                return ew1.getEdge().equals(ew2.getEdge())? 0 :
                        ew1.getEdge().toString().compareTo(ew2.getEdge().toString())<0? -1: 1;
        }
        return 2; //Unknown
    }

    private static String instanceOf(Object a){
        if(a instanceof Integer) return "Integer";
        if(a instanceof String) return "String";
        if(a instanceof Character) return "Character";
        return "Unknown"; //desconocido
    }

    public static String instanceOf(Object a, Object b){
        if(a instanceof Integer&&b instanceof Integer) return "Integer";
        if(a instanceof String&&b instanceof String) return "String";
        if(a instanceof Character&&b instanceof Character) return "Character";
        if(a instanceof EdgeWeight &&b instanceof EdgeWeight) return "EdgeWeight";
        return "Unknown"; //desconocido
    }

    public static char getAlphabet(){
        char alfabeto[] = new char[26] ;
        int cont=0;
        for(char i='A';i<='Z';i++)
            alfabeto[cont++] = i;
        return alfabeto[(int) (Math.random() * 27 - 1)];
    }

    public static int maxArray(int[] a) {
        int max = a[0]; //first element
        for (int i = 1; i < a.length; i++) {
            if(a[i]>max){
                max=a[i];
            }
        }
        return max;
    }

    public static void fill(int a[]){
        int n = a.length;
        for (int i = 0; i < n; i++){
            a[i] = random(99);
        }
    }

    public static void fill(int a[], int bound){
        int n = a.length;
        for (int i = 0; i < n; i++){
            a[i] = random(bound);
        }
    }

    public static String show(int[] a, int bound) {
        String result = "";
        for (int i = 0; i < bound; i++) {
            result += a[i] + ", ";

        }
        return result;
    }

    public static String show(int[] a, int low, int high) {
        String result = "";
        for (int i = low; i < high; i++) {
            result += a[i] + ", ";

        }
        return result;
    }

}
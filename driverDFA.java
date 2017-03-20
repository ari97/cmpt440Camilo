import java.io.*;
/**
  This is the program for ManWolf problem.
*/
   public class driverDFA{
     public static void main(String[] args)
           throws IOException {
             ManWolf m = new ManWolf(); // The DFA
             String s = args[0];
             m.reset();
             m.process(s);
             if (m.accepted()){
               System.out.println("That is a solution.");
             } else{
               System.out.println("That is not a solution.");
             }
           }
}

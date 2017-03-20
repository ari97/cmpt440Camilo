/**
  * This class represents the execution of a DFA,
  * which runs through a given string
  * and determines whether or not it is a solution
  * to the Man-Wolf-Sheep-Cabbage problem.
*/
public class ManWolf{
  /*
    * Constants q0 through q10 represent states, and
    * a private int holds the current state code.
  */
  private static final int q0 = 0;
  private static final int q1 = 1;
  private static final int q2 = 2;
  private static final int q3 = 3;
  private static final int q4 = 4;
  private static final int q5 = 5;
  private static final int q6 = 6;
  private static final int q7 = 7;
  private static final int q8 = 8;
  private static final int q9 = 9;
  private static final int q10 = 10;
  private int state;
  private int correctVal; //Used to calculate where the next state is

   /*
    * The transition function represented as an array.
    * The next state from current state s and character c
    * is at delta[s,c-'0'].
    */
     static private int[][] delta =
        {
          {q1},
          {q2},
          {q3,q5},
          {q4},
          {q7},  //q4's next state is q7
          {q6},
          {q7},
          {q8},
          {q9},
        };
     /**
      * Make one transition on each char in the given
      * string.
      * @param in the String to use
      */
     public void process(String in) {
       for (int i = 0; i < in.length(); i++) {
         char c = in.charAt(i);
         switch(state){
           case 0:  correctVal = 'g';
                    break;
           case 1:  correctVal = 'n';
                    break;
           case 2:  if(c == 'w'){
                      correctVal = 'w';
                    } else if(c == 'c'){
                      correctVal = 'b';
                    }
                    break;
           case 3:  correctVal = 'g';
                     break;
           case 4:   correctVal = 'c';
                    break;
           case 5:  correctVal = 'g';
                     break;
           case 6:  correctVal = 'w';
                    break;
           case 7:  correctVal = 'n';
                     break;
           case 8:  correctVal = 'g';
                     break;
         }
         try {
           state = delta[state][c-correctVal];
         }
         catch (ArrayIndexOutOfBoundsException ex) {
           state = q10;
         }
       }
     }

     public void reset() {
       state = q0;
     }
   /**
    * Test whether the DFA accepted the string.
    * @return true if the final state was accepting
    */
   public boolean accepted() {
     return state==q9;
   }



} // end class

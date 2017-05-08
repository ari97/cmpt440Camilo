/**
  * A deterministic finite-state automaton that
  * recognizes strings as potentional definite
  * clause grammers. These grammars can be typically
  * described by the following structure: a sentence(s)
  * is composed of a noun phrase(np) and a verb phrase(vp).
  * A noun phrase(np) is composed of a determinate(d) followed
  * by a noun. A verb phrase can be descrbied as either
  * a verb or a verb and a determinate. Therefore they can be simplified as:
  * s -> d,n,v,d,n | s -> d,n,v.
  */
public class GrammarDFA {
  /*
  * Constants q0 through q6 represent states, and
  * a private int holds the current state code.
  * Arrays of words represent the current verbs, nouns,
  * conjuctions and determinates the program can currently soul.
  */
   private static final int q0 = 0;
   private static final int q1 = 1;
   private static final int q2 = 2;
   private static final int q3 = 3;
   private static final int q4 = 4;
   private static final int q5 = 5;
   private static final int q6 = 6; //aka the failed state
   private int state;
   private static final String[] determinates = {
     "the","an", "a", "this","that"
   };
   private static final String[] nouns = {
     "man", "woman", "dog", "boy", "girl", "child","poodle","adult", "officer", "teacher", "bully"
   };
   private static final String[] verbs = {
     "kills", "shoots", "eats", "destroys", "consumes", "analyzes", "punches","fights"
   };
   private static final String[] conjunctions = {
     "but", "and", "or", "yet", "but,","and,","or,","yet,"
   };
  /**
  * The transition function.
  * param s state code (an int)
  * param c char to make a transition on
  * return the next state code
  */
  static private int delta(int s, char c) {
   switch (s) {
     case q0: switch (c) {
           case 'd': return q1;
           default: return q6;
         }
     case q1: switch (c) {
           case 'n': return q2;
           default: return q6;
         }
     case q2: switch (c) {
           case 'v': return q3;
           default: return q6;
         }
     case q3: switch (c) {
           case 'd': return q4;
           case 'c': return q0;
           default: return q6;
         }
     case q4: switch(c){
           case 'n': return q5;
           default: return q6;
     }
     case q5: switch(c){
           case 'c': return q0;
           default: return q6;
     }
     default: return q6;
   }
  }
  /**
  * Reset the current state to the start state.
  */
  public void reset() {
   state = q0;
  }
   /**
    * Make one transition on each char in the given
    * string.
    * param in the String to use
    */


   /**
    * Test whether the DFA accepted the string.
    * return true if the final state was accepting
    */
   public boolean accepted() {
     if ((state==q3) || (state==q5)){
       return true;
     } else{
       return false;
     }

   }
   public void process(String[] in) {
     String convertedSentence = convert(in);
     for (int i = 0; i < convertedSentence.length(); i++) {
       char c = convertedSentence.charAt(i);
       state = delta(state, c);
     }
   }

   /**
    * Convert the given potentional sentence into grammar symbols
    * listed above the in the description.
   */
   public String convert(String[] listOfWords){
     String convertedSentence = "";
     for(int i=0; i<listOfWords.length;i++){
       String word = listOfWords[i].toLowerCase();
       convertedSentence += getSymbol(word);
     }
     return convertedSentence;
   }

   /**
    * Check if word is listed in our list of 'accepted' words
    * If so, print appropriate grammar symbol, if not
    * return x as the character.
   */
   public String getSymbol(String word){
     for(int i=0;i<determinates.length;i++){
       if(word.equals(determinates[i])){
         return "d";
       }
     }
     for(int i=0;i<nouns.length;i++){
       if(word.equals(nouns[i])){
         return "n";
       }
     }
     for(int i=0;i<verbs.length;i++){
       if(word.equals(verbs[i])){
         return "v";
       }
     }
     for(int i=0;i<conjunctions.length;i++){
       if(word.equals(conjunctions[i])){
         return "c";
       }
     }
     return "x"; //This is an unrecognized word
   }
}

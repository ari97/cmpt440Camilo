import java.io.*;
/**
* A Java application to demonstrate the GrammarDFA class by
* using it to filter the standard input stream. Those
* lines that are accepted by Mod3 are echoed to the
* standard output. The program converts the given sentences
* into the grammar symbols, which will then be filtered
* through the DFA to determine if they are appripriately
* definite clause grammars.
*/
public class CFGProgram {
 public static void main(String[] args)
      throws IOException {
    GrammarDFA s = new GrammarDFA(); // The DFA
    BufferedReader in =  // Standard input
    new BufferedReader(new InputStreamReader(System.in));
    // Read lines until EOF
    String line = null;
    String result = null;
    while ((line = in.readLine()) != null)
    {
      String[] words = line.split("\\s+");
      for (int i = 0; i < words.length; i++) {
        words[i] = words[i].replaceAll("[^\\w]", "");
      }
      s.reset();
      s.process(words);
      result = (s.accepted()) ? " is a DCG sentence.":" is not a DCG sentence.";
      System.out.println("\""+line+"\""+ result);
    }
 }
}

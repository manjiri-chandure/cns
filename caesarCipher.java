import java.util.*;
public class caesarCipher{
    public static StringBuffer Encrypt(String s, int shift){
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<s.length(); i++){
              // for capital letter
              //for small letter
              // non letter

            //   if(Character.isUpperCase(s.charAt(i))){

            //   }
            char c = s.charAt(i);
            if(c >= 'A' && c<='Z'){
                // shift by shift value
                int curr = (int) c;
                char cc = (char)((curr + shift - 65)%26 + 65);
                sb.append(cc);
            }
            else if(c >= 'a' && c<= 'z'){
                int curr = (int) c;
                char cc = (char)((curr + shift - 97)%26 + 97);
                sb.append(cc); 
            }
            else{
                sb.append(c);
            }
              
        }
        return sb;
    }

    public static StringBuffer Decrypt(String s, int shift){
         StringBuffer sb = new StringBuffer();
         for(int i=0; i<s.length(); i++){
             char c = s.charAt(i);
            if(c >= 'A' && c<='Z'){
                // shift by shift value
                int curr = (int) c;
                char cc = (char)((curr - shift + 26 - 65)%25 + 65);
                sb.append(cc);
            }
            else if(c >= 'a' && c<= 'z'){
                int curr = (int) c;
                char cc = (char)((curr - shift + 26 - 97)%25 + 97);
                sb.append(cc); 
            }
            else{
                sb.append(c);
            }
         }
         return sb;
    }
    public static void main(String args[]){
         Scanner sc = new Scanner(System.in);
          System.out.println("Enter PlainText: ");
          String st = sc.nextLine();
          System.out.println(Encrypt(st, 1));
          System.out.println(Decrypt(st, 1));
    }
}


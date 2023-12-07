import java.util.*;
public class CaesarDecrypt {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter cipher text: ");
        String ciphertext =  sc.nextLine();
       decrypt(ciphertext);
    }

    public static void decrypt(String ciphertext) {
        for (int s=0; s<26; s++) {
             StringBuilder sb = new StringBuilder("");
             for(int i=0; i<ciphertext.length(); i++){
                  char c = ciphertext.charAt(i);
                  int cc = (int)c;
                  if(c >= 'A' && c<='Z'){
                      char temp = (char)((cc - s + 26 - 65)%25 + 65);
                      sb.append(temp);
                  }
                  else if(c >= 'a' && c <='z'){
                     char temp = (char)((cc - s + 26 - 97)%25 + 97);
                     sb.append(temp);
                  }
                  else{
                     sb.append(c);
                  }
             }

             System.out.println(" May be : "+ sb.toString());
        }
    }
}

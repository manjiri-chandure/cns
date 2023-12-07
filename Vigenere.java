// Java code to implement Vigenere Cipher
import java.util.*;
public class Vigenere
{
static String generateKey(String str, String key)
{
	int x = str.length();
    //aabdmnesxxxk
    //manjimanjima
    String temp = "";
    StringBuilder st = new StringBuilder(temp);
    int j = 0;
    for(int i=0; i<x; i++){
         st.append(key.charAt(j));
         j++;
         if(j == key.length())
            j=0; 
    }

    temp = st.toString();
    return temp;
}

static String cipherText(String str, String key)
{
	String cipher_text="";

	for (int i = 0; i < str.length(); i++)
	{
		int x = (str.charAt(i) + key.charAt(i)) %26;

		cipher_text+=(char)(x + 'A');
	}
	return cipher_text;
}

static String originalText(String cipher_text, String key)
{
	String orig_text="";

	for (int i = 0 ; i < cipher_text.length() &&
							i < key.length(); i++)
	{
		int x = (cipher_text.charAt(i) -
					key.charAt(i) + 26) %26;
		orig_text+=(char)(x + 'A');
	}
	return orig_text;
}

static String LowerToUpper(String s)
{
	StringBuffer str =new StringBuffer(s);
	for(int i = 0; i < s.length(); i++)
	{
		if(Character.isLowerCase(s.charAt(i)))
		{
			str.setCharAt(i, Character.toUpperCase(s.charAt(i)));
		}
	}
	s = str.toString();
	return s;
}
public static void main(String[] args)
{
	String Str = "manjiri";
	String Keyword = "tom";
    
	
	String str = LowerToUpper(Str);
	String keyword = LowerToUpper(Keyword);

	String key = generateKey(str, keyword);

	String cipher_text = cipherText(str, key);

	System.out.println("Ciphertext : "
		+ cipher_text + "\n");

	System.out.println("Original/Decrypted Text : "
		+ originalText(cipher_text, key));
	}
}

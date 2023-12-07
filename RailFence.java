
import java.util.Arrays;
import java.util.Scanner;
public class RailFence {

	public static String encryptRailFence(String text,
										int key)
	{
		// key = rows , length(text) = columns
		char[][] rail = new char[key][text.length()];

		// filled with spaces from blank ones
		for (int i = 0; i < key; i++)
			Arrays.fill(rail[i], '-');

		boolean dirDown = false;
		int row = 0, col = 0;

		for (int i = 0; i < text.length(); i++) {

			if (row == 0 || row == key - 1)
				dirDown = !dirDown; // change the direction

			rail[row][col++] = text.charAt(i);

			if (dirDown)
				row++;
			else
				row--;
		}
        System.out.println("Rail Fence");
		for(int i=0; i<key; i++){
			  for(int j=0; j<text.length(); j++){
				    System.out.print(rail[i][j] + " ");
			  }
			  System.out.println();
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < key; i++)
			for (int j = 0; j < text.length(); j++)
				if (rail[i][j] != '-')
					result.append(rail[i][j]);

		return result.toString();
	}

	public static String decryptRailFence(String cipher,
										int key)
	{
        char[][] rail = new char[key][cipher.length()];

		for (int i = 0; i < key; i++)
			Arrays.fill(rail[i], '\n'); 

		boolean dirDown = true;

		int row = 0, col = 0;

		// mark the places with '*'
		for (int i = 0; i < cipher.length(); i++) {

			if (row == 0)
				dirDown = true;
			if (row == key - 1)
				dirDown = false;

			rail[row][col++] = '*';

			if (dirDown)
				row++;
			else
				row--;
		}

		int index = 0;
		for (int i = 0; i < key; i++)
			for (int j = 0; j < cipher.length(); j++)
				if (rail[i][j] == '*'
					&& index < cipher.length())
					rail[i][j] = cipher.charAt(index++);

		StringBuilder result = new StringBuilder();

		row = 0;
		col = 0;
		for (int i = 0; i < cipher.length(); i++) {
			if (row == 0)
				dirDown = true;
			if (row == key - 1)
				dirDown = false;

			if (rail[row][col] != '*') // actuall traversing digonally
				result.append(rail[row][col++]);

			if (dirDown)
				row++;
			else
				row--;
		}
		return result.toString();
	}
	public static void main(String[] args)
	{
        Scanner sc = new Scanner(System.in);
		System.out.println("Enter Plaintext: ");
		String pt = sc.nextLine();
		System.out.println("Enter Key Value: ");
		int k = sc.nextInt();

		// Encryption
	    String cipherText =	encryptRailFence(pt, k);
	    System.out.println("Encrypted Message: " + cipherText);

		// Now decryption of the same cipher-text
		System.out.println("Decrypted Message: " + decryptRailFence(cipherText, k));
	
	}
}


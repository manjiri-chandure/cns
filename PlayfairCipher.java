import java.util.Scanner;

public class PlayfairCipher {

    private char[][] keyMatrix;
    private static int flag = 0;
    public PlayfairCipher(String key) {
        initializeKeyMatrix(key);
    }

    private void initializeKeyMatrix(String key) {
        keyMatrix = new char[5][5];
        boolean[] usedChars = new boolean[26];
        int keyIndex = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyIndex < key.length()) {
                    char c = Character.toLowerCase(key.charAt(keyIndex++));
                    if (c == 'j') c = 'i'; 
                    if (!usedChars[c - 'a']) {
                        keyMatrix[i][j] = c;
                        usedChars[c - 'a'] = true;
                    } else {
                        j--; // same character come in key
                    }
                } else {
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (ch != 'j' && !usedChars[ch - 'a']) {
                            keyMatrix[i][j] = ch;
                            usedChars[ch - 'a'] = true;
                            break;
                        }
                    }
                }
            }
        }
    
        if(flag == 0){
            System.out.println("KeyMatrix:");
            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    System.out.print(keyMatrix[i][j]+" ");
                }
                System.out.println();
            }
            flag = 1;
        }
    }

    private String beforePlaintext(String plaintext) {
        
        plaintext = plaintext.replaceAll(" ", "");

        plaintext = plaintext.replaceAll("j", "i");

        // Add an 'X' between repeated letters
        StringBuilder processedText = new StringBuilder();
        char prevChar = ' ';
        for (char c : plaintext.toCharArray()) {
            if (c == prevChar) {
                processedText.append("X");
            }
            processedText.append(c);
            prevChar = c;
        }
        return processedText.toString();
    }

    public String encrypt(String plaintext) {
        plaintext = beforePlaintext(plaintext);
        StringBuilder ciphertext = new StringBuilder();
        int[] pos1 = new int[2];
        int[] pos2 = new int[2];
        System.out.println("Digraphs: ");
        for (int i = 0; i < plaintext.length(); i += 2) {
            char c1 = plaintext.charAt(i);
            char c2 = plaintext.charAt(i + 1);
            System.out.println(c1 + " "+c2);
            findPositions(c1, pos1);
            findPositions(c2, pos2);

            if (pos1[0] == pos2[0]) { // Same row
                ciphertext.append(keyMatrix[pos1[0]][(pos1[1] + 1) % 5]);
                ciphertext.append(keyMatrix[pos2[0]][(pos2[1] + 1) % 5]);
            } else if (pos1[1] == pos2[1]) { // Same column
                ciphertext.append(keyMatrix[(pos1[0] + 1) % 5][pos1[1]]);
                ciphertext.append(keyMatrix[(pos2[0] + 1) % 5][pos2[1]]);
            } else { // Different row and column
                ciphertext.append(keyMatrix[pos1[0]][pos2[1]]);
                ciphertext.append(keyMatrix[pos2[0]][pos1[1]]);
            }
        }

        return ciphertext.toString();
    }

    public String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        int[] pos1 = new int[2];
        int[] pos2 = new int[2];

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char c1 = ciphertext.charAt(i);
            char c2 = ciphertext.charAt(i + 1);
            findPositions(c1, pos1);
            findPositions(c2, pos2);

            if (pos1[0] == pos2[0]) { // Same row
                plaintext.append(keyMatrix[pos1[0]][(pos1[1] -1 + 5) % 5]);
                plaintext.append(keyMatrix[pos2[0]][(pos2[1] + 4) % 5]);
            } else if (pos1[1] == pos2[1]) { // Same column
                plaintext.append(keyMatrix[(pos1[0] + 4) % 5][pos1[1]]);
                plaintext.append(keyMatrix[(pos2[0] + 4) % 5][pos2[1]]);
            } else { // Different row and column
                plaintext.append(keyMatrix[pos1[0]][pos2[1]]);
                plaintext.append(keyMatrix[pos2[0]][pos1[1]]);
            }
        }

        return plaintext.toString();
    }

    private void findPositions(char c, int[] pos) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyMatrix[i][j] == c) {
                    pos[0] = i;
                    pos[1] = j;
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the key: ");
        String key = sc.nextLine();
        PlayfairCipher playfair = new PlayfairCipher(key);

        System.out.print("Enter the plaintext: ");
        String plaintext = sc.nextLine();
        String ciphertext = playfair.encrypt(plaintext);
        System.out.println("Ciphertext: " + ciphertext);

        String decryptedText = playfair.decrypt(ciphertext);
        System.out.println("Decrypted Text: " + decryptedText);

    }
}
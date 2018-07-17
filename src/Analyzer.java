import java.util.Collections;
import java.util.HashMap;



public class Analyzer {
	HashMap<Character, Integer> letter_freq = new HashMap<Character,Integer>();
	HashMap<Character, Integer> digit_freq = new HashMap<Character,Integer>();
    HashMap<Character, Integer> special_freq = new HashMap<Character,Integer>();
	HashMap<String, Integer> word_freq = new HashMap<String,Integer>();
	HashMap<Integer, Integer> word_length = new HashMap<Integer,Integer>();
	int word_count = 0;
	boolean isWord = false;
	int total_char = 0;
	int special_char = 0;
	int digit_char = 0;
	
	public Analyzer() {
		char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		char[] digits = {'0','1','2','3','4','5','6','7','8','9'};
		
		for (int i=0 ; i<letters.length ; i++)
			letter_freq.put(letters[i], 0);
		
		for (int i=0 ; i<digits.length ; i++)
			digit_freq.put(digits[i], 0);
	}
	
	public  void analyze(String text) {
		int start =0 ; int end = 0;
		for (int i=0 ; i<text.length(); i++) {
			char var = text.charAt(i);
			if (Character.isLetter(var)) {
				addChar(letter_freq, var);
				total_char++;	//increment the number of characters
				if (!isWord) {
					isWord = true;
					//grab the starting index of word
					start = i;
				}
			}
			else if (Character.isDigit(var)) {
				addChar(digit_freq, var);
				digit_char++;
				if (isWord)	//if a letter comes between word it's not a word
					isWord = false;
			}
			else {
				// if the character is space and it's not at first index of string, then a word has passed
				if ((var == ' ' || var == '\n' || var == '\r') && i != 0 && isWord) {
					word_count++;
					isWord = false;
					// the ending index of the word
					end = i;
					String word = text.substring(start, end);
					addWord(word);
					addLength(word.length());
				}
				
				addChar(special_freq, var);
				special_char++;
			}
		}
	}
	
	public  void addChar(HashMap<Character,Integer> map, char character) {
		//for upper lower case comparison
		Character c1 = new Character(Character.toLowerCase(character));
		boolean found = false;
		for (Character x : map.keySet()) {
			if (x.equals(c1) && !found) {
				// increment the frequency of the key
				map.put(x, map.get(x)+1);
				found = true;
			}	
		}
		/*for only lower case
		 *if (map.containsKey(character)) {
			// increment the frequency of the key
			map.put(character, map.get(character)+1);
		}*/
		if (!found){
			//if the letter does not exist before add it to list
			map.put(c1, 1);
		}
	}
	
	public  void addWord(String word) {
		if (word_freq.containsKey(word)) {
			// increment the frequency of the key
			word_freq.put(word, word_freq.get(word)+1);
		}
		else {
			//if the word does not exist before add it to list
			word_freq.put(word, 1);
		}
	}
	
	public  void addLength(int len) {
		if (word_length.containsKey(len)) {
			// increment the frequency of the key
			word_length.put(len, word_length.get(len)+1);
		}
		else {
			//if the word does not exist before add it to list
			word_length.put(len, 1);
		}
	}
	
	public void print(HashMap<Character, Integer> map, int total) {
		double rel_freq = 0;
		int freq = 0; 		
		int max_freq = Collections.max(map.values());
		
		for (char x : map.keySet()) {
			rel_freq = 0;
			freq = 0; 		
			
			System.out.print(x+"      ");
			freq = map.get(x);
			rel_freq = freq/(total*1.0);	
			
			for (int j=0 ; j<freq ; j++)
				System.out.print("*");
				
			
			//print spaces to fill empty locations
			for (int j=0 ; j<max_freq-freq ; j++)
				System.out.print(" ");	
			
			System.out.println(" "+freq+" "+rel_freq);
		}
		System.out.println();
	}
	
	public void printFreq(char type) {
		
		if (type=='a') {
			System.out.println("Letter"+"     Freq"+"     Rel.Freq");
			print(letter_freq, total_char);
		}
		else if (type=='b') {
			System.out.println("Special char"+" Freq"+" Rel.Freq");
			if (!special_freq.isEmpty())
				print(special_freq,special_char);
		}
		else if (type=='c'){
			System.out.println("Digits"+" Freq"+" Rel.Freq");
			print(digit_freq, digit_char);
		}
		else if (type=='d'){
			System.out.println("Words"+" Freq"+" Rel.Freq");
			double rel_freq = 0;
			int freq = 0; 
			int max_freq = Collections.max(word_freq.values());
			
			for (String x : word_freq.keySet()) {
				freq = word_freq.get(x);
				
				System.out.print(x+"      ");
				
				for (int j=0 ; j<freq ; j++)
					System.out.print("*");
					
				
				//print spaces to fill empty locations
				for (int j=0 ; j<max_freq-freq ; j++)
					System.out.print(" ");	
				
				rel_freq = freq/(word_count*1.0);

				System.out.println(" "+freq+" "+rel_freq);		
			}
		}
		else if (type=='e'){
			System.out.println("Words length"+" Freq");
			int freq = 0; 
			double rel_freq = 0;
			int max_freq = Collections.max(word_length.values());
			
			for (int x : word_length.keySet()) {
				freq = word_length.get(x);
				System.out.print(x+"      ");
				
				for (int j=0 ; j<freq ; j++)
					System.out.print("*");
					
				
				//print spaces to fill empty locations
				for (int j=0 ; j<max_freq-freq ; j++)
					System.out.print(" ");	
				
				rel_freq = freq/(word_count*1.0);
				
				System.out.println(" "+freq+" "+rel_freq);		
			}
		}
	}
}

package myjava.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Keypad {
	
	private BufferedReader br;
	
	// initializes 
	public Keypad () {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public boolean check_isNumber(String str) {
	    int i;
	    for (i = 0; i < str.length(); i++) {
	        if (str.charAt(i) < '0' || str.charAt(i) > '9') break;
        }
        if (i != str.length()) return false;
	    else return true;
    }
	
	/*
	 *  This function may throw some Exception if the user enters non-integer input.
	 *  You must use try...catch to deal with it.
	 */
	public int getInput() {
        try {
            String input = br.readLine();
            return Integer.parseInt(input);
        } catch (IOException e) {
            Screen screen = new Screen();
            screen.displayMessageLine("I/O error.");
        }
        catch (NumberFormatException e) {
            // catch the exception
        }
        return -1;
    }
}

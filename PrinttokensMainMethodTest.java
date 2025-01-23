package junitTesting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;

class PrinttokensMainMethodTest {

    @Test
    public void testMainWithMultipleArguments() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        String[] args = {"input1.txt", "input2.txt"};
        Printtokens.main(args);
        assertEquals("Error! Please give the token stream\n", output.toString());
    }

    @Test
    public void testMainWithEmptyFile() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        // Create an empty file
        try (PrintWriter writer = new PrintWriter("emptyFile.txt")) {
            // Intentionally left empty
        }
        String[] args = {"emptyFile.txt"};
        Printtokens.main(args);
        assertEquals("", output.toString());
        new File("emptyFile.txt").delete();
    }
    @Test 
    public void testMainWithValidInput() throws IOException { 
    	ByteArrayOutputStream output = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(output)); 
    	String[] args = {"testInput.txt"}; 
    	try (PrintWriter writer = new PrintWriter("testInput.txt")) { 
    		writer.println("xor 42 #z \"test string\" ;This is a comment"); 
    		} 
    	Printtokens.main(args); assertEquals("keyword,\"xor\".\nnumeric,42.\ncharacter,\"z\".\nstring,\"test string\".\ncomment,\";This is a comment\".\n", output.toString()); 
    	new File("testInput.txt").delete(); }


    @Test
    public void testMainWithSpecialSymbols() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        try (PrintWriter writer = new PrintWriter("specialSymbols.txt")) {
            writer.println("ABC( 5 )");
        }
        String[] args = {"specialSymbols.txt"};
        Printtokens.main(args);
        String expectedOutput = 
            "identifier,\"ABC\".\n" +
            "lparen.\n" +
            "numeric,5.\n" +
            "rparen.\n";
        assertEquals(expectedOutput, output.toString());
        new File("specialSymbols.txt").delete();
    }

    @Test
    public void testMainWithComplexTokens() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        try (PrintWriter writer = new PrintWriter("complexTokens.txt")) {
            writer.println("if (x => 10)");
        }
        String[] args = {"complexTokens.txt"};
        Printtokens.main(args);
        String expectedOutput = 
            "keyword,\"if\".\n" +
            "lparen.\n" +
            "identifier,\"x\".\n" +
            "keyword,\"=>\".\n" +
            "numeric,10.\n" +
            "rparen.\n";
        assertEquals(expectedOutput, output.toString());
        new File("complexTokens.txt").delete();
    }

    @Test
    public void testMainWithConsoleInput() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        
        String input = "a ; \"hello\" ABC and 5 #c\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        Printtokens.main(new String[]{});
        
        String expectedOutput = "identifier,\"a\".\n" + 
                                "comment,\"; \"hello\" ABC and 5 #c\".\n";
        
        assertEquals(expectedOutput, output.toString());
    }

    @Test
    public void testMainWithMixedTokenTypes() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        try (PrintWriter writer = new PrintWriter("mixedTokens.txt")) {
            writer.println("lambda (x) => x / 2");
        }
        String[] args = {"mixedTokens.txt"};
        Printtokens.main(args);
        String expectedOutput = 
            "keyword,\"lambda\".\n" +
            "lparen.\n" +
            "identifier,\"x\".\n" +
            "rparen.\n" +
            "keyword,\"=>\".\n" +
            "identifier,\"x\".\n" +
            "slash.\n" +
            "numeric,2.\n";
        assertEquals(expectedOutput, output.toString());
        new File("mixedTokens.txt").delete();
    }

    @Test
    public void testMainWithErrorTokens() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        try (PrintWriter writer = new PrintWriter("errorTokens.txt")) {
            writer.println("\"unclosed string");
        }
        String[] args = {"errorTokens.txt"};
        Printtokens.main(args);
        String expectedOutput = 
            "error,\"\"unclosed string\".\n";
        assertEquals(expectedOutput, output.toString());
        new File("errorTokens.txt").delete();
    }
}
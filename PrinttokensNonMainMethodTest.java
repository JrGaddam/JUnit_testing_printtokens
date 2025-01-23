package junitTesting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import java.io.*;

class PrinttokensNonMainMethodTest {

    static Printtokens ob;
    
    @BeforeAll
    static void initialize() {
        ob = new Printtokens();
    }
    
    @Test
    void get_token_test() {
        testGetToken("or 123 #x \"sample\" ;note", new String[]{"or", "123", "#x", "\"sample\"", ";note"});
        testGetToken("if (a1 => 7) [b2] `test,", new String[]{"if", "(", "a1", "=>", "7", ")", "[", "b2", "]", "`", "test", ","});
        testGetToken("lambda x, y", new String[]{"lambda", "x", ",", "y"});
        testGetToken("xor 42 / 3", new String[]{"xor", "42", "/", "3"});
    }
    
    private void testGetToken(String input, String[] expected) {
        BufferedReader br = new BufferedReader(new StringReader(input));
        for (String exp : expected) {
            assertEquals(exp, ob.get_token(br));
        }
        assertNull(ob.get_token(br));
    }
    
    @Test
    void is_token_end_test() {
        assertTrue(Printtokens.is_token_end(0, -1));
        assertTrue(Printtokens.is_token_end(1, '"'));
        assertFalse(Printtokens.is_token_end(1, 'a'));
        assertTrue(Printtokens.is_token_end(2, '\n'));
        assertFalse(Printtokens.is_token_end(2, 'x'));
        assertTrue(Printtokens.is_token_end(0, ' '));
        assertTrue(Printtokens.is_token_end(0, ';'));
    }
    
    @Test
    void is_keyword_test() {
        assertTrue(Printtokens.is_keyword("if"));
        assertTrue(Printtokens.is_keyword("xor"));
        assertTrue(Printtokens.is_keyword("=>"));
        assertFalse(Printtokens.is_keyword("while"));
        assertFalse(Printtokens.is_keyword("for"));
    }
    
    @Test
    void is_num_constant_test() {
        assertTrue(Printtokens.is_num_constant("4"));
        assertTrue(Printtokens.is_num_constant("123"));
        assertFalse(Printtokens.is_num_constant("3.14"));
        assertFalse(Printtokens.is_num_constant("x2"));
        assertFalse(Printtokens.is_num_constant("123.1"));
    }
    
    @Test
    void is_str_constant_test() {
        assertTrue(Printtokens.is_str_constant("\"Hello\""));         
        assertFalse(Printtokens.is_str_constant("\"Unclosed"));       
        assertFalse(Printtokens.is_str_constant("1"));         
        assertTrue(Printtokens.is_str_constant("\"\""));             
        assertFalse(Printtokens.is_str_constant("\"\0\""));          
        assertFalse(Printtokens.is_str_constant("\"A\0B\""));                
    }

    
    @Test
    void is_char_constant_test() {
        assertTrue(Printtokens.is_char_constant("#a"));
        assertTrue(Printtokens.is_char_constant("#Z"));
        assertFalse(Printtokens.is_char_constant("#1"));
        assertFalse(Printtokens.is_char_constant("#ab"));
    }
    
    @Test
    void is_identifier_test() {
        assertTrue(Printtokens.is_identifier("validVar"));
        assertTrue(Printtokens.is_identifier("x1y2z3"));
        assertFalse(Printtokens.is_identifier("2invalid"));
        assertFalse(Printtokens.is_identifier("no-hyphens"));
    }
    
    @Test
    void is_spec_symbol_test() {
        assertTrue(Printtokens.is_spec_symbol('['));
        assertTrue(Printtokens.is_spec_symbol(']'));
        assertTrue(Printtokens.is_spec_symbol('/'));
        assertFalse(Printtokens.is_spec_symbol('a'));
        assertFalse(Printtokens.is_spec_symbol('2'));
    }
    
    @Test
    void token_type_test() {
        assertEquals(Printtokens.keyword, Printtokens.token_type("lambda"));
        assertEquals(Printtokens.spec_symbol, Printtokens.token_type("["));
        assertEquals(Printtokens.identifier, Printtokens.token_type("myVar"));
        assertEquals(Printtokens.num_constant, Printtokens.token_type("42"));
        assertEquals(Printtokens.str_constant, Printtokens.token_type("\"test\""));
        assertEquals(Printtokens.char_constant, Printtokens.token_type("#x"));
        assertEquals(Printtokens.comment, Printtokens.token_type(";comment"));
        assertEquals(Printtokens.error, Printtokens.token_type("@invalid"));
    }
}
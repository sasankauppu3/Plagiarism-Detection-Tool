package edu.northeastern.cs5500.team111.astgen;

import edu.northeastern.cs5500.team111.comparisonstrategies.LineMatch;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Ast class which is used to convert a Pythonfile to a AST using
 * the ANTLR library
 */

/**
 * Class to represent an Abstract syntax tree
 */
public class Ast {
    private PythonFile file;
    private AstWalk astWalk;

    /**
     * Constructor for AST class which initializes file and astwalk
     * @param file the python file whose ast needs to be generated
     */
    public Ast(PythonFile file)  {
        this.file = file;
        astWalk = new AstWalk(parse());
    }

    /**
     * Reads the file and converts it to String with encoding
     * @param file the input python file
     * @param encoding default encoding scheme
     * @return the encoded string value
     */
    private String readFile(PythonFile file, Charset encoding) {
        byte[] encoded = file.getFileContent();
        return new String(encoded, encoding);
    }

    /**
     * Function to generate an AST from a python source code file
     * @return File_inputContext object
     */
    public AltPython3Parser.File_inputContext parse() {
        String code = readFile(this.file, Charset.forName("UTF-8"));
        AltPython3Lexer lexer = new AltPython3Lexer(new ANTLRInputStream(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AltPython3Parser parser = new AltPython3Parser(tokens);
        return parser.file_input();
    }

    /**
     * getTokenMap parses an AST and returns a token hashmap
     * @return a hashmap of list of tokens
     */
    public Map<Integer, ArrayList<Token>> getTokenMap() {
        return astWalk.getAstTokenMap();
    }

    /**
     * getHashcodesList parses an AST and returns a Arraylist of hashcodes of the nodes
     * @return a list of hashcode integers
     */
    public List<Integer> getHashcodesList() {
        return this.astWalk.getAstHashCodes();
    }

    public Map<Integer, List<LineMatch>> getHashcodesLineMatch() {
        return this.astWalk.getHashCodeLineMatch();
    }

    public AstWalk getAstWalk() {
        return astWalk;
    }

}

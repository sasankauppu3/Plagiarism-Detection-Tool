package edu.northeastern.cs5500.team111.astgen;

import edu.northeastern.cs5500.team111.comparisonstrategies.LineMatch;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import edu.northeastern.cs5500.team111.comparisonstrategies.ComparisionReport.*;
import java.util.*;

/**
 * AstWalk class to simplify iterating in AST
 */
public class AstWalk {

    /**
     * The payload will either be the name of the parser rule, or the token
     * of a leaf in the tree.
     */
    private final Object payload;

    /**
     * All child nodes of this AstWalk.
     */
    private final List<AstWalk> children;

    private List<Token> tokenList = new ArrayList<>();

    public AstWalk(ParseTree tree) {
        this(null, tree);
        this.storeTokenList();
    }

    private AstWalk(AstWalk ast, ParseTree tree) {
        this(ast, tree, new ArrayList<AstWalk>());
    }

    private AstWalk(AstWalk parent, ParseTree tree, List<AstWalk> children) {

        this.payload = getPayload(tree);
        this.children = children;

        if (parent == null) {
            // We're at the root of the AstWalk, traverse down the parse tree to fill
            // this AstWalk with nodes.
            walk(tree, this);
        } else {
            parent.children.add(this);
        }
    }

    /**
     * getter function for payload
     * @return payload
     */
    public Object getPayload() {
        return payload;
    }

    /**
     * getter function for children
     * @return children list
     */
    public List<AstWalk> getChildren() {
        return new ArrayList<>(children);
    }

    /**
     * Determines the payload of this AstWalk: a string in case it's an inner node (which
     * is the name of the parser rule), or a Token in case it is a leaf node.
     * @param tree a parser tree
     * @return tree's payload
     */
    private Object getPayload(ParseTree tree) {
        if (tree.getChildCount() == 0) {
            // A leaf node: return the tree's payload, which is a Token.
            return tree.getPayload();
        } else {
            // The name for parser rule `foo` will be `FooContext`. Strip `Context` and
            // lower case the first character.
            String ruleName = tree.getClass().getSimpleName().replace("Context", "");
            return Character.toLowerCase(ruleName.charAt(0)) + ruleName.substring(1);
        }
    }


    /**
     * Fills this AstWalk based on the parse tree.
     * @param tree a parser tree
     * @param ast current ast walk
     */
    private static void walk(ParseTree tree, AstWalk ast) {

        if (tree.getChildCount() == 0) {
            // We've reached a leaf. We must create a new instance of an AstWalk because
            // the constructor will make sure this new instance is added to its parent's
            // child nodes.
            new AstWalk(ast, tree);
        } else if (tree.getChildCount() == 1) {
            // We've reached an inner node with a single child: we don't include this in
            // our AstWalk.
            walk(tree.getChild(0), ast);
        } else if (tree.getChildCount() > 1) {

            for (int i = 0; i < tree.getChildCount(); i++) {

                AstWalk temp = new AstWalk(ast, tree.getChild(i));

                if (!(temp.payload instanceof Token)) {
                    // Only traverse down if the payload is not a Token.
                    walk(tree.getChild(i), temp);
                }
            }
        }
    }

    
    /**
     * storeTokenList parses an AST and stores all the tokens in a 
     * arraylist for other methods to access them
     */
    public void storeTokenList() {

        AstWalk ast = this;
        List<AstWalk> firstStack = new ArrayList<>();
        firstStack.add(ast);

        List<List<AstWalk>> childListStack = new ArrayList<>();
        childListStack.add(firstStack);

        while (!childListStack.isEmpty()) {

            List<AstWalk> childStack = childListStack.get(childListStack.size() - 1);

            if (childStack.isEmpty()) {
                childListStack.remove(childListStack.size() - 1);
            } else {
                ast = childStack.remove(0);

                if (ast.payload instanceof Token) {
                    Token token = (Token) ast.payload;
                    if(token.getText() != null && !token.getText().trim().isEmpty())
                    {
                    		tokenList.add(token);
                    }
                } 

                if(getSubNodeChildren(ast)!=null){
                	childListStack.add(getSubNodeChildren(ast));
                }
            }
        }
    }
    
    /**
     * getSubNodeChildren gets the children of a subnodes of an ASTWalk
     * @return a list of childrens
     */
    public List<AstWalk> getSubNodeChildren(AstWalk ast)
    {
    		if (!ast.children.isEmpty()) {
            List<AstWalk> children1 = new ArrayList<>();
            children1.addAll(ast.children);
            return children1;
    		}
    		return Collections.emptyList();
    }

    /**
     * getAstTokenMap parses an AST and returns a token hashmap
     * the hashmap key is token type and value is ASTNode
     * @return a hashmap of list of tokens
     */
    public Map<Integer,ArrayList<Token>> getAstTokenMap() {

        HashMap<Integer,ArrayList<Token>> tokMap = new HashMap<>();

        for (Token token : tokenList) {
        		if (tokMap.containsKey(token.getType())){
                tokMap.get(token.getType()).add(token);
            }
            else{
                tokMap.put(token.getType(),new ArrayList<Token>());
                tokMap.get(token.getType()).add(token);
            }
		}

		if(tokMap.containsKey(-1))
    		tokMap.remove(-1);

        return tokMap;
    }

    Map<Integer,List<LineMatch>> hashCodeLineMatch = new HashMap<Integer,List<LineMatch>>();
    /**
     * getAstHashCodes parses an AST and returns a list of HashCodes for the nodes in AST
     * @return a list of integers which are hashcodes for AST
     */
    public List<Integer> getAstHashCodes() {
        ArrayList<Integer> hashCodes = new ArrayList<>();

        for (Token token : tokenList) {
            if(token.getType()==36) {
                hashCodes.add(token.getText().hashCode());

                LineMatch temp = new LineMatch();
                temp.setLine(token.getLine());
                temp.setStartIndex(token.getCharPositionInLine());
                temp.setStopIndex(token.getCharPositionInLine() + token.getText().length());

                if (hashCodeLineMatch.containsKey(token.getText().hashCode())) {
                    hashCodeLineMatch.get(token.getText().hashCode()).add(temp);
                } else {
                    hashCodeLineMatch.put(token.getText().hashCode(), new ArrayList<LineMatch>());
                    hashCodeLineMatch.get(token.getText().hashCode()).add(temp);
                }
            }
        }

        return hashCodes;
    }

    public Map<Integer,List<LineMatch>> getHashCodeLineMatch() {
        return hashCodeLineMatch;
    }
}
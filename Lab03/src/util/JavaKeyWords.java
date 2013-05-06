package util;

import java.util.HashMap;
import java.util.Map;

public class JavaKeyWords {
	
	private static JavaKeyWords javaKeyWords; 
	
	public static String[] javaKeys = { 
			"abstract",	   "continue",    "for",          "new",          "switch",
		    "assert",      "default",     "if",            "package",      "sychronized",
		    "boolean",     "do",          "goto",          "private",      "this",
		    "break",       "double",      "implements",    "protected",    "throw",
		    "byte",        "else",        "import",        "public",       "throws",
		    "case",        "enum",        "instanceof",    "return",       "transient",
		    "catch",       "extends",     "int",           "short",        "try",
		    "char",        "final",       "interface",     "static",       "void", 
		    "class",       "finally",     "long",          "strictfp",     "volatile",
		    "const",       "float",       "native",        "super",        "while" };
	
	private  Map<String, Integer> keyWords;
	
	private JavaKeyWords() {
		keyWords = new HashMap<String, Integer>();
		for(int i=0; i<javaKeys.length; i++) {
			keyWords.put(javaKeys[i], 0);
		}
	}
	
	public synchronized static JavaKeyWords getInstance() {
		if(javaKeyWords == null) {
			javaKeyWords = new JavaKeyWords();
		}
		return javaKeyWords;
	}
	
	public Map<String, Integer> getMapKeyWords() {
		return keyWords;
	}
	
	public String[] getJavaKeyWords() {
		return javaKeys;
	}
	
	/**
	 * Aumenta a quantidade de uma Chava em 1; 
	 * 
	 * @param keyWord a palavra reservada de java.
	 */
	public void incrementaKeyWord(String keyWord) {
		Integer novoValor = this.keyWords.get(keyWord) + 1;
		this.keyWords.put(keyWord, novoValor);
	}
	
	public static void reset() {
		javaKeyWords = new JavaKeyWords();
	}
}

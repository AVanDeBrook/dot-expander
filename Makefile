.PHONY: compile
.PHONY: run
.PHONY: clean

compile:
	javac source/ConfigBuilder/ConfigBuilder.java
	javac source/DotFileEnumerator/DotFileEnumerator.java
	javac source/DotFileWriter/DotFileWriter.java
	javac source/DotLangParser/DotLangParser.java
	javac source/DotLangParser/DotLangParserGraphType.java
	javac source/DotLangParser/DotLangParserObj.java
	javac source/DotLangParser/DotLangParserObjNode.java
	javac source/Manager.java

run:
	java source.Manager test

clean:
	del source\ConfigBuilder\*.class
	del source\DotFileEnumerator\*.class
	del source\DotFileWriter\*.class
	del source\DotLangParser\*.class
	del source\*.class

.PHONY: compile
.PHONY: run
.PHONY: clean

compile:
	javac source/DotFileEnumerator/DotFileEnumerator.java
	javac source/DotLangParser/DotLangParser.java
	javac source/DotLangParser/DotLangParserObj.java
	javac source/Manager.java

run:
	java source.Manager test

clean:
	del source\*.class
	del source\DotFileEnumerator\*.class
	del source\DotLangParser\*.class

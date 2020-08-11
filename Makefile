.PHONY: compile
.PHONY: run
.PHONY: clean

compile:
	javac source/Configuration/*.java
	javac source/DotFileEnumerator/*.java
	javac source/DotFileWriter/*.java
	javac source/DotGraphParser/*.java
	javac source/Manager.java

run:
	java source.Manager test

clean:
	del source\Configuration\*.class
	del source\DotFileEnumerator\*.class
	del source\DotFileWriter\*.class
	del source\DotGraphParser\*.class
	del source\*.class

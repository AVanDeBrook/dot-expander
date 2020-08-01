.PHONY: compile
.PHONY: run
.PHONY: clean

compile:
	javac source/Configuration/Configuration.java
	javac source/DotFileEnumerator/DotFileEnumerator.java
	javac source/DotFileWriter/DotFileWriter.java
	javac source/DotGraphParser/DotGraphParser.java
	javac source/DotGraphParser/DotGraphType.java
	javac source/DotGraphParser/GraphContainer.java
	javac source/DotGraphParser/GraphNodeContainer.java
	javac source/Manager.java

run:
	java source.Manager test

clean:
	del source\Configuration\*.class
	del source\DotFileEnumerator\*.class
	del source\DotFileWriter\*.class
	del source\DotGraphParser\*.class
	del source\*.class

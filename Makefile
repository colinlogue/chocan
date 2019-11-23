CPATHS = ./out/production:./lib/target/sqlite-jdbc-3.28.jar

all: src/*.java
	javac src/*.java -classpath $(CPATHS) -d out/production

CPATHS = ./out/production:./lib/sqlite-jdbc-3.27.2.1.jar

all: src/*.java
	javac src/*.java -d out/production

maven:
	cd lib; mvn package

run: all
	java -cp $(CPATHS) DataSource

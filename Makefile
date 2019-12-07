CPATHS = ./out/production/chocan:./lib/sqlite-jdbc-3.27.2.1.jar
TESTPATHS = ./lib/hamcrest-core-1.3.jar:./lib/junit-4.13-rc-2.jar:./out/test/chocan

all: src/*.java
	javac src/*.java -d out/production/chocan

run: all
	java -cp $(CPATHS) Simulation

csv:
	cd db; python3 db.py

testc: tests/*.java
	javac -cp $(CPATHS):$(TESTPATHS) tests/*.java -d out/test/chocan

test: all testc
	java -cp $(CPATHS):$(TESTPATHS) org.junit.runner.JUnitCore AllTests
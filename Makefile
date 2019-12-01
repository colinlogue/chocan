CPATHS = ./out/production:./lib/sqlite-jdbc-3.27.2.1.jar

all: src/*.java
	javac src/*.java -d out/production

run: all
	java -cp $(CPATHS) Simulation

csv:
	cd db; python3 db.py

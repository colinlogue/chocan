# ChocAn

## Running the simulation
To access the ChocAn simulation, run `make run` from the `chocan` directory.

If not on a system that supports makefiles, first compile all of the source files:

```bash
javac src/*.java -d out/production
```

Then you can run the program from the `chocan` directory with the following command:

```bash
java -cp ./out/production:./lib/sqlite-jdbc-3.27.2.1.jar Simulation
```

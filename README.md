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

## Test data

### Address
AddressID | Street          | City           | State | ZIP
----------|-----------------|----------------|-------|------
1         | 123 Fake St.    | Springfield    | OR    | 97243
2         | 456 Lols Lane   | Springfield    | MO    | 01345
3         | Test St.        | Test Town      | WA    | 11111
4         | Apple St.       | Portland       | OR    | 91234
5         | Orange Ave.     | Portland       | OR    | 91234
6         | 123 Sing St.    | Hendersonville | TN    | 12345
7         | 789 Song St.    | Hendersonville | TN    | 12344
8         | 155 Computer St | Portland       | OR    | 97206
9         | 104 Baker Rd    | Portland       | OR    | 97206
10        | 3045 SE Sunset  | Springfield    | MO    | 65804
11        | 123 Mulberry St | Paris          | OR    | 90890
12        | 304 Avenue Q    | Portland       | TX    | 90809

### Members
MemberID | Name             | IsActive | IsHidden | AddressID
---------|------------------|----------|----------|----------
100001   | Ada Lovelace     | 1        | 0        | 1
100002   | Kevin Smith      | 0        | 1        | 4
100003   | Taylor Swift     | 0        | 0        | 7
100004   | Chris Gilmore    | 0        | 0        | 8
100005   | Katie Smith      | 1        | 0        | 9
100006   | Colin Logue      | 1        | 0        | 12

### Providers
ProviderID | Name              | IsHidden | AddressID
-----------|-------------------|----------|----------
900001     | Choco No More     | 0        | 1
900003     | Kevin Smith       | 0        | 5
900004     | Barbara Streisand | 0        | 10
900005     | Theodore Geisel   | 0        | 11

### Service
ServiceCode | Label            | Fee
------------|------------------|------
102010      | Chocolate Detox  | 1425
102050      | Spirit Cleansing | 3250
103000      | Aura Debugging   | 4000
105055      | De-Nibbing       | 1688

### Sessions
SessionID | ProviderID | MemberID | ServiceCode | Comments
----------|------------|----------|-------------|----------
1         | 900001     | 100001   | 102010      | Ada has admitted she is powerless over chocolate
2         | 900001     | 100001   | 102010      | Ada is making progress.
3         | 900001     | 100001   | 102010      | Session started late today, ran 15 inutes late
4         | 900001     | 100001   | 102050      | Spirit Cleansing seems to have lifted Adas spirits
5         | 900001     | 100001   | 102010      | Trust the process

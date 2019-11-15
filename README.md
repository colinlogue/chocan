This is a repository for the CS 300 group project.

## Directory layout
```
  chocan/
  |-- db/
  |   | - chocan.sqlite3
  |   | - schema.sql
  |-- reports/
  |   |-- member_reports/
  |   |   | - <mem_id>_<timestamp>.txt
  |   |-- provider_reports/
  |   |   | - <prov_id>_<timestamp>.txt
  |-- src/
  |   | - ClassOne.java
  |   | - ClassTwo.java etc.
  |-- tests/
  |   | - ClassOneTests.java
  |   | - ClassTwoTests.java etc.
  | - README.md
```
The `src` directory is where all the `.java` files go. If it gets too
cluttered we can add some subfolders.
The `tests` directory should contain the unit tests for each class.

If you're using IntelliJ, mark the `src` as Sources Root by right
clicking and selecting Mark Directory As > Sources Root, and the same
with the `tests` folder as Test Sources Root.

Guidelines for Collaboration
-git pull before you begin coding for a day to ensure you're seeing the current code
-other ideas to ensure smooth integration<3

#Basic Steps for pull/pushing changes:
1. pull from git
2. make sure you are on your own branch
3. merge changes from the master branch
4. do code stuff on your local branch, commit it to your branch
5. pull again after you commit
6. switch to the master branch, merge your changes with master branch
7. push
8. success
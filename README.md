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

## Guidelines for Collaboration
- git pull before you begin coding for a day to ensure you're seeing the current code
- other ideas to ensure smooth integration<3

## Basic Steps for pull/pushing changes:
1. pull from git
2. make sure you are on your own branch
3. merge changes from the master branch
4. do code stuff on your local branch, commit it to your branch
5. pull again after you commit
6. switch to the master branch, merge your changes with master branch
7. push
8. success
9. <3

## Guidelines for working on your sections
- DON'T change the name or type signature (inputs/outputs) for any of
  the public functions.
- IF YOU DO NEED TO CHANGE IT, make sure to tell everyone so we can
  refactor any code portions that have been using them.
- Do whatever the heck you want with the private methods in your class.
- Do comment liberally, even in the private methods, just in case
  someone else needs to figure out what you've done.
- Only push to the master branch pieces that you are satisfied are
  working correctly.

## Accessing data from the database
Most methods are going to require looking up stored data or storing
new data. There are three patterns for this: retrieving information
about something that already exists, modifying information for
something that already exists, or creating a new thing.

### Retrieving existing information
```java
private void do_something(int thing_id) {
    // 1) attempt to load data
    try {
        ThingData thing = ThingData.retrieve(thing_id);
    }
    catch (DatabaseException e) {
       // handle case where loading fails
    }
    
    // 2) do something with that data
    String value = thing.value;    // data members are public
    do_the_thing(value);           // use the data for some purpose
    
    // note that this pattern does NOT call write, so nothing in
    // the database is changed
};
```

### Modifying existing information
```java
private void update_record(int thing_id) {
    // 1) attempt to load data
    try {
        ThingData thing = ThingData.retrieve(thing_id);
    }
    catch (DatabaseException e) {
        // handle case where loading fails
    }
    
    // 2) change some value in that data
    thing.value = get_value();     // set data directly
    
    // 3) attempt to save the changes
    try {
        thing.write();
    }
    catch (DatabaseException e) {
        // handle case where saving fails
    }
};
```

### Creating new information
```java
private void create_new_thing() {
    // 1) create a new instance of the data class
    ThingData thing = new ThingData();
    
    // 2) set the data member values directly
    thing.value = get_value();
    thing.another_value = get_another_value();
    
    // 3) attempy to save (creates new DB row)
    try {
        thing.write();
    }
    catch (DatabaseException e) {
        // handle case where saving fails
    }
}   
```

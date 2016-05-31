# BabySitter Kata


Obtained from [this Gist](https://gist.github.com/jameskbride/5482722)

Kata Info
---------
This kata simulates a babysitter working and getting paid for one night.  The rules are pretty straight forward:

The babysitter:
- starts no earlier than 5:00PM
- leaves no later than 4:00AM
- gets paid $12/hour from start-time to bedtime
- gets paid $8/hour from bedtime to midnight
- gets paid $16/hour from midnight to end of job
- gets paid for full hours (no fractional hours)

Feature: 
As a babysitter, In order to get paid for 1 night of work, I want to calculate my nightly charge

Project Info
------------
|                   |                             |
|-------------------|:---------------------------:|
|  Language         |                     Java 8  |
|  Build            |         Gradle 2.4 Wrapper  |
|  Test Framework   |                    JUnit 4  |
|  Static Analysis  |  FindBugs, PMD, Checkstyle  |  
|  Test Coverage    |                     Jacoco  |

To build: 

    $ ./gradlew build

Jacoco test coverage report

    ./build/reports/jacoco/test/html/index.html

Generate Eclipse project files

    $ ./gradlew eclipse

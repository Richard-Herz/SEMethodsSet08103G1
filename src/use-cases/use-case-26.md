# USE CASE: 26 Produce a Report on the number of people who speak the following the following languages from greatest number to smallest, including the percentage of the world population

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *Population Information Worker* I want to produce a report on the population of people, people living in cities, and people not living in cities in each country so that *I can support population reporting of the country.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the role.  Database contains language data.

### Success End Condition

A report is available to give to population analyst.

### Failed End Condition

No report is produced.

### Primary Actor

Population Information Worker.

### Trigger

A request for population information is sent to Population Information Worker.

## MAIN SUCCESS SCENARIO

1. Population analyst request information on the number of people who speak the following the following languages from greatest number to smallest, including the percentage of the world population.
2. Population Information Worker captures name of the language to get population information for.
3. Population Information Worker extracts  information for population of the language and then calculates the percentage of people who speak it.
4. Population Information Worker provides report to population analyst.

## EXTENSIONS

3. **Data does not exist**:
    1. Population Information Worker informs population analyst data doesn't exist..

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
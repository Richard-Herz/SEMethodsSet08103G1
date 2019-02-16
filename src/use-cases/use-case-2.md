# USE CASE: 2 Produce a Report on the population of people, people living in cities, and people not living in cities in each region.

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *Population Information Worker* I want to produce a report on the population of living in cities and people who don't live in a city in a region  so that *I can support population reporting of the region.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the role.  Database contains city, country and region data.

### Success End Condition

A report is available to give to population analyst.

### Failed End Condition

No report is produced.

### Primary Actor

Population Information Worker.

### Trigger

A request for population information is sent to Population Information Worker.

## MAIN SUCCESS SCENARIO

1. Population analyst request information on population of people living in cities and those who don't in a region.
2. HR advisor captures name of the region to get city information for.
3. HR advisor extracts  information for population of the region and then calculates the people who don't live in cities.
4. HR advisor provides report to population analyst.

## EXTENSIONS

3. **Data does not exist**:
    1. HR advisor informs population analyst data doesn't exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
# USE CASE: 4 Produce a Report on the cities in a district organised by largest population to smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *Population Information Worker* I want to produce a report on the cities in a district organised by largest population to smallest * so that *I can support population reporting of the district.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the role.  Database contains current employee salary data.

### Success End Condition

A report is available for HR to provide to population analyst.

### Failed End Condition

No report is produced.

### Primary Actor

Population Information Worker.

### Trigger

A request for population information is sent to Population Information Worker.

## MAIN SUCCESS SCENARIO

1. Population analyst request information on city population in a district from largest to smallest.
2. HR advisor captures name of the district to get city information for.
3. HR advisor extracts  information for population of the district.
4. HR advisor provides report to population analyst.

## EXTENSIONS

3. **Data does not exist**:
    1. HR advisor informs population analyst data doesn't exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
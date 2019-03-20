# USE CASE: 6 Produce a Report on the cities in a region organised by largest population to smallest 
## CHARACTERISTIC INFORMATION

### Goal in Context

As an *Population Information Worker* I want to produce a report on the population of cities in a region organised from largest to smallest * so that *I can support population reporting of the region.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the role. Database contains data for all the cities and countries and regions.

### Success End Condition

A report is available to give to population analyst.

### Failed End Condition

No report is produced.

### Primary Actor

Population Information Worker.

### Trigger

A request for population information is sent to Population Information Worker.

## MAIN SUCCESS SCENARIO

1. population analyst request city information in a region.
2. HR advisor captures name of the role to get salary information for.
3. HR advisor extracts current salary information of all employees of the given role.
4. HR advisor provides report to finance.

## EXTENSIONS

3. **Data does not exist**:
    1. HR advisor informs population analyst data doesn't exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
# USE CASE: 7 Produce a Report on the top N populated capital cities in a region where N is provided by the user

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want to produce a report on the cities in a district organised by largest population to smallest * so that *I can support population reporting of the district.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the role.  Database contains capital city data.

### Success End Condition

A report is available for HR to provide to population analyst.

### Failed End Condition

No report is produced.

### Primary Actor

HR Advisor.

### Trigger

A request for finance information is sent to HR.

## MAIN SUCCESS SCENARIO

1. Population analyst request information on the top N populated capital cities in a region where N is provided by the user.
2. HR advisor captures name of the region to get capital city information for.
3. HR advisor extracts  information for population of the capital cities.
4. HR advisor provides report to population analyst.

## EXTENSIONS

3. **Role does not exist**:
    1. HR advisor informs finance no role exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
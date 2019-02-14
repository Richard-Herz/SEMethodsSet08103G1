# USE CASE: 5 Produce a Report on the cities in a country organised by largest population to smallest
## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want to produce a report on the cities in a country organised by largest population to smallest so that *I can support population reporting of the country.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the role. Database contains data for all the cities and countries.

### Success End Condition

A report is available to give to someone who wants the data for all the cities in a country from biggest to smallest.

### Failed End Condition

No report is produced.

### Primary Actor

HR Advisor.

### Trigger

A request for city information is sent to HR.

## MAIN SUCCESS SCENARIO

1. population analyst requests information for city population in a country from largest to smallest.
2. HR advisor captures name of the role to get salary information for.
3. HR advisor extracts current salary information of all employees of the given role.
4. HR advisor provides report to finance.

## EXTENSIONS

3. **Role does not exist**:
    1. HR advisor informs finance no role exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
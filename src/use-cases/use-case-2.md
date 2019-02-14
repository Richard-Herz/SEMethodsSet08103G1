# USE CASE: 2 Produce a Report on the population of people, people living in cities, and people not living in cities in each region.

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want to produce a report on the population of living in cities and people who don't live in a city in a region  so that *I can support population reporting of the region.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the role.  Database contains city, country and region data.

### Success End Condition

A report is available to give to someone who wants the data provide to finance.

### Failed End Condition

No report is produced.

### Primary Actor

HR Advisor.

### Trigger

A request for finance information is sent to HR.

## MAIN SUCCESS SCENARIO

1. Population analyst request population of people living in cities and those that don't.
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
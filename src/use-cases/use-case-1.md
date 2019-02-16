# USE CASE: 1 Produce a Report on the population of people, people living in cities, and people not living in cities in each country

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want to produce a report on the population of people, people living in cities, and people not living in cities in each country so that *I can support population reporting of the country.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the role.  Database contains city and country population data.

### Success End Condition

A report is available to give to someone who wants the data for people living in cities and those who don't in a country.

### Failed End Condition

No report is produced.

### Primary Actor

HR Advisor.

### Trigger

A request for population information is sent to HR.

## MAIN SUCCESS SCENARIO

1. Population analyst request information on population of people living in cities and those who don't in a country.
2. HR advisor captures name of the country to get city information for.
3. HR advisor extracts  information for population of the country and then calculates the people who don't live in cities.
4. HR advisor provides report to population analyst.

## EXTENSIONS

3. **Role does not exist**:
    1. HR advisor informs finance no role exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
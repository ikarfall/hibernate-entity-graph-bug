# hibernate-entity-graph-bug
small repo to reproduce hibernate entity graph bug

This is a repo to reproduce an @EntityGraph bug of hibernate 4.5.20
run the test to see warning unexpected warning 
"Entity graph specified is not applicable to the entity [entity type]. Ignored."
and extra sql queries that retreaves relations that are nit in the path and should be lazy.
https://hibernate.atlassian.net/browse/HHH-14212

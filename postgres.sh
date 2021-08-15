#!/bin/bash


docker run --name exploring-mars-postgres --rm -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=exploring-mars -p 5432:5432 -v /opt/db/exploring-mars:/var/lib/postgresql/data -d postgres

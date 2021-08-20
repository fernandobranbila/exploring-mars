CREATE TABLE rover
(
    ID            bigserial PRIMARY KEY,
    PLATEAU_ID    bigserial,
    NAME          VARCHAR(255),
    X_POSITION    smallint,
    Y_POSITION    smallint,
    FACING_SIDE   char(1)
);

SELECT setval('rover_id_seq', (select max(id) from rover), true);
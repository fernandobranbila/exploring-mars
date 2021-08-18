CREATE TABLE plateau
(
    ID            bigserial PRIMARY KEY,
    NAME          VARCHAR(255),
    PLANET_ID     bigserial,
    X_SIZE        smallint,
    Y_SIZE        smallint
);

SELECT setval('plateau_id_seq', (select max(id) from plateau), true);
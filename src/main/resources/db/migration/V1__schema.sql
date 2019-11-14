CREATE TABLE WORK_SITE(
                        ID                   UUID PRIMARY KEY,
                        COMMENT               VARCHAR(200),
                        CREATED_AT            timestamp,
                        UPDATED_AT           timestamp,
                        CREATED_BY           VARCHAR(200),
                        UPDATED_BY           VARCHAR(200)

);

CREATE TABLE TIMELINE
(
  ID             UUID PRIMARY KEY,
  FK_WORK_SITE   UUID,
  DATE           TIMESTAMP,
  TITLE          varchar(250),
  COMMENT        varchar(600),
  STATE          varchar(250),
  TYPE           varchar(250),
  CORRELATION_ID UUID,
  CREATED_BY     VARCHAR(200)
);

CREATE TABLE TIMELINE_EVENT_METADATA
(
  ID          UUID PRIMARY KEY,
  FK_TIMELINE UUID NOT NULL,
  KEY         varchar(100),
  VALUE       varchar(2000)
);

INSERT INTO TIMELINE(ID, FK_WORK_SITE, DATE, COMMENT, TITLE, TYPE, STATE, CORRELATION_ID, CREATED_BY)
VALUES ('f5607d38-8fc1-43ef-b44e-34967083c80a', 'e5607d38-8fc1-43ef-b44e-34967083c80a',
        to_timestamp('2018-05-13 11:34:24', 'yyyy-dd-MM HH24:MI:ss'), 'Creation', 'WORK_SITE_CREATION',
        'Work site created', 'TBP', 'a5607d38-8fc1-43ef-b44e-34967083c80a', 'test');

INSERT INTO TIMELINE_EVENT_METADATA(ID, KEY, VALUE, FK_TIMELINE)
VALUES ('f5607d38-8fc1-43ef-b44e-34967083c80b', 'creation.product.date',
        to_timestamp('2019-02-07 14:00:00', 'yyyy-dd-MM HH24:MI:ss'), 'f5607d38-8fc1-43ef-b44e-34967083c80a');

INSERT INTO TIMELINE_EVENT_METADATA(ID, KEY, VALUE, FK_TIMELINE)
VALUES ('f5607d38-8fc1-43ef-b44e-34967083c80c', 'creation.start.date',
        to_timestamp('2019-02-07 14:00:00', 'yyyy-dd-MM HH24:MI:ss'), 'f5607d38-8fc1-43ef-b44e-34967083c80a');

INSERT INTO WORK_SITE (ID, COMMENT, CREATED_AT, UPDATED_AT, CREATED_BY, UPDATED_BY)
VALUES ('e5607d38-8fc1-43ef-b44e-34967083c80a',
        'this is a comment',
        to_timestamp('2019-02-07 14:00:00', 'yyyy-dd-MM HH24:MI:ss'),
        to_timestamp('2019-02-07 14:00:00', 'yyyy-dd-MM HH24:MI:ss'),
        'me',
        'me');

INSERT INTO WORK_SITE (ID, COMMENT, CREATED_AT, UPDATED_AT, CREATED_BY, UPDATED_BY)
VALUES ('b5607d38-8fc1-43ef-b44e-34967083c80a',
        'this is another comment',
        to_timestamp('2019-02-07 14:00:00', 'yyyy-dd-MM HH24:MI:ss'),
        to_timestamp('2019-02-07 14:00:00', 'yyyy-dd-MM HH24:MI:ss'),
        'me',
        'you');


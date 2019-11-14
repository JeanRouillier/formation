INSERT INTO TIMELINE(ID, FK_WORK_SITE, DATE, COMMENT, TITLE, TYPE, STATE, CORRELATION_ID, CREATED_BY)
VALUES ('f5607d38-8fc1-43ef-b44e-34967083c80a', 'e5607d38-8fc1-43ef-b44e-34967083c80a',
        PARSEDATETIME('2018-05-13 11:34:24', 'yyyy-dd-MM hh:mm:ss'), 'Creation', 'WORK_SITE_CREATION',
        'Work site created', 'TBP', 'a5607d38-8fc1-43ef-b44e-34967083c80a', 'test');

INSERT INTO TIMELINE_EVENT_METADATA(ID, KEY, VALUE, FK_TIMELINE)
VALUES ('f5607d38-8fc1-43ef-b44e-34967083c80b', 'creation.product.date',
        PARSEDATETIME('2019-02-07 14:00:00', 'yyyy-dd-MM hh:mm:ss'), 'f5607d38-8fc1-43ef-b44e-34967083c80a');

INSERT INTO TIMELINE_EVENT_METADATA(ID, KEY, VALUE, FK_TIMELINE)
VALUES ('f5607d38-8fc1-43ef-b44e-34967083c80c', 'creation.start.date',
        PARSEDATETIME('2019-02-07 14:00:00', 'yyyy-dd-MM hh:mm:ss'), 'f5607d38-8fc1-43ef-b44e-34967083c80a');

INSERT INTO WORK_SITE (ID, COMMENT, CREATED_AT, UPDATED_AT, CREATED_BY, UPDATED_BY)
VALUES ('e5607d38-8fc1-43ef-b44e-34967083c80a',
        'this is a comment',
        PARSEDATETIME('2019-02-07 14:00:00', 'yyyy-dd-MM hh:mm:ss'),
        PARSEDATETIME('2019-02-07 14:00:00', 'yyyy-dd-MM hh:mm:ss'),
        'me',
        'me');

INSERT INTO WORK_SITE (ID, COMMENT, CREATED_AT, UPDATED_AT, CREATED_BY, UPDATED_BY)
VALUES ('b5607d38-8fc1-43ef-b44e-34967083c80a',
        'this is another comment',
        PARSEDATETIME('2019-02-07 14:00:00', 'yyyy-dd-MM hh:mm:ss'),
        PARSEDATETIME('2019-02-07 14:00:00', 'yyyy-dd-MM hh:mm:ss'),
        'me',
        'you');


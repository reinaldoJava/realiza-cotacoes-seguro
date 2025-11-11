CREATE STREAM topic-external WITH (KAFKA_TOPIC='topic-external', VALUE_FORMAT='JSON') AS
  SELECT
    *,
    ROW_NUMBER() OVER (ORDER BY ROWTIME) AS insurance_policy_id
  FROM
    topic-external-original;
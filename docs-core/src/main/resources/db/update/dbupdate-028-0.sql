alter table T_DOCUMENT add column DOC_SKILLS_C integer;
alter table T_DOCUMENT add column DOC_EXPERIENCE_C integer;
alter table T_DOCUMENT add column DOC_GPA_C integer;
alter table T_DOCUMENT add column DOC_SCORES_C integer;
update T_CONFIG set CFG_VALUE_C = '28' where CFG_ID_C = 'DB_VERSION';
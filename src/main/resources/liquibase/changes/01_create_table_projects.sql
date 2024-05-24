create table projects (
    id bigint generated always as identity,
    name varchar,
    parent_id bigint
)
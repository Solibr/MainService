create table tasks (
    id bigint generated always as identity,
    name varchar,
    project_id bigint,
    task_type varchar,
    task_status varchar,
    create_time TIMESTAMP WITH TIME ZONE,
    status_update_time TIMESTAMP WITH TIME ZONE,
    text TEXT
)
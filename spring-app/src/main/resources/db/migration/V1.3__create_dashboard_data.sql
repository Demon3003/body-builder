insert into dashboard_type(
    id,
    name,
    description
)
values
    (1, 'Top bar', '...'),
    (2, 'Composite', 'Aggregates several simple widgets');

insert into dashboard (
    id,
	name,
	type_id,
	display_name,
	description,
	parent_id
)
 values
    (1, 'Test', 1, 'Test', '...', null),
    (2, 'Test2', 2, 'Test2', '...', 1);
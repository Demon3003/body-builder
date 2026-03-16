insert into widget_type (
    id,
    name,
    description
    )
values
    (1, 'Table', '...'),
    (2, 'Parameterized', '...');

insert into widget (
    id ,
    name,
    type_id,
    data,
    parent_id
    )
values
    (1, 'Test', 1, '{"color": "white", "size-x": 200, "size-y": 100}', 2),
    (2, 'User Info', 2, '{"FirstName": 1, "Email": 1, "Age": 2}', 2);

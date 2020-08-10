create database university default character set utf8;

create table students(
    Id_Students int Auto_increment, Primary key(Id_Students),
    name varchar (55),
    address varchar (55)
);

create table courses(
    Id_Courses int Auto_increment, Primary key(Id_Courses),
    name varchar (55)
);

create table enrollments(
    fk_students int,
    fk_courses  int,
    foreign key (fk_students) references students(Id_Students),
    foreign key (fk_courses) references courses(Id_Courses)
);



insert into students (name, address)
    values
    ("Ivan Katschuk", "Himmelgasse 34"),
    ("Karl Seitz", "Graben 1"),
    ("Herman Hinterstuber", "Holzjöchel 2"),
    ("Thomas Brezina", "Holzwiesn 29"),
    ("Tiberius Kirk", "Entergasse 23");


insert into courses (name)
    values
    ("Haarentfernung"),
    ("Zauberkunde"),
    ("Origami"),
    ("Star Trek Theory"),
    ("Yoga u. Pilates"),
    ("Iglu Tiefbau Lehrgang");

insert into enrollments (fk_courses, fk_students)
    values
    (1,1),
    (2,2),
    (3,3),
    (3,4),
    (3,4),
    (5,1),
    (5,2),
    (6,1);
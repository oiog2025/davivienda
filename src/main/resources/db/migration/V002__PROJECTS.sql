CREATE TABLE projects (
                          id  BIGINT GENERATED BY DEFAULT AS IDENTITY
                              PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    start_inicio DATE,
    end_date DATE,
    status VARCHAR(50) NOT NULL
);

INSERT INTO projects (name, description, start_inicio, end_date, status) VALUES
('Project Alpha', 'First dummy project', '2024-01-01', '2024-06-01', 'EN_PROGRESO'),
('Project Beta', 'Second dummy project', '2024-02-01', '2024-07-01', 'COMPLETADO'),
('Project Gamma', 'Third dummy project', '2024-03-01', '2024-08-01', 'PLANIFICADO'),
('Project Delta', 'Fourth dummy project', '2024-04-01', '2024-09-01', 'CANCELADO'),
('Project Epsilon', 'Fifth dummy project', '2024-05-01', '2024-10-01', 'CANCELADO');
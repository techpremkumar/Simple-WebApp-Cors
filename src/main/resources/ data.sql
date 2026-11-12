INSERT INTO product
(name, description, brand, price, category,
 release_date, available, quantity)

SELECT
    'Dell Inspiron',
    'Laptop for software development',
    'Dell',
    60000.00,
    'Electronics',
    '2026-01-15',
    1,
    10
    WHERE NOT EXISTS (
    SELECT 1 FROM product WHERE name = 'Dell Inspiron'
);

INSERT INTO product
(name, description, brand, price, category,
 release_date, available, quantity)

SELECT
    'iPhone 17',
    'Apple smartphone',
    'Apple',
    80000.00,
    'Electronics',
    '2026-01-20',
    1,
    15
    WHERE NOT EXISTS (
    SELECT 1 FROM product WHERE name = 'iPhone 17'
);

INSERT INTO product
(name, description, brand, price, category,
 release_date, available, quantity)

SELECT
    'Galaxy S25',
    'Samsung smartphone',
    'Samsung',
    75000.00,
    'Electronics',
    '2026-02-10',
    1,
    20
    WHERE NOT EXISTS (
    SELECT 1 FROM product WHERE name = 'Galaxy S25'
);
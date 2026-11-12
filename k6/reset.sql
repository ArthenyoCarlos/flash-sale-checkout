DELETE FROM pedido;

TRUNCATE TABLE pedido RESTART IDENTITY;

SELECT
    COUNT(v.id)              AS pedidos,
    p.estoque                AS estoque,
    COUNT(v.id) + p.estoque  AS soma
FROM produto p
LEFT JOIN pedido v ON v.produto_id = p.id
WHERE p.id = 1
GROUP BY p.id, p.estoque LIMIT 100

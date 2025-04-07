# Transaction History Scheduler - Backend Tasks

Este proyecto gestiona transacciones diarias y las mueve a un historial.

## 📌 Características
- Mueve automáticamente las transacciones diarias a un historial a la medianoche.
- Proporciona un endpoint REST para listar y filtrar transacciones históricas.

## Insertar Datos Iniciales
Desde resources `data.sql`:
```sql
INSERT INTO transaction_day (name, transaction_number, date, amount, type)
VALUES
    ('Juan Perez', 10001, '2025-04-02 10:15:30', 150.75, 'DEBIT'),
    ('Maria Lopez', 10002, '2025-04-02 11:45:00', 200.00, 'CREDIT'),
    ('Carlos Hunt', 10003, '2025-04-02 14:20:15', 50.25, 'DEBIT'),
    ('Diana Prince', 10004, '2025-04-02 16:05:45', 300.00, 'CREDIT'),
    ('Ana Aguilar', 10005, '2025-04-02 18:30:20', 75.99, 'DEBIT');
```
## 🔍 Endpoints REST
### Obtener todas las transacciones históricas (con paginación y filtros)
```http
GET http://localhost:8080/api/transactions/history/filter?page=0&size=20&sortBy=id&sortDirection=desc&keyword=a
```
**Algunos parámetros:**
- `page`: Número de página (por defecto `0`)
- `keyword`: Filtra por nombre, número de transacción o tipo

## TAREA 2

### Endpoints (POST)
NOT SUPPORTED
```http
POST http://localhost:8080/api/transactions/history/not-supported?name=Maria
```
NEVER
```http
POST http://localhost:8080/api/transactions/history/never?name=Maria
```
NESTED
```http
POST http://localhost:8080/api/transactions/history/nested?name=Maria&name2=Rosa
```
import http from 'k6/http';
import { check } from 'k6';
import { Counter } from 'k6/metrics';

const vendas = new Counter('vendas');

http.setResponseCallback(http.expectedStatuses(201, 409));

export const options = {
    thresholds: {
        vendas: ['count<=100'],
    },
    scenarios: {
        compra: {
            executor: 'per-vu-iterations',
            vus: 200,
            iterations: 1,
        },
    },
};

const BASE_URL = __ENV.BASE_URL || 'http://localhost:8080';

export default function () {
    const res = http.post(`${BASE_URL}/pedidos/1`);

    if (res.status === 201) {
        vendas.add(1);
    }

    check(res, {
        vendeu: (r) => r.status === 201,
        esgotado: (r) => r.status === 409,
    });
}
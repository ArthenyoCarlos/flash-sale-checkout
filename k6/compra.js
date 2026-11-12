import http from 'k6/http';
import { check } from 'k6';
import { Counter } from 'k6/metrics';

const vendas = new Counter('vendas');
const esgotados = new Counter('esgotados');
const conflitos = new Counter('conflitos');
const outros = new Counter('outros');

http.setResponseCallback(http.expectedStatuses(201, 409));

export const options = {
    thresholds: {
        vendas: ['count<=100'],
        outros: ['count==0'],
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
    let codigo;
    let outro = false;

    if (res.status === 201) {
        vendas.add(1);
    } else if (res.status === 409) {
        try {
            codigo = res.json().codigoErro;
        } catch (error) {
            outro = true;
        }

        if (outro) {
            outros.add(1);
        } else if (codigo === 'ESTOQUE_ESGOTADO') {
            esgotados.add(1);
        } else if (codigo === 'CONFLITO_CONCORRENCIA') {
            conflitos.add(1);
        } else {
            outros.add(1);
            outro = true;
        }
    } else {
        outros.add(1);
        outro = true;
    }

    check(res, {
        vendeu: (r) => r.status === 201,
        esgotado: (r) => r.status === 409 && codigo === 'ESTOQUE_ESGOTADO',
        conflito: (r) => r.status === 409 && codigo === 'CONFLITO_CONCORRENCIA',
        outro: () => outro,
    });
}
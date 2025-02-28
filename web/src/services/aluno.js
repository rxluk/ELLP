/* eslint-disable no-useless-catch */
import api from './api';

export const alunoService = {
    getAll: async () => {
        try {
            const [alunosResponse, notasResponse] = await Promise.all([
                api.get('/aluno'),
                api.get('/nota')
            ]);

            const alunos = alunosResponse.data;
            const todasNotas = notasResponse.data;

            const alunosComNotas = alunos.map(aluno => ({
                ...aluno,
                notas: todasNotas.filter(nota => nota.idAluno === aluno.id)
            }));

            return Array.isArray(alunos) ? alunosComNotas : [];
        } catch (error) {
            console.error('Erro ao buscar dados:', error);
            throw error;
        }
    },

    getById: async (id) => {
        try {
            const [alunoResponse, notasResponse] = await Promise.all([
                api.get(`/aluno/${id}`),
                api.get('/nota')
            ]);

            const notas = notasResponse.data.filter(nota => nota.idAluno === id);

            return {
                ...alunoResponse.data,
                notas: notas
            };
        } catch (error) {
            throw error;
        }
    },

    create: async (alunoData) => {
        try {
            const response = await api.post('/aluno', alunoData);
            return response.data;
        } catch (error) {
            throw error;
        }
    },

    update: async (id, alunoData) => {
        try {
            const response = await api.put(`/aluno/${id}`, alunoData);
            return response.data;
        } catch (error) {
            throw error;
        }
    },

    delete: async (id) => {
        try {
            const response = await api.delete(`/aluno/${id}`);
            return response.data;
        } catch (error) {
            throw error;
        }
    },

    buscarAvancado: async (filtros) => {
        try {
            const response = await api.get('/aluno/busca', { 
                params: filtros 
            });
            return response.data;
        } catch (error) {
            throw error;
        }
    }
};

export default alunoService;
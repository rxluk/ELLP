/* eslint-disable no-useless-catch */
import api from './api';

export const notaService = {
    getAll: async () => {
        try {
            const response = await api.get('/nota');
            return response.data.map(nota => ({
                ...nota,
                alunoId: nota.idAluno,
                disciplinaId: nota.idDisciplina
            }));
        } catch (error) {
            throw error;
        }
    },

    create: async (notaData) => {
        try {
            const dadosParaEnviar = {
                alunoId: notaData.alunoId,
                disciplinaId: notaData.disciplinaId,
                primeiroBimestre: Number(notaData.primeiroBimestre),
                segundoBimestre: Number(notaData.segundoBimestre),
                terceiroBimestre: Number(notaData.terceiroBimestre),
                quartoBimestre: Number(notaData.quartoBimestre),
                ano: Number(notaData.ano)
            };

            console.log('Dados preparados para envio:', {
                dados: dadosParaEnviar,
                tipos: {
                    alunoId: typeof dadosParaEnviar.alunoId,
                    disciplinaId: typeof dadosParaEnviar.disciplinaId,
                    primeiroBimestre: typeof dadosParaEnviar.primeiroBimestre,
                    segundoBimestre: typeof dadosParaEnviar.segundoBimestre,
                    terceiroBimestre: typeof dadosParaEnviar.terceiroBimestre,
                    quartoBimestre: typeof dadosParaEnviar.quartoBimestre,
                    ano: typeof dadosParaEnviar.ano
                }
            });

            const response = await api.post('/nota', dadosParaEnviar);

            console.log('Resposta do servidor:', response.data);

            return {
                ...response.data,
                alunoId: response.data.idAluno,
                disciplinaId: response.data.idDisciplina
            };
        } catch (error) {
            console.error('Erro detalhado:', {
                mensagem: error.message,
                resposta: error.response?.data,
                status: error.response?.status,
                headers: error.response?.headers,
                configEnviada: error.config
            });
            throw error;
        }
    },
    getByAlunoId: async (alunoId) => {
        try {
            const response = await api.get(`/nota/aluno/${alunoId}`);
            return response.data;
        } catch (error) {
            throw error;
        }
    },

    update: async (id, notaData) => {
        try {
            const response = await api.put(`/nota/${id}`, notaData);
            return response.data;
        } catch (error) {
            throw error;
        }
    },

    delete: async (id) => {
        try {
            const response = await api.delete(`/nota/${id}`);
            return response.data;
        } catch (error) {
            throw error;
        }
    },

    getByAlunoEDisciplina: async (alunoId, disciplinaId) => {
        try {
            const response = await api.get(`/nota/aluno/${alunoId}/disciplina/${disciplinaId}`);
            return response.data;
        } catch (error) {
            throw error;
        }
    }
};

export default notaService;
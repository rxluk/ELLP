import api from './api';

export const disciplinaService = {
    getAll: async () => {
        try {
            const response = await api.get('/disciplina');
            const data = response.data;
            return Array.isArray(data) ? data : [];
        } catch (error) {
            console.error('Erro ao buscar disciplinas:', error);
            throw error;
        }
    },

    getById: async (id) => {
        try {
            const response = await api.get(`/disciplina/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Erro ao buscar disciplina ${id}:`, error);
            throw error;
        }
    },

    create: async (disciplinaData) => {
        try {
            const response = await api.post('/disciplina', {
                nome: disciplinaData.nome,
                escolaId: disciplinaData.escolaId
            });
            return response.data;
        } catch (error) {
            console.error('Erro ao criar disciplina:', {
                message: error.message,
                detalhe: error.response?.data,
                status: error.response?.status
            });
            throw new Error(`Não foi possível criar a disciplina: ${error.message}`);
        }
    },

    update: async (id, disciplinaData) => {
        try {
            const response = await api.put(`/disciplina/${id}`, disciplinaData);
            return response.data;
        } catch (error) {
            console.error(`Erro ao atualizar disciplina ${id}:`, error);
            throw error;
        }
    },

    delete: async (id) => {
        try {
            const response = await api.delete(`/disciplina/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Erro ao deletar disciplina ${id}:`, error);
            throw error;
        }
    },

    buscarPorNome: async (nome) => {
        try {
            const response = await api.get(`/disciplina/nome/${nome}`);
            return response.data;
        } catch (error) {
            console.error(`Erro ao buscar disciplina por nome ${nome}:`, error);
            throw error;
        }
    }
};

export default disciplinaService;
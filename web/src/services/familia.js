/* eslint-disable no-useless-catch */
import api from './api';

export const familiaService = {
    getAll: async () => {
        try {
            const response = await api.get('/familia');
            return response.data;
        } catch (error) {
            throw error;
        }
    },

    getById: async (id) => {
        try {
            const response = await api.get(`/familia/${id}`);
            return response.data;
        } catch (error) {
            throw error;
        }
    },

    create: async (familiaData) => {
        try {
            const response = await api.post('/familia', familiaData);
            return response.data;
        } catch (error) {
            throw error;
        }
    },

    update: async (id, familiaData) => {
        try {
            const response = await api.put(`/familia/${id}`, familiaData);
            return response.data;
        } catch (error) {
            throw error;
        }
    },

    delete: async (id) => {
        try {
            const response = await api.delete(`/familia/${id}`);
            return response.data;
        } catch (error) {
            throw error;
        }
    }
};

export default familiaService;
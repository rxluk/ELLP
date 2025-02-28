import api from './api';

export const escolaService = {
  getAll: async () => {
    try {
      const response = await api.get('/escola');
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar escolas:', error);
      throw error;
    }
  },
  
  getById: async (id) => {
    try {
      const response = await api.get(`/escola/${id}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar escola por ID:', error);
      throw error;
    }
  },
  
  create: async (data) => {
    try {
      const response = await api.post('/escola', data);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar escola:', error);
      throw error;
    }
  },
  
  update: async (id, data) => {
    try {
      const response = await api.put(`/escola/${id}`, data);
      return response.data;
    } catch (error) {
      console.error('Erro ao atualizar escola:', error);
      throw error;
    }
  },
  
  delete: async (id) => {
    try {
      const response = await api.delete(`/escola/${id}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao deletar escola:', error);
      throw error;
    }
  }
};

export default escolaService;
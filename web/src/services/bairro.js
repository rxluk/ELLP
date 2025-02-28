import api from './api';
export const bairroService = {
    getAll: () => api.get('/bairro'),
    create: (data) => api.post('/bairro', data),
    update: (id, data) => api.put(`/bairro/${id}`, data),
    delete: (id) => api.delete(`/bairro/${id}`)
  };
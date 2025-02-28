import api from './api';

export const getColaboradores = async () => {
  return api.get('/colaborador/getAll');
};

export const getColaboradorByEmail = async (email) => {
  return api.get(`/colaborador/getByEmail/${email}`);
};

export const updateColaborador = async (id, data) => {
  return api.put(`/colaborador/updateById/${id}`, data);
};
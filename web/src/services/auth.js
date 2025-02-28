import api from './api';

export const login = async (credentials) => {
  const response = await api.post('/login', credentials);
  if (response.data.token) {
    localStorage.setItem('token', response.data.token);
  }
  return response.data;
};

export const registerColaborador = async (colaboradorData) => {
  const response = await api.post('/register/colaborador', colaboradorData);
  return response.data;
};

export const registerUser = async (userData) => {
  const response = await api.post('/register/user', userData);
  return response.data;
};
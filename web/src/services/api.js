import axios from 'axios';

const api = axios.create({
  baseURL: '/api',
  headers: {
    'Content-Type': 'application/json'
  }
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
}, (error) => {
  return Promise.reject(error);
});

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response) {
      switch (error.response.status) {
        case 401:
        case 403: 
          localStorage.removeItem('token');
          window.location.href = '/login';
          break;
        
        case 500:
          console.error('Erro interno do servidor:', error.response.data);
          break;
        
        default:
          console.error('Erro na requisição:', error.response.data);
      }
    } else if (error.request) {
      console.error('Erro de rede:', error.request);
    } else {
      console.error('Erro:', error.message);
    }
    
    return Promise.reject(error);
  }
);

export default api;
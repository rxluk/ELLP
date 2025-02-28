import { BrowserRouter as Router, Routes, Route, Navigate, useLocation } from 'react-router-dom';
import { AuthProvider } from './contexts/AuthContext';
import ProtectedRoute from './components/ProtectedRoute/ProtectedRoute';
import SideBar from './components/SideBar/SideBar';
import FormAluno from './components/FormAluno/FormAluno';
import FormFamilia from './components/FormFamilia/FormFamilia';
import FormEscola from './components/FormEscola/FormEscola';
import FormBairro from './components/FormBairro/FormBairro';
import LoginForm from './components/LoginForm/LoginForm';
import Dashboard from './components/Dashboard/DashBoard';
import RelatorioDesempenho from './components/RelatorioDesempenho/RelatorioDesempenho';
import { HistoricoNotas } from './components/HistoricoNotas/HistoricoNotas';
import BuscaAvancada from './components/BuscaAvancada/BuscaAvancada';
import LancamentoNotas from './components/LancamentoNotas/LancamentoNotas';
import DetalhesAluno from './components/DetalheAluno/DetalheAluno';
import FormDisciplina from './components/FormDisciplina/FormDisciplina';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './App.css';

const AppContent = () => {
  const location = useLocation();
  const isLoginPage = location.pathname === '/login';

  return (
    <div className={`app-container ${isLoginPage ? 'login-page' : ''}`}>
      <ToastContainer />
      <Routes>
        <Route path="/login" element={<LoginForm />} />
        <Route path="/" element={<Navigate to="/dashboard" />} />
        
        <Route path="/*" element={
          <ProtectedRoute>
            <div className="app-layout">
              <SideBar />
              <div className="main-content">
                <Routes>
                  <Route path="/dashboard" element={<Dashboard />} />
                  <Route path="/aluno" element={<FormAluno />} />
                  <Route path="/familia" element={<FormFamilia />} />
                  <Route path="/escola" element={<FormEscola />} />
                  <Route path="/bairro" element={<FormBairro />} />
                  <Route path="/relatorios" element={<RelatorioDesempenho />} />
                  <Route path="/aluno/:id/historico" element={<HistoricoNotas />} />
                  <Route path="/busca" element={<BuscaAvancada />} />
                  <Route path="/notas" element={<LancamentoNotas />} />
                  <Route path="/aluno/:id" element={<DetalhesAluno />} />
                  <Route path="/disciplina" element={<FormDisciplina />} />
                </Routes>
              </div>
            </div>
          </ProtectedRoute>
        } />
      </Routes>
    </div>
  );
};

function App() {
  return (
    <Router>
      <AuthProvider>
        <AppContent />
      </AuthProvider>
    </Router>
  );
}

export default App;
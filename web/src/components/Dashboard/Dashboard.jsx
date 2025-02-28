import { useState, useEffect } from 'react';
import { alunoService } from '../../services/aluno';
import { Link } from 'react-router-dom';
import { useAuth } from '../../contexts/AuthContext';
import './Dashboard.css';

const Dashboard = () => {
  const [stats, setStats] = useState({
    totalAlunos: 0,
    mediaNotas: 0,
  });

  const [alunos, setAlunos] = useState([]);
  const [loading, setLoading] = useState(true);
  const { logout } = useAuth();

  useEffect(() => {
    const loadDashboardData = async () => {
      try {
        setLoading(true);
        const alunos = await alunoService.getAll();
        
        const mediaGeral = alunos.reduce((acc, aluno) => {
          const mediaAluno = calcularMediaGeral(aluno.notas);
          return acc + parseFloat(mediaAluno);
        }, 0) / alunos.length || 0;

        setStats({
          totalAlunos: alunos.length,
          mediaNotas: mediaGeral,
        });

        setAlunos(alunos);
      } catch (error) {
        console.error('Erro ao carregar dados do dashboard:', error);
      } finally {
        setLoading(false);
      }
    };

    loadDashboardData();
  }, []);

  const calcularMediaGeral = (notas) => {
    if (!notas || !Array.isArray(notas) || notas.length === 0) {
      return 0;
    }

    let somaTotal = 0;
    let notasValidas = 0;

    for (const nota of notas) {
      const bimestres = [
        nota.primeiroBimestre,
        nota.segundoBimestre,
        nota.terceiroBimestre,
        nota.quartoBimestre
      ];

      const bimestresValidos = bimestres.map(Number).filter(valor => 
        !isNaN(valor) && valor !== null && valor !== undefined
      );

      if (bimestresValidos.length === 4) {
        const mediaDisciplina = bimestresValidos.reduce((acc, valor) => acc + valor, 0) / 4;
        somaTotal += mediaDisciplina;
        notasValidas++;
      }
    }

    if (notasValidas === 0) return 0;
    return (somaTotal / notasValidas);
  };

  if (loading) {
    return <div className="loading">Carregando...</div>;
  }

  return (
    <>
      <header className="header">
        <h2>Dashboard</h2>
      </header>

      <main className="container">
        <div className="dashboard-content">
          <div className="stats-grid">
            <div className="stat-box">
              <h3>Total de Alunos</h3>
              <p>{stats.totalAlunos}</p>
            </div>
            
            <div className="stat-box">
              <h3>Média de Notas</h3>
              <p>{stats.mediaNotas.toFixed(2)}</p>
            </div>
          </div>

          <div className="cadastro-links">
            <Link to="/aluno" className="cadastro-link cadastro-link-aluno">
              Cadastrar Aluno
            </Link>
            <Link to="/familia" className="cadastro-link cadastro-link-familia">
              Cadastrar Família
            </Link>
            <Link to="/escola" className="cadastro-link cadastro-link-escola">
              Cadastrar Escola
            </Link>
            <Link to="/bairro" className="cadastro-link cadastro-link-bairro">
              Cadastrar Bairro
            </Link>
          </div>

          <div className="logout-container">
            <button onClick={logout} className="logout-button">
              Sair
            </button>
          </div>
        </div>
      </main>
    </>
  );
};

export default Dashboard;
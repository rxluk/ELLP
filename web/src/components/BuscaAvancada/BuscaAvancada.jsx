import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { alunoService } from '../../services/aluno';
import { toast } from 'react-toastify';
import './BuscaAvancada.css';

const BuscaAvancada = () => {
  const navigate = useNavigate();
  const [criterios, setCriterios] = useState({
    nome: '',
    idade: '',
    escola: '',
    rendaMinima: '',
    rendaMaxima: '',
    mediaMinima: '',
  });

  const [resultados, setResultados] = useState([]);

  const filtrarResultados = (alunos, criterios) => {
    return alunos.filter(aluno => {
      // Filtro por nome
      if (criterios.nome && !aluno.nome?.toLowerCase().includes(criterios.nome.toLowerCase())) {
        return false;
      }

      // Filtro por idade
      if (criterios.idade) {
        const idadeAluno = aluno.idade || 
          (aluno.dataNascimento ? 
            new Date().getFullYear() - new Date(aluno.dataNascimento).getFullYear() 
            : null);
        if (idadeAluno !== Number(criterios.idade)) {
          return false;
        }
      }

      // Filtro por escola
      if (criterios.escola && !aluno.escola?.nome?.toLowerCase().includes(criterios.escola.toLowerCase())) {
        return false;
      }

      // Filtro por renda
      if (aluno.familia) {
        if (criterios.rendaMinima && aluno.familia.renda < Number(criterios.rendaMinima)) {
          return false;
        }
        if (criterios.rendaMaxima && aluno.familia.renda > Number(criterios.rendaMaxima)) {
          return false;
        }
      }

      // Filtro por média mínima
      if (criterios.mediaMinima && aluno.notas) {
        const mediaGeral = aluno.notas.reduce((acc, nota) => {
          const mediaNota = [
            nota.primeiroBimestre,
            nota.segundoBimestre,
            nota.terceiroBimestre,
            nota.quartoBimestre
          ].filter(n => n !== null && n !== undefined)
           .reduce((sum, n) => sum + n, 0) / 4;
          return acc + mediaNota;
        }, 0) / aluno.notas.length;

        if (mediaGeral < Number(criterios.mediaMinima)) {
          return false;
        }
      }

      return true;
    });
  };

  const buscarAlunos = async () => {
    try {
      const criteriosFiltrados = Object.fromEntries(
        Object.entries(criterios).filter(([_, value]) => value !== '')
      );

      const response = await alunoService.getAll();
      const todosAlunos = Array.isArray(response) 
        ? response 
        : (response.data || response.content || []);
      
      const resultadosFiltrados = filtrarResultados(todosAlunos, criteriosFiltrados);
      setResultados(resultadosFiltrados);

      if (resultadosFiltrados.length === 0) {
        toast.info('Nenhum aluno encontrado com esses critérios');
      }
    } catch (error) {
      console.error('Erro na busca:', error);
      toast.error('Erro ao realizar a busca. Tente novamente.');
      setResultados([]);
    }
  };

  return (
    <>
      <header className="header">
        <h2>Busca Avançada</h2>
      </header>

      <main className="container">
        <div className="busca-avancada">
          <div className="filtros-busca">
            <div className="form-group">
              <label htmlFor="nome">Nome do aluno</label>
              <input
                type="text"
                id="nome"
                value={criterios.nome}
                onChange={(e) => setCriterios({...criterios, nome: e.target.value})}
              />
            </div>
            
            <div className="form-group">
              <label htmlFor="idade">Idade</label>
              <input
                type="number"
                id="idade"
                value={criterios.idade}
                onChange={(e) => setCriterios({...criterios, idade: e.target.value})}
              />
            </div>
            
            <div className="form-group">
              <label htmlFor="escola">Escola</label>
              <input
                type="text"
                id="escola"
                value={criterios.escola}
                onChange={(e) => setCriterios({...criterios, escola: e.target.value})}
              />
            </div>
            
            <div className="form-group">
              <label htmlFor="rendaMinima">Renda mínima</label>
              <input
                type="number"
                id="rendaMinima"
                value={criterios.rendaMinima}
                onChange={(e) => setCriterios({...criterios, rendaMinima: e.target.value})}
              />
            </div>

            <div className="form-group">
              <label htmlFor="rendaMaxima">Renda máxima</label>
              <input
                type="number"
                id="rendaMaxima"
                value={criterios.rendaMaxima}
                onChange={(e) => setCriterios({...criterios, rendaMaxima: e.target.value})}
              />
            </div>
            
            <div className="form-group">
              <label htmlFor="mediaMinima">Média mínima</label>
              <input
                type="number"
                id="mediaMinima"
                value={criterios.mediaMinima}
                onChange={(e) => setCriterios({...criterios, mediaMinima: e.target.value})}
              />
            </div>
            
            <div className="div-botao">
              <button onClick={buscarAlunos}>Buscar</button>
            </div>
          </div>

          <div className="resultados-busca">
            {resultados.length > 0 ? (
              resultados.map(aluno => (
                <div key={aluno.id} className="card-aluno">
                  <h3>{aluno.nome || 'Nome não informado'}</h3>
                  <p>Idade: {aluno.idade || 
                    (aluno.dataNascimento ? 
                      new Date().getFullYear() - new Date(aluno.dataNascimento).getFullYear() 
                      : 'N/A')
                  }</p>
                  <p>Escola: {aluno.escola?.nome || 'N/A'}</p>
                  <p>Média: {aluno.notas ? 
                    (aluno.notas.reduce((acc, nota) => {
                      const mediaNota = [
                        nota.primeiroBimestre,
                        nota.segundoBimestre,
                        nota.terceiroBimestre,
                        nota.quartoBimestre
                      ].filter(n => n !== null && n !== undefined)
                       .reduce((sum, n) => sum + n, 0) / 4;
                      return acc + mediaNota;
                    }, 0) / aluno.notas.length).toFixed(1)
                    : 'N/A'
                  }</p>
                  <button 
                    onClick={() => navigate(`/aluno/${aluno.id}`)}
                    className="button-detalhes"
                  >
                    Ver detalhes
                  </button>
                </div>
              ))
            ) : (
              <p>Nenhum resultado encontrado</p>
            )}
          </div>
        </div>
      </main>
    </>
  );
};

export default BuscaAvancada;
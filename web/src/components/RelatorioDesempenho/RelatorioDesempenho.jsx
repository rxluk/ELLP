import { useState, useEffect } from 'react';
import { alunoService } from '../../services/aluno';
import { toast } from 'react-toastify';
import './RelatorioDesempenho.css';

const RelatorioDesempenho = () => {
  const [alunos, setAlunos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [filtros, setFiltros] = useState({
    rendaPerCapita: '',
    escola: '',
    evolucaoNotas: ''
  });

  useEffect(() => {
    const carregarDados = async () => {
      try {
        setLoading(true);
        const response = await alunoService.getAll();
        console.log('Dados completos:', response);
        setAlunos(response);
      } catch (error) {
        console.error('Erro ao carregar dados:', error);
        toast.error('Erro ao carregar dados dos alunos');
      } finally {
        setLoading(false);
      }
    };
    carregarDados();
  }, []);

  const calcularMediaGeral = (notas) => {
    if (!notas || !Array.isArray(notas) || notas.length === 0) {
      return '0.00';
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

    if (notasValidas === 0) return '0.00';
    return (somaTotal / notasValidas).toFixed(2);
  };

  const calcularEvolucao = (notas) => {
    if (!notas || !Array.isArray(notas) || notas.length === 0) {
      return { valor: "Sem dados", classe: '' };
    }

    const primeiraNotaValida = notas.find(nota => 
      !isNaN(nota.primeiroBimestre) && 
      !isNaN(nota.segundoBimestre) && 
      !isNaN(nota.terceiroBimestre) && 
      !isNaN(nota.quartoBimestre)
    );

    const ultimaNotaValida = [...notas].reverse().find(nota => 
      !isNaN(nota.primeiroBimestre) && 
      !isNaN(nota.segundoBimestre) && 
      !isNaN(nota.terceiroBimestre) && 
      !isNaN(nota.quartoBimestre)
    );

    if (!primeiraNotaValida || !ultimaNotaValida) {
      return { valor: "Dados incompletos", classe: '' };
    }

    const primeiraNota = (
      Number(primeiraNotaValida.primeiroBimestre) + 
      Number(primeiraNotaValida.segundoBimestre) + 
      Number(primeiraNotaValida.terceiroBimestre) + 
      Number(primeiraNotaValida.quartoBimestre)
    ) / 4;

    const ultimaNota = (
      Number(ultimaNotaValida.primeiroBimestre) + 
      Number(ultimaNotaValida.segundoBimestre) + 
      Number(ultimaNotaValida.terceiroBimestre) + 
      Number(ultimaNotaValida.quartoBimestre)
    ) / 4;

    if (primeiraNota === 0) {
      return { valor: "Não calculável", classe: '' };
    }

    const evolucao = ((ultimaNota - primeiraNota) / primeiraNota * 100);
    return {
      valor: `${evolucao.toFixed(1)}%`,
      classe: evolucao >= 0 ? 'evolucao-positiva' : 'evolucao-negativa'
    };
  };

  const filtrarAlunos = () => {
    let alunosFiltrados = [...alunos];
    
    if (filtros.rendaPerCapita) {
      alunosFiltrados = alunosFiltrados.filter(aluno => 
        aluno.familia && 
        (aluno.familia.renda / aluno.familia.grupoFamiliar <= Number(filtros.rendaPerCapita))
      );
    }

    if (filtros.escola) {
      alunosFiltrados = alunosFiltrados.filter(aluno => 
        aluno.escola?.nome?.toLowerCase().includes(filtros.escola.toLowerCase())
      );
    }

    return alunosFiltrados;
  };

  const exportarParaCSV = () => {
    const dadosCSV = [
      ['Aluno', 'Escola', 'Renda per capita', 'Média Geral', 'Evolução']
    ];

    filtrarAlunos().forEach(aluno => {
      const evolucao = calcularEvolucao(aluno.notas);
      dadosCSV.push([
        aluno.nome,
        aluno.escola?.nome || 'N/A',
        aluno.familia ? 
          `R$ ${(aluno.familia.renda / aluno.familia.grupoFamiliar).toFixed(2)}` :
          'N/A',
        calcularMediaGeral(aluno.notas),
        evolucao.valor
      ]);
    });

    const csvContent = dadosCSV.map(linha => linha.join(',')).join('\n');
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    
    link.setAttribute('href', url);
    link.setAttribute('download', 'relatorio_desempenho.csv');
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  };

  if (loading) {
    return <div className="loading">Carregando...</div>;
  }

  return (
    <>
      <header className="header">
        <h2>Relatório de Desempenho Acadêmico</h2>
        <button 
          onClick={exportarParaCSV}
          style={{
            marginLeft: '20px',
            padding: '10px 15px',
            backgroundColor: '#4CAF50',
            color: 'white',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer'
          }}
        >
          Exportar CSV
        </button>
      </header>

      <main className="container">
        <div className="relatorio-container">
          <div className="filtros">
            <div className="form-group">
              <label htmlFor="rendaPerCapita">Renda per capita até:</label>
              <input
                type="number"
                id="rendaPerCapita"
                value={filtros.rendaPerCapita}
                onChange={(e) => setFiltros({...filtros, rendaPerCapita: e.target.value})}
              />
            </div>

            <div className="form-group">
              <label htmlFor="escola">Escola:</label>
              <input
                type="text"
                id="escola"
                value={filtros.escola}
                onChange={(e) => setFiltros({...filtros, escola: e.target.value})}
              />
            </div>
          </div>

          <div className="tabela-container">
            <table className="tabela-relatorio">
              <thead>
                <tr>
                  <th>Aluno</th>
                  <th>Escola</th>
                  <th>Renda per capita</th>
                  <th>Média Geral</th>
                  <th>Evolução</th>
                </tr>
              </thead>
              <tbody>
                {filtrarAlunos().map(aluno => {
                  const evolucao = calcularEvolucao(aluno.notas);
                  return (
                    <tr key={aluno.id}>
                      <td>{aluno.nome}</td>
                      <td>{aluno.escola?.nome || 'N/A'}</td>
                      <td>
                        {aluno.familia ? 
                          `R$ ${(aluno.familia.renda / aluno.familia.grupoFamiliar).toFixed(2)}` :
                          'N/A'}
                      </td>
                      <td>{calcularMediaGeral(aluno.notas)}</td>
                      <td className={evolucao.classe}>{evolucao.valor}</td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>
        </div>
      </main>
    </>
  );
};

export default RelatorioDesempenho;
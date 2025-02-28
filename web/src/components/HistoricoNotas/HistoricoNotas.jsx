import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { alunoService } from '../../services/aluno';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';

export const HistoricoNotas = () => {
  const { id } = useParams();
  const [aluno, setAluno] = useState(null);
  const [historico, setHistorico] = useState([]);

  useEffect(() => {
    const carregarHistorico = async () => {
      try {
        const alunoData = await alunoService.getById(id);
        setAluno(alunoData);
        
        const notasOrganizadas = alunoData.notas.map(nota => ({
          bimestre: `${nota.ano}/${nota.bimestre}`,
          valor: nota.valor,
          disciplina: nota.disciplina.nome
        }));
        
        setHistorico(notasOrganizadas);
      } catch (error) {
        console.error('Erro ao carregar histórico:', error);
      }
    };

    if (id) {
      carregarHistorico();
    }
  }, [id]);

  return (
    <div className="historico-container">
      <h2>Histórico de Notas - {aluno?.nome}</h2>

      <div className="grafico-container">
        <LineChart width={800} height={400} data={historico}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="bimestre" />
          <YAxis />
          <Tooltip />
          <Legend />
          <Line type="monotone" dataKey="valor" stroke="#8884d8" />
        </LineChart>
      </div>

      <table className="tabela-historico">
        <thead>
          <tr>
            <th>Disciplina</th>
            <th>Bimestre</th>
            <th>Nota</th>
            <th>Situação</th>
          </tr>
        </thead>
        <tbody>
          {historico.map((nota, index) => (
            <tr key={index}>
              <td>{nota.disciplina}</td>
              <td>{nota.bimestre}</td>
              <td>{nota.valor}</td>
              <td>{nota.valor >= 7 ? 'Aprovado' : 'Em recuperação'}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
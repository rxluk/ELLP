import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { alunoService } from '../../services/aluno';
import { toast } from 'react-toastify';
import './DetalheAluno.css'

const DetalhesAluno = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [aluno, setAluno] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const carregarDetalhesAluno = async () => {
      try {
        const dadosAluno = await alunoService.getById(id);
        setAluno(dadosAluno);
        setLoading(false);
      } catch (error) {
        console.error('Erro ao carregar detalhes do aluno:', error);
        toast.error('Erro ao carregar informações do aluno');
        setLoading(false);
      }
    };

    carregarDetalhesAluno();
  }, [id]);

  if (loading) {
    return <div>Carregando...</div>;
  }

  if (!aluno) {
    return <div>Aluno não encontrado</div>;
  }

  return (
    <div className="detalhes-aluno-container">
      <header className="header">
        <h2>Detalhes do Aluno</h2>
        <button 
          onClick={() => navigate(-1)} 
          className="btn-voltar"
        >
          Voltar
        </button>
      </header>

      <main className="detalhes-aluno-content">
        <section className="informacoes-pessoais">
          <h3>Informações Pessoais</h3>
          <div className="info-grid">
            <div className="info-item">
              <strong>Nome:</strong> {aluno.nome}
            </div>
            <div className="info-item">
              <strong>Data de Nascimento:</strong> {new Date(aluno.dataNascimento).toLocaleDateString()}
            </div>
            <div className="info-item">
              <strong>Série:</strong> {aluno.serie}
            </div>
            <div className="info-item">
              <strong>Escola:</strong> {aluno.escola?.nome || 'Não informada'}
            </div>
          </div>
        </section>

        <section className="informacoes-endereco">
          <h3>Endereço</h3>
          <div className="info-grid">
            <div className="info-item">
              <strong>Rua:</strong> {aluno.rua}
            </div>
            <div className="info-item">
              <strong>Número:</strong> {aluno.numero}
            </div>
            <div className="info-item">
              <strong>Bairro:</strong> {aluno.bairro}
            </div>
          </div>
        </section>

        <section className="informacoes-adicionais">
          <h3>Informações Adicionais</h3>
          <div className="info-grid">
            <div className="info-item">
              <strong>Necessita Transporte:</strong> {aluno.necessitaTransporte ? 'Sim' : 'Não'}
            </div>
            <div className="info-item">
              <strong>Recebe Atendimento Médico:</strong> {aluno.recebeAtendimentoMedico ? 'Sim' : 'Não'}
            </div>
          </div>
        </section>

        {aluno.familia && (
          <section className="informacoes-familia">
            <h3>Informações Familiares</h3>
            <div className="info-grid">
              <div className="info-item">
                <strong>Grupo Familiar:</strong> {aluno.familia.grupoFamiliar}
              </div>
              <div className="info-item">
                <strong>Renda:</strong> R$ {aluno.familia.renda.toFixed(2)}
              </div>
              <div className="info-item">
                <strong>Acesso à Internet:</strong> {aluno.familia.acessoInternet ? 'Sim' : 'Não'}
              </div>
              <div className="info-item">
                <strong>Possui Computador:</strong> {aluno.familia.possuiComputador ? 'Sim' : 'Não'}
              </div>
            </div>
          </section>
        )}

        <section className="historico-notas">
          <h3>Histórico de Notas</h3>
          {aluno.notas && aluno.notas.length > 0 ? (
            <table className="tabela-notas">
              <thead>
                <tr>
                  <th>Disciplina</th>
                  <th>1º Bimestre</th>
                  <th>2º Bimestre</th>
                  <th>3º Bimestre</th>
                  <th>4º Bimestre</th>
                  <th>Média</th>
                </tr>
              </thead>
              <tbody>
                {aluno.notas.map((nota, index) => (
                  <tr key={index}>
                    <td>{nota.disciplina?.nome || 'Não informada'}</td>
                    <td>{nota.primeiroBimestre?.toFixed(1) || 'N/A'}</td>
                    <td>{nota.segundoBimestre?.toFixed(1) || 'N/A'}</td>
                    <td>{nota.terceiroBimestre?.toFixed(1) || 'N/A'}</td>
                    <td>{nota.quartoBimestre?.toFixed(1) || 'N/A'}</td>
                    <td>
                      {nota.primeiroBimestre && nota.segundoBimestre && 
                       nota.terceiroBimestre && nota.quartoBimestre
                        ? ((nota.primeiroBimestre + nota.segundoBimestre + 
                            nota.terceiroBimestre + nota.quartoBimestre) / 4).toFixed(1)
                        : 'N/A'}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <p>Nenhuma nota registrada</p>
          )}
        </section>
      </main>
    </div>
  );
};

export default DetalhesAluno;
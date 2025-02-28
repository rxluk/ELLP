import { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { alunoService } from '../../services/aluno';
import { disciplinaService } from '../../services/disciplina';
import { notaService } from '../../services/nota';
import { toast } from 'react-toastify';
import './LancamentoNotas.css';

const LancamentoNotas = () => {
    const [alunos, setAlunos] = useState([]);
    const [disciplinas, setDisciplinas] = useState([]);
    const [todasNotas, setTodasNotas] = useState([]);
    const [carregando, setCarregando] = useState({
        alunos: true,
        disciplinas: true,
        notas: true
    });
    
    const { 
        register, 
        handleSubmit, 
        reset, 
        watch, 
        formState: { errors } 
    } = useForm({
        defaultValues: {
            alunoId: '',
            disciplinaId: '',
            primeiroBimestre: '',
            segundoBimestre: '',
            terceiroBimestre: '',
            quartoBimestre: ''
        }
    });

    const alunoSelecionado = watch('alunoId');
    const disciplinaSelecionada = watch('disciplinaId');

    // Carrega os dados separadamente para melhor tratamento de erro
    useEffect(() => {
        const carregarAlunos = async () => {
            try {
                const response = await alunoService.getAll();
                setAlunos(response);
            } catch (error) {
                console.error('Erro ao carregar alunos:', error);
                toast.error('Erro ao carregar lista de alunos');
            } finally {
                setCarregando(prev => ({ ...prev, alunos: false }));
            }
        };

        const carregarDisciplinas = async () => {
            try {
                const response = await disciplinaService.getAll();
                setDisciplinas(response);
            } catch (error) {
                console.error('Erro ao carregar disciplinas:', error);
                toast.error('Erro ao carregar disciplinas. O serviço pode estar indisponível.');
                // Disciplinas provisórias para teste
                setDisciplinas([
                    { id: '1', nome: 'Matemática' },
                    { id: '2', nome: 'Português' },
                    { id: '3', nome: 'Ciências' }
                ]);
            } finally {
                setCarregando(prev => ({ ...prev, disciplinas: false }));
            }
        };

        const carregarNotas = async () => {
            try {
                const response = await notaService.getAll();
                setTodasNotas(response);
            } catch (error) {
                console.error('Erro ao carregar notas:', error);
                toast.error('Erro ao carregar histórico de notas');
            } finally {
                setCarregando(prev => ({ ...prev, notas: false }));
            }
        };

        carregarAlunos();
        carregarDisciplinas();
        carregarNotas();
    }, []);

    const getNotasAnteriores = () => {
        if (!alunoSelecionado || !disciplinaSelecionada) return [];
        
        return todasNotas.filter(nota => 
            nota.alunoId === alunoSelecionado && 
            nota.disciplinaId === disciplinaSelecionada
        ).sort((a, b) => b.ano - a.ano);
    };

    const onSubmit = async (data) => {
        try {
            const notaData = {
                alunoId: data.alunoId,
                disciplinaId: data.disciplinaId,
                primeiroBimestre: parseFloat(data.primeiroBimestre || 0),
                segundoBimestre: parseFloat(data.segundoBimestre || 0),
                terceiroBimestre: parseFloat(data.terceiroBimestre || 0),
                quartoBimestre: parseFloat(data.quartoBimestre || 0),
                ano: 2025 // Ano fixo para teste
            };
    
            console.log('Enviando dados:', {
                dados: notaData,
                validacoes: {
                    alunoExiste: Boolean(notaData.alunoId),
                    disciplinaExiste: Boolean(notaData.disciplinaId),
                    notasValidas: !isNaN(notaData.primeiroBimestre) &&
                                 !isNaN(notaData.segundoBimestre) &&
                                 !isNaN(notaData.terceiroBimestre) &&
                                 !isNaN(notaData.quartoBimestre)
                }
            });
    
            const response = await notaService.create(notaData);
            console.log('Nota criada:', response);
            
            toast.success('Notas cadastradas com sucesso!');
            reset();
            
            const notasAtualizadas = await notaService.getAll();
            setTodasNotas(notasAtualizadas);
        } catch (error) {
            console.error('Erro ao criar nota:', {
                mensagem: error.message,
                resposta: error.response?.data,
                status: error.response?.status
            });
            
            const mensagemErro = error.response?.data?.message 
                || 'Erro ao cadastrar notas. Verifique os dados e tente novamente.';
            
            toast.error(mensagemErro);
        }
    };
    if (carregando.alunos) {
        return <div className="loading">Carregando alunos...</div>;
    }

    const notasAnteriores = getNotasAnteriores();

    return (
        
        <div className="lancamento-notas-container">
            <header className="header">
                <h2>Lançamento de Notas</h2>
            </header>
            <main className="container">
            
                <form onSubmit={handleSubmit(onSubmit)} className="form-notas">
                    <div className="form-section">
                        <div className="form-group">
                            <label htmlFor="alunoId">Aluno</label>
                            <select
                                id="alunoId"
                                {...register('alunoId', { 
                                    required: 'Selecione um aluno'
                                })}
                            >
                                <option value="">Selecione um aluno</option>
                                {alunos.map(aluno => (
                                    <option key={aluno.id} value={aluno.id}>
                                        {aluno.nome}
                                    </option>
                                ))}
                            </select>
                            {errors.alunoId && (
                                <span className="error-message">
                                    {errors.alunoId.message}
                                </span>
                            )}
                        </div>

                        <div className="form-group">
                            <label htmlFor="disciplinaId">Disciplina</label>
                            <select
                                id="disciplinaId"
                                {...register('disciplinaId', { 
                                    required: 'Selecione uma disciplina'
                                })}
                                disabled={carregando.disciplinas}
                            >
                                <option value="">
                                    {carregando.disciplinas 
                                        ? 'Carregando disciplinas...' 
                                        : 'Selecione uma disciplina'}
                                </option>
                                {disciplinas.map(disciplina => (
                                    <option key={disciplina.id} value={disciplina.id}>
                                        {disciplina.nome}
                                    </option>
                                ))}
                            </select>
                            {errors.disciplinaId && (
                                <span className="error-message">
                                    {errors.disciplinaId.message}
                                </span>
                            )}
                        </div>
                    </div>

                    {notasAnteriores.length > 0 && (
                        <div className="historico-notas">
                            <h3>Histórico de Notas Anteriores</h3>
                            <table>
                                <thead>
                                    <tr>
                                        <th>Ano</th>
                                        <th>1º Bimestre</th>
                                        <th>2º Bimestre</th>
                                        <th>3º Bimestre</th>
                                        <th>4º Bimestre</th>
                                        <th>Média</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {notasAnteriores.map((nota, index) => {
                                        const media = (
                                            (nota.primeiroBimestre || 0) +
                                            (nota.segundoBimestre || 0) +
                                            (nota.terceiroBimestre || 0) +
                                            (nota.quartoBimestre || 0)
                                        ) / 4;
                                        
                                        return (
                                            <tr key={index}>
                                                <td>{nota.ano}</td>
                                                <td>{nota.primeiroBimestre?.toFixed(1) || 'N/A'}</td>
                                                <td>{nota.segundoBimestre?.toFixed(1) || 'N/A'}</td>
                                                <td>{nota.terceiroBimestre?.toFixed(1) || 'N/A'}</td>
                                                <td>{nota.quartoBimestre?.toFixed(1) || 'N/A'}</td>
                                                <td>{media.toFixed(1)}</td>
                                            </tr>
                                        );
                                    })}
                                </tbody>
                            </table>
                        </div>
                    )}

                    <div className="form-section notas-bimestre">
                        <h3>Novas Notas</h3>
                        <div className="notas-grid">
                            {['primeiro', 'segundo', 'terceiro', 'quarto'].map((bimestre, index) => (
                                <div className="form-group" key={bimestre}>
                                    <label htmlFor={`${bimestre}Bimestre`}>
                                        {`${index + 1}º Bimestre`}
                                    </label>
                                    <input
                                        type="number"
                                        id={`${bimestre}Bimestre`}
                                        step="0.1"
                                        min="0"
                                        max="10"
                                        {...register(`${bimestre}Bimestre`, {
                                            required: 'Nota obrigatória',
                                            min: { value: 0, message: 'Nota mínima é 0' },
                                            max: { value: 10, message: 'Nota máxima é 10' }
                                        })}
                                    />
                                    {errors[`${bimestre}Bimestre`] && (
                                        <span className="error-message">
                                            {errors[`${bimestre}Bimestre`].message}
                                        </span>
                                    )}
                                </div>
                            ))}
                        </div>
                    </div>

                    <div className="div-botao">
                        <button 
                            type="submit" 
                            className="btn-salvar-notas"
                            disabled={carregando.disciplinas}
                        >
                            {carregando.disciplinas ? 'Carregando...' : 'Salvar Notas'}
                        </button>
                    </div>
                </form>
            </main>
        </div>
    );
};

export default LancamentoNotas;
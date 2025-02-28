import { useForm } from 'react-hook-form';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './FormAluno.css';
import { alunoService } from '../../services/aluno';
import { useState, useEffect } from 'react';
import { familiaService } from '../../services/familia';
import { escolaService } from '../../services/escola';

export default function FormAluno() {
    const [familias, setFamilias] = useState([]);
    const [escolas, setEscolas] = useState([]);

    const { register, handleSubmit, formState: { errors, isSubmitting }, reset } = useForm({
        defaultValues: {
            nome: '',
            bairro: '',
            rua: '',
            numero: '',
            serie: '',
            dataNascimento: '',
            necessitaTransporte: false,
            recebeAtendimentoMedico: false,
            familiaId: '',
            escolaId: ''
        }
    });

    useEffect(() => {
        const carregarDados = async () => {
            try {
                const [familiasResponse, escolasResponse] = await Promise.all([
                    familiaService.getAll(),
                    escolaService.getAll()
                ]);
                
                setFamilias(familiasResponse);
                setEscolas(escolasResponse);
            } catch (error) {
                toast.error('Erro ao carregar dados');
                console.error('Erro ao carregar dados:', error);
            }
        };

        carregarDados();
    }, []);

    const onSubmit = async (data) => {
        try {
            const loadingToast = toast.loading("Salvando dados...");
            
            const formattedData = {
                nome: data.nome,
                bairro: data.bairro,
                rua: data.rua,
                numero: parseInt(data.numero),
                serie: data.serie,
                dataNascimento: new Date(data.dataNascimento).toISOString(),
                necessitaTransporte: data.necessitaTransporte,
                recebeAtendimentoMedico: data.recebeAtendimentoMedico,
                familia: {
                    id: data.familiaId
                },
                escola: {
                    id: data.escolaId
                }
            };

            await alunoService.create(formattedData);
            
            toast.update(loadingToast, {
                render: "Aluno cadastrado com sucesso!",
                type: "success",
                isLoading: false,
                autoClose: 3000
            });

            reset();
        } catch (error) {
            toast.error('Erro ao cadastrar aluno. Por favor, tente novamente.');
            console.error('Erro ao cadastrar aluno:', error);
        }
    };

    return (
        <>
            <ToastContainer />
            <header className="header">
                    <h2>Cadastro de Aluno</h2>
                </header>
            <main className="container">
                
                
                <form onSubmit={handleSubmit(onSubmit)} className="form-aluno">
                    <div className="form-section">
                        <h3>Dados Pessoais</h3>
                        <div className="form-row">
                            <div className="form-group">
                                <label htmlFor="nome">Nome Completo</label>
                                <input
                                    type="text"
                                    id="nome"
                                    {...register('nome', { required: 'Nome é obrigatório' })}
                                />
                                {errors.nome && <span className="error">{errors.nome.message}</span>}
                            </div>

                            <div className="form-group">
                                <label htmlFor="dataNascimento">Data de Nascimento</label>
                                <input
                                    type="date"
                                    id="dataNascimento"
                                    {...register('dataNascimento', { required: 'Data de nascimento é obrigatória' })}
                                />
                                {errors.dataNascimento && <span className="error">{errors.dataNascimento.message}</span>}
                            </div>

                            <div className="form-group">
                                <label htmlFor="serie">Série</label>
                                <input
                                    type="text"
                                    id="serie"
                                    {...register('serie', { required: 'Série é obrigatória' })}
                                />
                                {errors.serie && <span className="error">{errors.serie.message}</span>}
                            </div>
                        </div>
                    </div>

                    <div className="form-section">
                        <h3>Endereço</h3>
                        <div className="form-row">
                            <div className="form-group">
                                <label htmlFor="rua">Rua</label>
                                <input
                                    type="text"
                                    id="rua"
                                    {...register('rua', { required: 'Rua é obrigatória' })}
                                />
                                {errors.rua && <span className="error">{errors.rua.message}</span>}
                            </div>

                            <div className="form-group">
                                <label htmlFor="numero">Número</label>
                                <input
                                    type="number"
                                    id="numero"
                                    {...register('numero', { required: 'Número é obrigatório' })}
                                />
                                {errors.numero && <span className="error">{errors.numero.message}</span>}
                            </div>

                            <div className="form-group">
                                <label htmlFor="bairro">Bairro</label>
                                <input
                                    type="text"
                                    id="bairro"
                                    {...register('bairro', { required: 'Bairro é obrigatório' })}
                                />
                                {errors.bairro && <span className="error">{errors.bairro.message}</span>}
                            </div>
                        </div>
                    </div>

                    <div className="form-section">
                        <h3>Vínculos</h3>
                        <div className="form-row">
                            <div className="form-group">
                                <label htmlFor="familiaId">Família</label>
                                <select
                                    id="familiaId"
                                    {...register('familiaId', { required: 'Família é obrigatória' })}
                                >
                                    <option value="">Selecione uma família</option>
                                    {familias.map((familia) => (
                                        <option key={familia.id} value={familia.id}>
                                            Família {familia.grupoFamiliar} - Renda: R$ {familia.renda}
                                        </option>
                                    ))}
                                </select>
                                {errors.familiaId && <span className="error">{errors.familiaId.message}</span>}
                            </div>

                            <div className="form-group">
                                <label htmlFor="escolaId">Escola</label>
                                <select
                                    id="escolaId"
                                    {...register('escolaId', { required: 'Escola é obrigatória' })}
                                >
                                    <option value="">Selecione uma escola</option>
                                    {escolas.map((escola) => (
                                        <option key={escola.id} value={escola.id}>
                                            {escola.nome}
                                        </option>
                                    ))}
                                </select>
                                {errors.escolaId && <span className="error">{errors.escolaId.message}</span>}
                            </div>
                        </div>
                    </div>

                    <div className="form-section">
                        <h3>Informações Adicionais</h3>
                        <div className="form-row checkbox-row">
                            <div className="form-group checkbox-group">
                                <label>
                                    <input
                                        type="checkbox"
                                        {...register('necessitaTransporte')}
                                    />
                                    Necessita de Transporte
                                </label>
                            </div>

                            <div className="form-group checkbox-group">
                                <label>
                                    <input
                                        type="checkbox"
                                        {...register('recebeAtendimentoMedico')}
                                    />
                                    Recebe Atendimento Médico
                                </label>
                            </div>
                        </div>
                    </div>

                    <div className="div-botao">
                        <button 
                            type="submit" 
                            disabled={isSubmitting}
                            className={isSubmitting ? 'loading' : ''}
                        >
                            {isSubmitting ? 'Salvando...' : 'Salvar'}
                        </button>
                    </div>
                </form>
            </main>
        </>
    );
}
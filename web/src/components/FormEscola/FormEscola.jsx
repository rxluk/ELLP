import { useForm } from 'react-hook-form';
import { escolaService } from '../../services/escola';
import { toast } from 'react-toastify';
import './FormEscola.css';

export default function FormEscola() {
    const { 
        register, 
        handleSubmit, 
        formState: { errors, isSubmitting }, 
        reset 
    } = useForm({
        defaultValues: {
            nome: '',
            bairro: '',
            endereco: '',
            numero: '',
            telefone: ''
        }
    });

    const onSubmit = async (data) => {
        try {
            const dadosEscola = {
                nome: data.nome,
                bairro: data.bairro,
                rua: data.endereco,  
                numero: parseInt(data.numero, 10),
                telefone: data.telefone
            };

            console.log('Dados processados:', dadosEscola);

            const response = await escolaService.create(dadosEscola);
            
            console.log('Resposta do servidor:', response);

            toast.success('Escola cadastrada com sucesso!');
            reset();
        } catch (error) {
            console.error('Erro completo:', error);
            console.error('Detalhes do erro:', {
                message: error.message,
                response: error.response?.data,
                status: error.response?.status,
                headers: error.response?.headers
            });

            const errorMessage = error.response?.data?.message 
                || error.response?.data 
                || 'Erro ao cadastrar escola. Por favor, tente novamente.';
            
            toast.error(errorMessage);
        }
    };

    return (
        <>
            <header className="header">
                <h2>Cadastrar Escola</h2>
            </header>

            <main className="container">
                <form onSubmit={handleSubmit(onSubmit)} className="form-escola">
                    <div className="form-group">
                        <label htmlFor="nome">Nome:</label>
                        <input
                            type="text"
                            id="nome"
                            {...register('nome', { 
                                required: 'Nome é obrigatório',
                                minLength: {
                                    value: 3,
                                    message: 'Nome deve ter no mínimo 3 caracteres'
                                }
                            })}
                        />
                        {errors.nome && <p className="error">{errors.nome.message}</p>}
                    </div>

                    <div className="form-group">
                        <label htmlFor="bairro">Bairro:</label>
                        <input
                            type="text"
                            id="bairro"
                            {...register('bairro', { 
                                required: 'Bairro é obrigatório',
                                minLength: {
                                    value: 2,
                                    message: 'Bairro deve ter no mínimo 2 caracteres'
                                }
                            })}
                        />
                        {errors.bairro && <p className="error">{errors.bairro.message}</p>}
                    </div>

                    <div className="form-group">
                        <label htmlFor="endereco">Endereço:</label>
                        <input
                            type="text"
                            id="endereco"
                            {...register('endereco', { 
                                required: 'Endereço é obrigatório',
                                minLength: {
                                    value: 5,
                                    message: 'Endereço deve ter no mínimo 5 caracteres'
                                }
                            })}
                        />
                        {errors.endereco && <p className="error">{errors.endereco.message}</p>}
                    </div>

                    <div className="form-group">
                        <label htmlFor="numero">Nº:</label>
                        <input
                            type="text"
                            id="numero"
                            {...register('numero', { 
                                required: 'Número é obrigatório',
                                pattern: {
                                    value: /^[0-9]+$/,
                                    message: 'Número deve conter apenas dígitos'
                                }
                            })}
                        />
                        {errors.numero && <p className="error">{errors.numero.message}</p>}
                    </div>

                    <div className="form-group">
                        <label htmlFor="telefone">Telefone:</label>
                        <input
                            type="text"
                            id="telefone"
                            {...register('telefone', { 
                                required: 'Telefone é obrigatório',
                                pattern: {
                                    value: /^[0-9]{10,11}$/,
                                    message: 'Telefone deve conter 10 ou 11 dígitos'
                                }
                            })}
                        />
                        {errors.telefone && <p className="error">{errors.telefone.message}</p>}
                    </div>

                    <button 
                        type="submit" 
                        className="button-cadastrar"
                        disabled={isSubmitting}
                    >
                        {isSubmitting ? 'Cadastrando...' : 'Cadastrar'}
                    </button>
                </form>
            </main>
        </>
    );
}
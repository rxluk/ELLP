import { useForm } from 'react-hook-form';
import './FormFamilia.css';
import { familiaService } from '../../services/familia';

export default function FormFamilia() {
    const { register, handleSubmit, formState: { errors, isSubmitting } } = useForm({
        defaultValues: {
            grupoFamiliar: 0,
            pessoasEmpregadas: 0,
            renda: 0,
            acessoInternet: false,
            possuiComputador: false,
            casaPropria: false,
            possuiCarro: false
        }
    });

    const onSubmit = async (data) => {
        try {
            const formattedData = {
                ...data,
                grupoFamiliar: Number(data.grupoFamiliar),
                pessoasEmpregadas: Number(data.pessoasEmpregadas),
                renda: Number(data.renda)
            };

            await familiaService.create(formattedData);
            alert('Família cadastrada com sucesso!');
        } catch (error) {
            console.error('Erro ao cadastrar família:', error);
            alert('Erro ao cadastrar família. Por favor, tente novamente.');
        }
    };

    return (
        <>
            <header className="header">
                <h2>Cadastro de família</h2>
            </header>
            <main className="container">
            
                <section className="titulo">
                    <h2>Família:</h2>
                </section>

                <form onSubmit={handleSubmit(onSubmit)}>
                    <div className="form-row">
                        <div className="form-numeric-fields">
                            <div className="form-group">
                                <label htmlFor="grupoFamiliar">Número de pessoas no grupo familiar</label>
                                <input
                                    type="number"
                                    id="grupoFamiliar"
                                    min="0"
                                    {...register('grupoFamiliar', { 
                                        required: 'Este campo é obrigatório',
                                        min: {
                                            value: 0,
                                            message: 'O valor deve ser maior ou igual a 0'
                                        }
                                    })}
                                />
                                {errors.grupoFamiliar && <p className="error">{errors.grupoFamiliar.message}</p>}
                            </div>

                            <div className="form-group">
                                <label htmlFor="pessoasEmpregadas">Número de pessoas empregadas</label>
                                <input
                                    type="number"
                                    id="pessoasEmpregadas"
                                    min="0"
                                    {...register('pessoasEmpregadas', { 
                                        required: 'Este campo é obrigatório',
                                        min: {
                                            value: 0,
                                            message: 'O valor deve ser maior ou igual a 0'
                                        }
                                    })}
                                />
                                {errors.pessoasEmpregadas && <p className="error">{errors.pessoasEmpregadas.message}</p>}
                            </div>

                            <div className="form-group">
                                <label htmlFor="renda">Renda familiar total</label>
                                <input
                                    type="number"
                                    id="renda"
                                    min="0"
                                    step="0.01"
                                    {...register('renda', { 
                                        required: 'Este campo é obrigatório',
                                        min: {
                                            value: 0,
                                            message: 'O valor deve ser maior ou igual a 0'
                                        }
                                    })}
                                />
                                {errors.renda && <p className="error">{errors.renda.message}</p>}
                            </div>
                        </div>

                        <div className="form-checkbox-fields">
                            <div className="form-group checkbox-container">
                                <label htmlFor="acessoInternet">
                                    <input
                                        type="checkbox"
                                        id="acessoInternet"
                                        {...register('acessoInternet')}
                                    />
                                    Acesso à internet em casa
                                </label>
                            </div>

                            <div className="form-group checkbox-container">
                                <label htmlFor="possuiComputador">
                                    <input
                                        type="checkbox"
                                        id="possuiComputador"
                                        {...register('possuiComputador')}
                                    />
                                    Possui computador em casa
                                </label>
                            </div>

                            <div className="form-group checkbox-container">
                                <label htmlFor="casaPropria">
                                    <input
                                        type="checkbox"
                                        id="casaPropria"
                                        {...register('casaPropria')}
                                    />
                                    Possui casa própria
                                </label>
                            </div>

                            <div className="form-group checkbox-container">
                                <label htmlFor="possuiCarro">
                                    <input
                                        type="checkbox"
                                        id="possuiCarro"
                                        {...register('possuiCarro')}
                                    />
                                    Possui carro próprio
                                </label>
                            </div>
                        </div>
                    </div>

                    <div className="div-botao">
                        <button type="submit" disabled={isSubmitting}>
                            {isSubmitting ? 'Salvando...' : 'Salvar'}
                        </button>
                    </div>
                </form>
            </main>
        </>
    );
}
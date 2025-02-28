import { useForm } from 'react-hook-form';
import { bairroService } from '../../services/bairro';
import './FormBairro.css';

export default function FormBairro() {
    const { register, handleSubmit, formState: { errors, isSubmitting }, reset } = useForm();

    const onSubmit = async (data) => {
        try {
            await bairroService.create(data);
            alert('Bairro cadastrado com sucesso!');
            reset();
        } catch (error) {
            console.error('Erro ao cadastrar bairro:', error);
            alert('Erro ao cadastrar bairro. Por favor, tente novamente.');
        }
    };

    return (
        <>
            <header className="header">
                <h2>Cadastrar Bairro</h2>
            </header>

            <main className="container">
                <form onSubmit={handleSubmit(onSubmit)} className="form-bairro">
                    <div className="form-group">
                        <label htmlFor="nomeBairro">Nome do Bairro:</label>
                        <input
                            type="text"
                            id="nomeBairro"
                            {...register('nomeBairro', { required: 'Nome do bairro é obrigatório' })}
                        />
                        {errors.nomeBairro && <p className="error">{errors.nomeBairro.message}</p>}
                    </div>

                    <div className="form-group">
                        <label htmlFor="cidade">Cidade:</label>
                        <input
                            type="text"
                            id="cidade"
                            {...register('cidade', { required: 'Cidade é obrigatória' })}
                        />
                        {errors.cidade && <p className="error">{errors.cidade.message}</p>}
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
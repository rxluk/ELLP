import { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { disciplinaService } from '../../services/disciplina';
import { escolaService } from '../../services/escola';
import { toast } from 'react-toastify';
import './FormDisciplina.css';

const FormDisciplina = () => {
  const [escolas, setEscolas] = useState([]);

  const { 
    register, 
    handleSubmit, 
    formState: { errors, isSubmitting },
    reset 
  } = useForm({
    defaultValues: {
      nome: '',
      escolaId: ''
    }
  });

  useEffect(() => {
    const carregarEscolas = async () => {
      try {
        const response = await escolaService.getAll();
        
        const dadosEscolas = Array.isArray(response) 
          ? response 
          : (response.data || response.content || []);
        
        setEscolas(dadosEscolas);

        if (dadosEscolas.length === 0) {
          toast.info('Nenhuma escola encontrada');
        }
      } catch (error) {
        console.error('Erro ao carregar escolas:', error);
        toast.error('Erro ao carregar escolas');
      }
    };

    carregarEscolas();
  }, []);

  const onSubmit = async (data) => {
    try {
      console.log('Dados da disciplina:', data);
  
      if (!data.escolaId) {
        toast.error('Por favor, selecione uma escola');
        return;
      }
  
      const disciplinaData = {
        nome: data.nome,
        escolaId: data.escolaId
      };
  
      console.log('Dados processados:', disciplinaData);
  
      const response = await disciplinaService.create(disciplinaData);
      
      console.log('Resposta do servidor:', response);
  
      toast.success('Disciplina cadastrada com sucesso!');
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
        || 'Erro ao cadastrar disciplina. Por favor, tente novamente.';
      
      toast.error(errorMessage);
    }
  };

  return (
    <>
      <header className="header">
        <h2>Cadastro de Disciplina</h2>
      </header>

      <main className="container">
        <form onSubmit={handleSubmit(onSubmit)} className="form-disciplina">
          <div className="form-group">
            <label htmlFor="nome">Nome da Disciplina</label>
            <input
              type="text"
              id="nome"
              {...register('nome', { 
                required: 'Nome da disciplina é obrigatório',
                minLength: {
                  value: 3,
                  message: 'Nome deve ter no mínimo 3 caracteres'
                }
              })}
            />
            {errors.nome && <span className="error">{errors.nome.message}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="escolaId">Escola</label>
            <select
              id="escolaId"
              {...register('escolaId', { 
                required: 'Selecione uma escola',
              })}
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

          <div className="div-botao">
            <button 
              type="submit" 
              disabled={isSubmitting}
              className={isSubmitting ? 'loading' : ''}
            >
              {isSubmitting ? 'Salvando...' : 'Salvar Disciplina'}
            </button>
          </div>
        </form>
      </main>
    </>
  );
};

export default FormDisciplina;
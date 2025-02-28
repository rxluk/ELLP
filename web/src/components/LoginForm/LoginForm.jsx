import { useForm } from 'react-hook-form';
import { useState } from 'react';
import { useAuth } from '../../contexts/AuthContext';
import { registerColaborador } from '../../services/auth';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './LoginForm.css';

const LoginForm = () => {
  const [activeTab, setActiveTab] = useState('login');
  const { login } = useAuth();
  
  const { 
    register, 
    handleSubmit, 
    formState: { errors, isSubmitting },
    setError,
    reset 
  } = useForm();

  const onSubmit = async (data) => {
    try {
      if (activeTab === 'login') {
        const loginData = {
          login: data.login,
          password: data.password
        };
        await login(loginData);
        toast.success('Login realizado com sucesso!');
      } else if (activeTab === 'cadastro') {
        const colaboradorData = {
          nome: data.nome,
          email: data.email,
          registro: data.registro,
          login: data.login,
          password: data.password,
          role: "ADMIN"
        };
        
        await registerColaborador(colaboradorData);
        toast.success('Cadastro realizado com sucesso!');
        setActiveTab('login');
        reset();
      }
    } catch (error) {
      const errorMessage = error.response?.data?.message || 'Erro ao processar requisição';
      toast.error(errorMessage);
      setError('root', {
        type: 'manual',
        message: errorMessage
      });
    }
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <div className="login-header">
          <h1>Sistema de Gestão</h1>
        </div>
        
        <div className="login-tabs">
          <button
            type="button"
            className={`tab-button ${activeTab === 'login' ? 'active' : ''}`}
            onClick={() => setActiveTab('login')}
          >
            Entrar
          </button>
          <button
            type="button"
            className={`tab-button ${activeTab === 'cadastro' ? 'active' : ''}`}
            onClick={() => setActiveTab('cadastro')}
          >
            Cadastro
          </button>
        </div>

        <form onSubmit={handleSubmit(onSubmit)} className="login-form">
          {activeTab === 'login' ? (
            <>
              <div className="form-group">
                <label>Usuário:</label>
                <input
                  type="text"
                  placeholder="Digite seu usuário"
                  {...register('login', { required: 'Usuário é obrigatório' })}
                />
                {errors.login && 
                  <span className="error-message">{errors.login.message}</span>
                }
              </div>

              <div className="form-group">
                <label>Senha:</label>
                <input
                  type="password"
                  placeholder="Digite sua senha"
                  {...register('password', { required: 'Senha é obrigatória' })}
                />
                {errors.password && 
                  <span className="error-message">{errors.password.message}</span>
                }
              </div>
            </>
          ) : (
            <>
              <div className="form-group">
                <label>Nome:</label>
                <input
                  type="text"
                  placeholder="Digite seu nome completo"
                  {...register('nome', { required: 'Nome é obrigatório' })}
                />
                {errors.nome && 
                  <span className="error-message">{errors.nome.message}</span>
                }
              </div>

              <div className="form-group">
                <label>E-mail:</label>
                <input
                  type="email"
                  placeholder="Digite seu e-mail"
                  {...register('email', { 
                    required: 'E-mail é obrigatório',
                    pattern: {
                      value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                      message: "E-mail inválido"
                    }
                  })}
                />
                {errors.email && 
                  <span className="error-message">{errors.email.message}</span>
                }
              </div>

              <div className="form-group">
                <label>Registro:</label>
                <input
                  type="text"
                  placeholder="Digite seu registro acadêmico"
                  {...register('registro', { required: 'Registro é obrigatório' })}
                />
                {errors.registro && 
                  <span className="error-message">{errors.registro.message}</span>
                }
              </div>

              <div className="form-group">
                <label>Login:</label>
                <input
                  type="text"
                  placeholder="Digite seu login"
                  {...register('login', { required: 'Login é obrigatório' })}
                />
                {errors.login && 
                  <span className="error-message">{errors.login.message}</span>
                }
              </div>

              <div className="form-group">
                <label>Senha:</label>
                <input
                  type="password"
                  placeholder="Digite sua senha"
                  {...register('password', { 
                    required: 'Senha é obrigatória',
                    minLength: {
                      value: 6,
                      message: 'A senha deve ter no mínimo 6 caracteres'
                    }
                  })}
                />
                {errors.password && 
                  <span className="error-message">{errors.password.message}</span>
                }
              </div>
            </>
          )}

          {errors.root && 
            <div className="error-message">{errors.root.message}</div>
          }

          <button 
            type="submit" 
            className="login-button"
            disabled={isSubmitting}
          >
            {isSubmitting ? 'Processando...' : activeTab === 'login' ? 'Entrar' : 'Cadastrar'}
          </button>
        </form>
      </div>
    </div>
  );
};

export default LoginForm;
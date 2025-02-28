import { Link, useLocation } from 'react-router-dom';
import './SideBar.css';

export default function SideBar() {
    const location = useLocation();
    
    const menuItems = [
        { 
            icon: 'src/assets/icons/busca.svg', 
            alt: 'Busca',
            path: '/busca' 
        },
        { 
            icon: 'src/assets/icons/alunos.svg', 
            alt: 'Alunos',
            path: '/aluno' 
        },
        { 
            icon: 'src/assets/icons/familia.svg', 
            alt: 'Família',
            path: '/familia' 
        },
        { 
            icon: 'src/assets/icons/escola.svg', 
            alt: 'Escola',
            path: '/escola' 
        },
        { 
            icon: 'src/assets/icons/bairro.svg', 
            alt: 'Bairro',
            path: '/bairro' 
        },
        { 
            icon: 'src/assets/icons/disciplina.svg', 
            alt: 'Disciplina',
            path: '/disciplina' 
        },
        {
            icon: 'src/assets/icons/notas.svg',
            alt: 'Lançar Notas',
            path: '/notas'
        },
        {
            icon: 'src/assets/icons/relatorios.svg',
            alt: 'Relatórios',
            path: '/relatorios'
        },
        { 
            icon: 'src/assets/icons/home.svg', 
            alt: 'Home',
            path: '/' 
        },
    ];

    return (
        <nav className="sidebar">
            <div className="sidebar-logo">
                <Link to="/">
                    <img 
                        src="src/assets/icons/ellpinho.png" 
                        alt="Logo da Página" 
                        className="logo-img"
                    />
                </Link>
            </div>

            <ul className="sidebar-menu">
                {menuItems.map((item, index) => (
                    <li
                        key={index}
                        className={location.pathname === item.path ? 'active' : ''}
                    >
                        <Link to={item.path} title={item.alt}>
                            <img 
                                src={item.icon} 
                                alt={item.alt} 
                                className="sidebar-icon"
                            />
                            <span className="menu-text">{item.alt}</span>
                        </Link>
                    </li>
                ))}
            </ul>
        </nav>
    );
}